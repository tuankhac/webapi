package com.neo.app.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Component;

/**
 *
 * @author skargopolov
 */
@Component
public class AuthenticationUtil {
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	public String generateSalt(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

	public String generateUserId(int length) {
		return generateSalt(length);
	}

	public String generateSecurePassword(String password, String salt) throws InvalidKeySpecException {

		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
		return Base64.getEncoder().encodeToString(securePassword);

	}

	private byte[] hash(char[] password, byte[] salt) throws InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	public static void main(String[] args) throws InvalidKeySpecException {
		AuthenticationUtil a = new AuthenticationUtil();
		System.out.println(a.generateSalt(30));
		System.out.println(a.generateSecurePassword("123456", "SM7yhiyb6UP7K28J8olZ5qR45uXk24"));
	}
}