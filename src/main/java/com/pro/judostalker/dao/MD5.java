/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Lalli
 */
class MD5 {
    
    public static String crypt(String crypt) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        return DigestUtils.md5DigestAsHex(m.digest(crypt.getBytes()));
        
    }
    
}
