package edu.uga.cinemabooking;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class testCrypto {
    String keyString = "$7��\u0017D� ��GX�@��\u0018�\u0019b�A\u0015�HK�:�y�\u0014";
    byte[] keyInBytes = new byte[StandardCharsets.UTF_8.encode(keyString).remaining()];
    String ivString = "TS�&�\u0002�\u0013C.����@�";
    byte[] ivInBytes = new byte[StandardCharsets.UTF_8.encode(ivString).remaining()];
    SecretKey key = new SecretKeySpec(keyInBytes,0,keyInBytes.length,"AES");
    IvParameterSpec iv = new IvParameterSpec(ivInBytes);

    String algorithm = "AES/CBC/PKCS5Padding";

    public String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            input = Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            e.toString();
        }
        return input;
    }

    public String decrypt(String encryptedMsg) {

        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(encryptedMsg));
            encryptedMsg = new String(plainText);
        } catch (Exception e){
            e.toString();
        }
        return encryptedMsg;

    } // decrypt
}
