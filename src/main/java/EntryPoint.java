package main.java;

import static org.junit.Assert.*;
import main.java.logic.Creator;
import main.java.logic.WrongRegexFormatException;
import main.java.model.FiniteStateMachine;

public class EntryPoint {

	public static void main(String[] args) {
		Creator creator = new Creator();
		String regex ="a*|bca*";
		FiniteStateMachine finiteStateMachine = null;
		try {
			finiteStateMachine = creator.createFromRegex(regex);
		} catch (WrongRegexFormatException e) {
			e.printStackTrace();
		}
		finiteStateMachine.determine();
		System.out.println(finiteStateMachine.toString());
		System.out.println("\nNumber of states before minimization: " + finiteStateMachine.getNumberOfStates() + "\n");
		finiteStateMachine.minimize();		
		System.out.println("\nNumber of states after minimization: " + finiteStateMachine.getNumberOfStates() + "\n");
		System.out.println(finiteStateMachine.toString());
		String line1="aaaaaaaaa";
		assertTrue(finiteStateMachine.isMatch(line1));
		String line2="bc";
		assertTrue(finiteStateMachine.isMatch(line2));
		String line3="bca";
		assertTrue(finiteStateMachine.isMatch(line3));
		String line4="bcaa";
		assertTrue( finiteStateMachine.isMatch(line4));		
	}
}
