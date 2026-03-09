package com.bank.credit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Utils tests")
public class EncryptionTest {

     @Test
     @DisplayName("Testing comparation of a word and the encrypted word")
     void sameWordEncrypt(){
        String word = "secret word";

        word = Encryption.encryptWord(word);

        boolean validEncriptation = Encryption.checkPassword("secret word", word);

        assertTrue(validEncriptation);
    }

    @Test
    @DisplayName("Testing comparation of a word and another word encrypted")
    void differentWordEncrypt(){
        String word = "one word";

        word = Encryption.encryptWord(word);

        boolean unvalidEncriptation = Encryption.checkPassword("another word", word);

        assertFalse(unvalidEncriptation);
    }

}
