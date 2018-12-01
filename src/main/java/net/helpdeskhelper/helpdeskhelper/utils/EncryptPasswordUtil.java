package net.helpdeskhelper.helpdeskhelper.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPasswordUtil {

	// Encrypts provided password with BCryptPasswordEncoder strong hashing function.
	public static String encryptPassword(String password) {
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        return pwEncoder.encode(password);
    }
}
