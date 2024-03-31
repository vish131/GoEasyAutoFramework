package javaPrograms;

import java.util.Iterator;

public class reverseStringWOStringFunction {

	public static void main(String[] args) {
		
		
		String name = "Vishnu";
		
		char[] value = name.toCharArray();
		
	for(int i=value.length-1; i>=0; i--) {
		
		System.out.print(value[i]);
	}
		
		

	}


}
