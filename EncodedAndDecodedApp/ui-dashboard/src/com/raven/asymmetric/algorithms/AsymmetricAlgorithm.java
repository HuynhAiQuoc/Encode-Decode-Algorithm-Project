/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.asymmetric.algorithms;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class AsymmetricAlgorithm {
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public void genKeyPair(String algorithm, int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}

	public String encrypt(String text, String algorithm, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] plaintext = text.getBytes(StandardCharsets.UTF_8);
		byte[] ciphertext = cipher.doFinal(plaintext);
		return Base64.getEncoder().encodeToString(ciphertext);
	}

	public String decrypt(String text, String algorithm, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] ciphertext = Base64.getDecoder().decode(text);
		byte[] plaintext = cipher.doFinal(ciphertext);
		return new String(plaintext, StandardCharsets.UTF_8);
	}

	public String getStringPublicKey() {
		PublicKey key = getPublicKey();
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public String getStringPrivateKey() {
		PrivateKey key = getPrivateKey();
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public PrivateKey convertStringToPrivateKey(String privateKeyString, String algorithm) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		Base64.Decoder decoder = Base64.getDecoder();
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoder.decode(privateKeyString)));

	}

	public PublicKey convertStringToPublicKey(String publicKeyString, String algorithm) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		Base64.Decoder decoder = Base64.getDecoder();
		return keyFactory.generatePublic(new X509EncodedKeySpec(decoder.decode(publicKeyString)));

	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}
}
