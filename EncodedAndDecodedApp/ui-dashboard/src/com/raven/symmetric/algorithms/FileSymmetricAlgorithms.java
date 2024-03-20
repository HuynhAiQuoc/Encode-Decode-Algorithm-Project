
package com.raven.symmetric.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class FileSymmetricAlgorithms {

	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	private SecretKey key;

	public void encryptFile(String sourceFile, String desFile, SecretKey key, String algorithm, String mode,
			String padding) throws Exception {
		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key);

			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(new File(desFile));

			byte[] input = new byte[64];
			int bytesRead;

			while ((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					fos.write(output);
				}
			}

			byte[] output = cipher.doFinal();
			if (output != null) {
				fos.write(output);
			}

			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Encrypted");
		} else {
			System.out.println("This is not file");
		}
	}
//	public static void main(String[] args) throws Exception {
//		SymmetricFile sf = new SymmetricFile();
//		SecretKey key = sf.getKey();
//		sf.setKey(key);
//		sf.encryptFile("‪D:\\QuanLyBenhNhan.docx", "‪D:\\1.docx", key, "DES", "E", "PKCS5Padding");
//	}

	public void decryptFile(String sourceFile, String desFile, SecretKey key, String algorithm, String mode,
			String padding) throws Exception {
		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key);
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(new File(desFile));
			byte[] input = new byte[64];
			int bytesRead;

			while ((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					fos.write(output);
				}
			}

			byte[] output = cipher.doFinal();
			if (output != null) {
				fos.write(output);
			}

			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Decrypted");
		} else {
			System.out.println("This is not file");
		}
	}

	public void encryptFileIV(String sourceFile, String desFile, SecretKey key, String algorithm, String mode,
			String padding, byte[] iv) throws Exception {
		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
			IvParameterSpec ips = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, ips);

			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(new File(desFile));

			byte[] input = new byte[64];
			int bytesRead;

			while ((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					fos.write(output);
				}
			}

			byte[] output = cipher.doFinal();
			if (output != null) {
				fos.write(output);
			}

			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Encrypted");
		} else {
			System.out.println("This is not file");
		}
	}

	public void decryptFileIV(String sourceFile, String desFile, SecretKey key, String algorithm, String mode,
			String padding, byte[] iv) throws Exception {
		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
			IvParameterSpec ips = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, key, ips);
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(new File(desFile));
			byte[] input = new byte[64];
			int bytesRead;

			while ((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					fos.write(output);
				}
			}

			byte[] output = cipher.doFinal();
			if (output != null) {
				fos.write(output);
			}

			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Decrypted");
		} else {
			System.out.println("This is not file");
		}
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}
	
	public SecretKey getKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);
		key = keyGenerator.generateKey();
		return this.key;

	}

}
