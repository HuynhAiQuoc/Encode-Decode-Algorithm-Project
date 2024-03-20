
package com.raven.symmetric.algorithms;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class StringSymmetricAlgorithms {

	public String encrypt(String text, SecretKey key, String algorithm, String mode, String padding) throws Exception {
		if (key == null)
			return null;
		Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] plaintext = text.getBytes(StandardCharsets.UTF_8);
		byte[] ciphertext = cipher.doFinal(plaintext);
		return Base64.getEncoder().encodeToString(ciphertext);
	}

	public String decrypt(String text, SecretKey key, String algorithm, String mode, String padding) throws Exception {
		if (key == null)
			return null;
		Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] ciphertext = Base64.getDecoder().decode(text);
		byte[] plaintext = cipher.doFinal(ciphertext);
		return new String(plaintext, StandardCharsets.UTF_8);
	}

	public String encryptUseIV(String text, SecretKey key, String algorithm, String mode, String padding, byte[] iv)
			throws Exception {
		if (key == null)
			return null;
		Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
		IvParameterSpec ips = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, ips);
		byte[] plaintext = text.getBytes(StandardCharsets.UTF_8);
		byte[] ciphertext = cipher.doFinal(plaintext);
		return Base64.getEncoder().encodeToString(ciphertext);
	}

	public String decryptUseIV(String text, SecretKey key, String algorithm, String mode, String padding, byte[] iv)
			throws Exception {
		if (key == null)
			return null;
		Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
		IvParameterSpec ips = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, ips);
		byte[] ciphertext = Base64.getDecoder().decode(text);
		byte[] plaintext = cipher.doFinal(ciphertext);
		return new String(plaintext, StandardCharsets.UTF_8);
	}

}
