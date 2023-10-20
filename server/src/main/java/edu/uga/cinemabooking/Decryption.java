package edu.uga.cinemabooking;

import javax.crypto.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Decryption {

    private static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWHh1Lo7/JREHUNxprVJHjpT/hlyqgMFA4meDUnJC4ubQvjVfENXKfPPohLGORsfYlUjalYGkhP0rMvb3fpMJnNGE/aueYawSp66QDHWzYCzDEEo7w8FcD3FEcX5LQruX4YN2Z/hMiiNnw9eG/1SXCOLQIxPrSEgH51pGwzVfA1QIDAQAB" + 
    "-----END PRIVATE KEY-----";


    public static String decryptData(String encryptedData) {
        try {
            String privateKeyPEM = PRIVATE_KEY.replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "");
            System.out.println(privateKeyPEM);
            byte[] encodedPrivateKey = Base64.getDecoder().decode(privateKeyPEM);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            String decryptedData = new String(decryptedBytes);
            System.out.println(decryptedData);

            return decryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return "Decryption fail";
        }
    }

    public static void main(String[] args) {
        decryptData("RkWw9R+7M3TOskV6tgAwIe0ipwe6i3F4pErijRdH8BYQByNR5PIU19A2Ms+b5KXeuFg5/nkHA+ZOBfFvFsZhp0f+mROrVgxOpdPxewvwNUevxd+9UvNWClL7iH1d9C+5qQ5k4i7ew0avN0clg8BBjEiLoV1zqJjQox64zqYUYOs=");
    }

}
