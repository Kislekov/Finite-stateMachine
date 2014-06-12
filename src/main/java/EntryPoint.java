package main.java;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import main.java.logic.Creator;
import main.java.logic.WrongRegexFormatException;
import main.java.model.FiniteStateMachine;

public class EntryPoint {

	public static void main(String[] args) {
		String regex="a+bc";
		Creator creator = new Creator();
		FiniteStateMachine finiteStateMachine = null;
		try {
			finiteStateMachine = creator.createFromRegex(regex);
		} catch (WrongRegexFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line1="aaabc";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		finiteStateMachine.determine();
		System.out.println(finiteStateMachine.toString());
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
	}
}
