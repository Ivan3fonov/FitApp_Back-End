package com.fitapp.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {

   // @Autowired
    //private PasswordEncoder passwordEncoder;

    public String encodePassword(String password) {
      //return passwordEncoder.encode(password);
        String sha256hex = DigestUtils.sha256Hex(password);
            return sha256hex;
    }
}
