package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private static FileReader fr;
	private static FileWriter fw;
	private static BufferedReader br;
	private static final String USER_FILE_NAME = "user.txt";
	private static final String BOARD_FILE_NAME = "board.txt";
	
	private FileManager() { }

	private static void saveDataToFile(String fileName, String data) {
		try {
			fw = new FileWriter(fileName);
			fw.write(data);
			fw.close();
		} catch (Exception e) {
			System.out.println(fileName + " 파일 저장 실패");
		}
	}

	public static void saveUserDataToFile(String data) {
		saveDataToFile(USER_FILE_NAME, data);
	}

	public static void saveBoardDataToFile(String data) {
		saveDataToFile(BOARD_FILE_NAME, data);
	}
}
