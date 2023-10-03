import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Base64;

public class testCrypto {

    SecretKey key;
    IvParameterSpec iv;

    String algorithm = "AES/CBC/PKCS5Padding";
    public String encrypt(String input) {
        try {
            if (key == null && iv == null) {
                throw new IllegalArgumentException("Use setSecretKey() and setIV() before using this function.");
            } else if (key == null) {
                throw new IllegalArgumentException("Use setSecretKey() before using this function.");
            } else if (iv == null) {
                throw new IllegalArgumentException("Use setIV() before using this function.");
            } // if

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
        } catch (Exception e) {
            e.toString();
        }

    }

    public String decrypt(String encryptedMsg) {

        try {
            if (key == null && iv == null) {
                throw new IllegalArgumentException("Use setSecretKey() and setIV() before using this function.");
            } else if (key == null) {
                throw new IllegalArgumentException("Use setSecretKey() before using this function.");
            } else if (iv == null) {
                throw new IllegalArgumentException("Use setIV() before using this function.");
            } // if

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(encryptedMsg));
            return new String(plainText);
        } catch (Exception e){
            e.toString();
        }

    } // decrypt

    public void setSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(192);
            key = keyGenerator.generateKey();
        } catch (Exception e) {
            e.toString();
        }
    } // setSecretKey

    public void setIv() {
        byte[] ivBytes = new byte[16];
        new SecureRandom().nextBytes(ivBytes);
        iv = new IvParameterSpec(ivBytes);
    } // setIv
}
