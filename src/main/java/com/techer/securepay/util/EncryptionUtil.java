package com.techer.securepay.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_SPEC_ALGORITHM = "AES";
    private static final String HASH_ALGORITHM = "SHA-256";

    private static final int IV_LENGTH = 16;
    private static final int KEY_LENGTH = 256;

    private static final String ENCRYPTION_KEY = "EncryptionKey";

    public static String encrypt(String plainText) throws Exception {
        SecretKey secretKey = generateSecretKey();
        byte[] iv = generateIV();

        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        SecretKey secretKey = generateSecretKey();
        byte[] iv = generateIV();

        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKey generateSecretKey() throws Exception {
        MessageDigest sha = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] keyBytes = ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8);
        byte[] hashedBytes = sha.digest(keyBytes);

        return new SecretKeySpec(hashedBytes, 0, KEY_LENGTH / 8, KEY_SPEC_ALGORITHM);
    }

    private static byte[] generateIV() {
        byte[] iv = new byte[IV_LENGTH];
        return iv;
    }
}
