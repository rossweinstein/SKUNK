package commandLine;

import java.util.Scanner;

public class InputHelper {
	
	private Scanner input;
	
	public InputHelper() {
		this.input = new Scanner(System.in);
	}
	
	public int getNumberInput(String prompt) {
		
		System.out.print(prompt + ": ");
		
		String answer = input.nextLine();
		int value = 0;
		
		try {
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 1;
	}
	
	public String getStringInput(String prompt) {
		return "YES";
	}
}
