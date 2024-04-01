package main;

import java.util.Scanner;

public class Input {
	private static Scanner sc = new Scanner(System.in);
	
	private Input() { }

	public static String getString(String msg) {
		System.out.print(msg + ": ");
		return sc.nextLine();
	}

	public static int getNumber(String msg) {
		while (true) {
			String input = getString(msg);

			try {
				return Integer.parseInt(input);
			} catch (Exception e) { }
		}
	}	
}
