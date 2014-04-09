package main.java;

import java.util.regex.Pattern;

public class EntryPoint {

	public static void main(String[] args) {
		String regex="aaa|b";
		String input="b";
		System.out.println(Pattern.matches(regex,input));
	
		String regex2="(aaa)b";
		String input2="aaab";
		System.out.println(Pattern.matches(regex2,input2));
		
		String regex3="a*|b";
		String input3="aaa";
		System.out.println(Pattern.matches(regex3,input3));
	}
}
