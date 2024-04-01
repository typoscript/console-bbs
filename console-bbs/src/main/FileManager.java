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

	private static String getDataFromFile(String fileName) {
		String data = "";

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			while (br.ready())
				data += br.readLine() + "\n";
			
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println(fileName + " 파일 로드 실패");
		}
		
		return data;
	}

	public static void saveUserDataToFile(String data) {
		saveDataToFile(USER_FILE_NAME, data);
	}

	public static void saveBoardDataToFile(String data) {
		saveDataToFile(BOARD_FILE_NAME, data);
	}

	public static String getUserDataFromFile() {
		return getDataFromFile(USER_FILE_NAME);
	}
	
	public static String getBoardDataFromFile() {
		return getDataFromFile(BOARD_FILE_NAME);
	}
}
