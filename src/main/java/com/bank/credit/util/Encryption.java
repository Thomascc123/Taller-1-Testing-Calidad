package com.bank.credit.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encryption {

    public static String encryptWord(String word){
        return BCrypt.hashpw(word, BCrypt.gensalt());
    }

    public static boolean checkPassword(String word, String hashedWord){
        return BCrypt.checkpw(word, hashedWord);
    }

}
