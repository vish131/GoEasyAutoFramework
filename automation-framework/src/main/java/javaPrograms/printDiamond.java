package javaPrograms;

import java.util.Scanner;

public class printDiamond {
	
	
	public static void main (String [] arg) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("ENter no. of row needed to be printed");
		
		int n = sc.nextInt();
		
		System.out.println("Enter what symbol you wanna print it");
		
		char c = sc.next().charAt(0);
		
		
		for(int i=1; i<=n; i++) {						// iterating number of times you need
			
			for(int j=1; j<=n-i; j++) {					//n-i    (till n-1)   highlight of this prog
				
				System.out.print(" ");
				
			}
			
			for(int j=1; j<=i*2-1; j++) {				// i*2-1		highlight of this prog
				System.out.print(c);
			}
			
			
			System.out.println();
			
		}
		
		for(int i= n-1; i>=0; i-- ) {
			
			
			for(int j=1; j<=n-i; j++) {					// n-1	(STarts with n-1)	highlight of this prog
				
				System.out.print(" ");
			}
			
			for(int j=1; j<=i*2-1; j++) {				// i*2-1		highlight of this prog
				System.out.print(c);
			}
			
			System.out.println();
			
		}
	
	}

}
