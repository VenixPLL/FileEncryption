package me.dickmeister.file.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    private static final String SALT = "CHUJ";

    public static byte[] encryptData(byte[] data, String master) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            var ivspec = new IvParameterSpec(iv);

            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            var spec = new PBEKeySpec(master.toCharArray(), SALT.getBytes(), 65536, 256);
            var tmp = factory.generateSecret(spec);
            var secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] decryptData(byte[] data, String master) {
        try {
            final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            var ivspec = new IvParameterSpec(iv);

            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            var spec = new PBEKeySpec(master.toCharArray(), SALT.getBytes(), 65536, 256);
            var tmp = factory.generateSecret(spec);
            var secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            var cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

}
