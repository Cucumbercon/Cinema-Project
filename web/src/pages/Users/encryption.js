import JSEncrypt from "jsencrypt";

function encrypt(data) {
    
    const publicKey = '-----BEGIN PUBLIC KEY-----\n' +
    'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWHh1Lo7/JREHUNxprVJHjpT/hlyqgMFA4meDUnJC4ubQvjVfENXKfPPohLGORsfYlUjalYGkhP0rMvb3fpMJnNGE/aueYawSp66QDHWzYCzDEEo7w8FcD3FEcX5LQruX4YN2Z/hMiiNnw9eG/1SXCOLQIxPrSEgH51pGwzVfA1QIDAQAB' +
    '-----END PUBLIC KEY-----';
    
    const encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    const encrypted = encrypt.encrypt(data);

    return encrypted;
}

export { encrypt };
