package javaPrograms;

import java.util.Scanner;

public class findInputNumberEvenOrOdd {
	
	
	public static void main(String [] arg) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("ENter the Number which you wanna find Even or Odd");
		
		
		int input = sc.nextInt();
		
		
		int answer = input%2;
		
		if(answer==1) {
			
			System.out.println("The entered number is Odd Number");
			
		} else System.out.println("The entered number is Even Number");
		
		
	}
	
	

}
