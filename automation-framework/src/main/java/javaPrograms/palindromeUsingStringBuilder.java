package javaPrograms;

public class palindromeUsingStringBuilder {

	public static void main(String[] args) {

			//palindrome Using String Builder
		
		String lang = "malayalam";
		
		
		StringBuilder builder = new StringBuilder(lang);
		
		String rev1 = builder.toString();
		
		String rev2 = builder.reverse().toString();
		
		System.out.println(rev1);
		System.out.println(rev2);
		
		if(rev1==rev2) {
			System.out.println("The given string is palindrome");
		} else System.out.println("The given string is not a palindrome");
		
	}

}
