// temporary
// Refer to this website https://stackoverflow.com/questions/57416217/cryptojs-encrypt-in-aes-256-cbc-returns-an-unexpected-value
// Import crypto-js
var CryptoJS = require("crypto-js");

function encrypt(data) {
    let key = CryptoJS.enc.Utf8.parse('$7��D� ��GX�@���b�A�HK�:�y�')
    let iv = CryptoJS.enc.Utf8.parse('TS�&��C.����@�')
    let cipher = CryptoJS.AES.encrypt(data, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7 // In Java file, padding is Pkcs5 but this does not matter because AES uses a block size of 8 bytes
    });
    return cipher.toString();
}