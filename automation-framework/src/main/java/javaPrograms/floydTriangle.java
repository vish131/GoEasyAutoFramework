package javaPrograms;

import java.util.Scanner;

public class floydTriangle {

	public static void main(String[] args) {
		// triangle with a different number of rows
		
		int number = 1;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of row needed to perform Floyd Triangle ");
		
		int n = sc.nextInt();
		
		
		for(int i=1; i<=n; i++) {
			
			
			for(int j=1; j<=i; j++) {
				
				System.out.print(number);
				number++;
				
			}
			
			System.out.println();
			
		}
		
		
		
		
		
		
		

	}

}
