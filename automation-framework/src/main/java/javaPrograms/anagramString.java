package javaPrograms;

import java.util.Arrays;

public class anagramString {

	public static void main(String[] args) {


		String one = "army";
		String two = "mary";
		
		char[] ch1 = one.toCharArray();
		char[] ch2 = two.toCharArray();
		
		Arrays.sort(ch1);
		Arrays.sort(ch2);
		
		String str1 = new String(ch1);
		String str2 = new String(ch2);
		

		System.out.print(str1.equals(str2));
		
	}

}
