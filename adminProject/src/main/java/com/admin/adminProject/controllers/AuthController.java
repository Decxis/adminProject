package com.admin.adminProject.controllers;

import com.admin.adminProject.dto.LoginRequest;
import com.admin.adminProject.model.db1.Usuario;
import com.admin.adminProject.repository.db1.UsuarioRepository;
import com.admin.adminProject.security.JwtUtil;
import com.admin.adminProject.util.GoogleAuthenticatorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UsuarioRepository usuarioRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    // 👇 ESTE es el constructor correcto para inyectar todo
    public AuthController(UsuarioRepository usuarioRepo, JwtUtil jwtUtil) {
        this.usuarioRepo = usuarioRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setSecretKey(GoogleAuthenticatorUtil.generarClaveSecreta());
        usuario.setActivo(true);
        return usuarioRepo.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        if (!GoogleAuthenticatorUtil.validarCodigo(usuario.getSecretKey(), request.getCodigo2FA())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código 2FA inválido");
        }

        String token = jwtUtil.generarToken(usuario.getUsername());
        return ResponseEntity.ok().body(Map.of("token", token));
    }
}