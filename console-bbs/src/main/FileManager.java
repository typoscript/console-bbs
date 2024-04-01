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

	public static void saveUserDataToFile(String data) {
		saveDataToFile(USER_FILE_NAME, data);
	}
}
