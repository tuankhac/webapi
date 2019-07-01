package com.neo.app.utils;

public class AlgorithmHash {
	private SHA512 sha512;
	private MD5 md5;

	public SHA512 getSha512() {
		return sha512;
	}

	public void setSha512(SHA512 sha512) {
		this.sha512 = sha512;
	}

	public MD5 getMd5() {
		return md5;
	}

	public void setMd5(MD5 md5) {
		this.md5 = md5;
	}

	public AlgorithmHash(SHA512 sha512, MD5 md5) {
		super();
		this.sha512 = sha512;
		this.md5 = md5;
	}

	public static String encrypt(String className, String input) {
		String hashPass = null;
		switch (className) {
		case "MD5":
			hashPass = MD5.encrypt(input);
			break;

		default:
			hashPass = SHA512.encrypt(input);
			break;
		}
		return hashPass;
	}

	public static String encrypt(String className, String input, int interator) {
		String hashPass = null;
		switch (className) {
		case "MD5":
			do {
				hashPass = MD5.encrypt(input);
				input = hashPass;
				interator--;
			} while (interator > 0);
			break;

		default:
			do {
				hashPass = SHA512.encrypt(input);
				input = hashPass;
				interator--;
			} while (interator > 0);
			break;
		}
		return hashPass;
	}

	public static String encrypt(String className, String input, String saltPass) {
		String hashPass = null;
		switch (className) {
		case "MD5":
			hashPass = MD5.encrypt(input + saltPass);
			input = hashPass;
			break;

		default:
			hashPass = SHA512.encrypt(input + saltPass);
			input = hashPass;
			break;
		}
		return hashPass;
	}

	public static String encrypt(String className, String input, int interator, String saltPass) {
		String hashPass = null;
		switch (className) {
		case "MD5":
			do {
				hashPass = MD5.encrypt(input);
				input = hashPass;
				interator--;
			} while (interator > 0);
			hashPass = MD5.encrypt(hashPass + saltPass);
			break;

		default:
			do {
				hashPass = SHA512.encrypt(input);
				input = hashPass;
				interator--;
			} while (interator > 0);
			hashPass = SHA512.encrypt(hashPass + saltPass);
			break;
		}
		return hashPass;
	}
}
