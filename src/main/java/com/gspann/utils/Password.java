package com.gspann.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Ashish Jaiswal
 *
 */
public class Password {
	private static int workload = 12;
	
	public static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);
		return(hashed_password);
	}
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		System.out.println(stored_hash);
		return BCrypt.checkpw(password_plaintext, stored_hash);
	}
	
	/*public static void main(String[] args) {
		System.out.println(checkPassword("admin",hashPassword("admin")));
	}*/
}
