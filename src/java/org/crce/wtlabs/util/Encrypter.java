/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.util;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author Flav
 */
public class Encrypter {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec keySpec;
    private SecretKey secretKey;
    private SecretKeyFactory keyFactory;
    private Cipher cipher;
    byte[] arrayBytes;
    private String encryptionKey;
    private String encryptionScheme;
    
    public Encrypter() {
        
        try {
            
            this.encryptionKey = "WTProjectWTProjectWTProject";
            this.encryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = encryptionKey.getBytes(UNICODE_FORMAT);
            keySpec = new DESedeKeySpec(arrayBytes);
            keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
            cipher = Cipher.getInstance(encryptionScheme);
            secretKey = keyFactory.generateSecret(keySpec);
            
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String encrypt(String unencryptedString) {
       
        String encryptedString = null;
        
        try {
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            
            encryptedString = new String(Base64.encode(encryptedText));
            
        } catch (InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encryptedString;
    }
    
    public String decrypt(String encryptedString) {
        
        String decryptedString = null;
        
        try {
            
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedText = Base64.decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            
            decryptedString = new String(plainText);
            
        } catch (InvalidKeyException | Base64DecodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return decryptedString;
    }
    
}
