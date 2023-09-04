package com.lgs.usermgmt.utils;

import java.util.Base64;

public class EncryptionDecryptionUtility {

    public static String encrypt(String plainText){
        //TODO add any encryption/decryption logic here.
        //I am using Base64 encoding as encryption is not in scope as per given problem statement.
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }
}
