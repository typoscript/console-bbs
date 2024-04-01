package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private static FileReader fr;
	private static FileWriter fw;
	private static BufferedReader br;
	private static final String USER_FILE = "user.txt";
	private static final String BOARD_FILE = "board.txt";
	
	private FileManager() { }

	public static void saveUserDataToFile(String data) {
		try {
			fw = new FileWriter(USER_FILE);
			fw.write(data);
			fw.close();
		} catch (Exception e) {
			System.out.println("파일 저장 실패");
		}
	}
}
