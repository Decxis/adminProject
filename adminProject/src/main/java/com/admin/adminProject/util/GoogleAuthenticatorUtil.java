package com.admin.adminProject.util;


import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;

public class GoogleAuthenticatorUtil {

    private static final DefaultSecretGenerator generator = new DefaultSecretGenerator();
    private static final DefaultCodeVerifier verifier = new DefaultCodeVerifier(
            new DefaultCodeGenerator(),
            new SystemTimeProvider()
    );

    public static String generarClaveSecreta() {
        return generator.generate();
    }

    public static boolean validarCodigo(String secret, String code) {
        return verifier.isValidCode(secret, code);
    }
}