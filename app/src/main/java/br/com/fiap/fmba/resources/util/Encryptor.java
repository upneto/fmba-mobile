package br.com.fiap.fmba.resources.util;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class Encryptor {

    private final Cipher cipher;
    private final SecretKeySpec key;
    private AlgorithmParameterSpec spec;
    private static final String PASS = "7C0403562FA53BAE8A8A9AE42FD43C781BABB10B9940753F1CA53DEF2BD00382";

    public static Encryptor getInstance() throws Exception {
        return new Encryptor();
    }

    private Encryptor() throws Exception {
        // hash password with SHA-256 and crop the output to 128-bit for key
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(PASS.getBytes("UTF-8"));
        byte[] keyBytes = new byte[32];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        this.cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        this.key = new SecretKeySpec(keyBytes, "AES");
        this.spec = this.getIV();
    }

    private AlgorithmParameterSpec getIV() {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec;
    }

    public String execute(String text) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        String encryptedText = new String(Base64.encode(encrypted, Base64.DEFAULT), "UTF-8");
        return encryptedText.replaceAll("\n", "");
    }
}

