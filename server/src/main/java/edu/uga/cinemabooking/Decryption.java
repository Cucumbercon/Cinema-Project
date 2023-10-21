package edu.uga.cinemabooking;

import javax.crypto.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Decryption {

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJYeHUujv8lEQdQ3GmtUkeOlP+GXKqAwUDiZ4NSckLi5tC+NV8Q1cp88+iEsY5Gx9iVSNqVgaSE/Ssy9vd+kwmc0YT9q55hrBKnrpAMdbNgLMMQSjvDwVwPcURxfktCu5fhg3Zn+EyKI2fD14b/VJcI4tAjE+tISAfnWkbDNV8DVAgMBAAECgYAhVGLXPC5U+q9t9WULfv2cMECobuZzJf7trjaVpgRYyzxnEWfy45YFJmX3rxIVU1COFTyWR7tXuyR5pEgPq1ZQ+hPNh6gPEXHIiD9zmHwNmWCV6QaAzmJzcdezFExJMY0Td4+uFBPVdxM4qyS/K1pto8F8dAiCXy7ibGOI1WhFuQJBAM0Y5vJDdratq8B76lSHrCY8K+GC/w5EXvcYlfVvIzrz+XYA6g2FA5n8p7kqFqOT8j+/6JiKeultQSJit7VF9B0CQQC7YAQREytwJA9O/9zutcOYqpc9VTmi2sG1YrvEaPqXKtYBaPnjyuSLwaNK2Lj6TC50pKbX6lutO21/5oTIpnIZAkEArpgaUfYYYx6o7S0PqHHunIXwnQMU+SI+7X6iedLoXNFAdbIFjQjErhuk9D3eE9ZoDNjjuqd4RqpZHlqHJOuuCQJALu8+FYjXuJ9Ptlog3SpqdDNG7vYKGeDx5J12qxwgqPzx3iiNb6HC3b8DSBxBOXEbsdwMbsklbZ8VLpRvblNDAQJAO+YUjbgBHnxY3xfI4+2rDxpHH0QKwBHuIIOM8t8cccX8+m3swqhxOWOFUoJpEqaOzQ3njemeYHoj6KQEA2/1xQ==";



    public String decryptData(String encryptedData) {
        try {
            byte[] encodedPrivateKey = Base64.getDecoder().decode(PRIVATE_KEY);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            String decryptedData = new String(decryptedBytes);
            
            return decryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return "Decryption fail";
        }
    }

    // public static void main(String[] args) {
    //     System.out.println(decryptData("YbFiGFX4PJoDD0g0XcZaO8yoG0YcoDLTt//CisK+u08Vv1NEvuR+WGhpcl6mFSffM2lsqS0TDww7Nvh0Qc940ph3bCpyNcHGdPNA/oxEI8s84tTCnx8X+Rhkkxzr0Gzx6HK+vZ2O3NI3OnWAYuDrLtcfUppeyW6oKDTeSp8KdeM="));
    // }

}
