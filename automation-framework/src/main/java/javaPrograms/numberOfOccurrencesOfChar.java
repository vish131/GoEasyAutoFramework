package javaPrograms;

import java.util.Scanner;

public class numberOfOccurrencesOfChar {

	public static void main(String[] args) {

			//number Of Occurrences Of Char in a given string
		
		String str = "From sprinkler splashes to fireplace ashes";
		
		int number = 0;
		
		char[] charSplit = str.toCharArray();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the CHaracter you wanna find in the string");
		
		char input = sc.next().charAt(0);
		
		
		for(char letter : charSplit ) {
			
			if(letter==input) {
				number++;
			}
		}
		
		System.out.println("Number of occurrence of the char is : " + number);
		
	}


}
