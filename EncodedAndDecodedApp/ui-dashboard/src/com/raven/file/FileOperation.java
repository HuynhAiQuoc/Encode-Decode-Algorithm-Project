
package com.raven.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileOperation {
	public void writeFile(String data, String pathFile) throws IOException {
		File file = new File(pathFile);

		if (!file.exists()) {
			file.createNewFile();
		}

		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(data);
		// Closing BufferedWriter Stream
		bw.close();
	}

	public String readFile(String pathFile) throws IOException {
		String result = "";
		File file = new File(pathFile);
		InputStream inputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(inputStreamReader);

		String line = "";
		while ((line = reader.readLine()) != null) {
			result += line;
		}
		return result;
	}
}

//	public static void main(String[] args) throws IOException {
//		FileOperation fo = new FileOperation();
//		fo.writeFile("Hello world", "D:\\test keyFile.txt");
//
//		System.out.println(fo.readFile("D:\\test KeyFile.txt"));
//	}
