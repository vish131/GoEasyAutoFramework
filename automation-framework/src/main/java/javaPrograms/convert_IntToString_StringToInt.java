package javaPrograms;

public class convert_IntToString_StringToInt {

	public static void main(String[] args) {


		String str = "12345";
		
		int i = Integer.parseInt(str);
		
		System.out.println(i);
		
		String s = String.valueOf(i);
		
		System.out.println(s);
	}

}
