package javaPrograms;

import java.util.Set;
import java.util.TreeSet;

public class removeDuplicate {

	public static void main(String[] args) {
		// remove duplicate from a array 
		
		
		/*
		 
		 * Duplicates:

			ArrayList: Allows duplicate elements. You can have multiple occurrences of the same element in an ArrayList.
			TreeSet: Does not allow duplicate elements. When you try to add a duplicate element, it will simply be ignored.
		 
		  
		  
		 * */
		
		String[] str = {"1,2,1,2,1,2,3,2,3,4,5,6,4,6,6,7,7,8,8,7,8,8,9,9,0"};
		
		char[] ch = str.toString().toCharArray();
		
		Set<Character> S = new TreeSet<Character>();
		
		for(char uni : ch) {
			
			S.add(uni);
			
		}
		
		System.out.println("Print all the unique values : " + S);
		
		

	}

}
