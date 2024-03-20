
package com.raven.symmetric.algorithms;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeySymmetricAlgorithms {

	public SecretKey createKey(String algorithm, int size) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(size);
		return keyGenerator.generateKey();
	}

	public String convertSecretKeytoString(SecretKey key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());

	}

	public SecretKey convertStringToSecretKey(String key, String algorithm) {
		byte[] decodedKey = Base64.getDecoder().decode(key);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);
		return originalKey;
	}

//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		KeySymmetric sk = new KeySymmetric();
//		SecretKey key = sk.createKey("DES", 56);
//		System.out.println(key);
//		String text = Base64.getEncoder().encodeToString(key.getEncoded());
//		System.out.println(text);
//		System.out.println(sk.convertStringToSecretKey(text, "DES"));
//
//	}

}
