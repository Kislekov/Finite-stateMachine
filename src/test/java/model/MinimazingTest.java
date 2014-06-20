package test.java.model;

import static org.junit.Assert.*;
import main.java.logic.Creator;
import main.java.logic.WrongRegexFormatException;
import main.java.model.FiniteStateMachine;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class MinimazingTest {

	
	private Creator creator;

	@Before
	public void init(){
		creator=new Creator();
	}
	
	@Test
	public void test2() throws WrongRegexFormatException{
		String regex="a*a*as(da)*sd|(sd*sf)*a*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		finiteStateMachine.determine();
		System.out.println("\nNumber of states before minimization: " + finiteStateMachine.getNumberOfStates() + "\n");
		finiteStateMachine.minimize();
		System.out.println("\nNumber of states after minimization: " + finiteStateMachine.getNumberOfStates() + "\n");
		String line1="aaaaaaaaadadasd";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		String line2="sddddsfssfa";
		assertEquals(Pattern.matches(regex, line2), finiteStateMachine.isMatch(line2));
		String line3="asdadadadaasdasd";
		assertEquals(Pattern.matches(regex, line3), finiteStateMachine.isMatch(line3));
		String line4="sdsasdsasfasdfsaszdasd";
		assertEquals(Pattern.matches(regex, line4), finiteStateMachine.isMatch(line4));
		assertTrue(notNullStates(finiteStateMachine));
	}
	
	@Test
	public void test3() throws WrongRegexFormatException{
		String regex ="a*|bca*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		finiteStateMachine.determine();
		System.out.println("\nNumber of states before minimization: " + finiteStateMachine.getNumberOfStates() + "\n");
		finiteStateMachine.minimize();
		System.out.println("\nNumber of states after minimization: " + finiteStateMachine.getNumberOfStates() + "\n");
		String line1="aaaaaaaaa";
		System.out.println(finiteStateMachine.toString());
		assertTrue(finiteStateMachine.isMatch(line1));
		String line2="bc";
		assertTrue(finiteStateMachine.isMatch(line2));
		String line3="bca";
		assertTrue(finiteStateMachine.isMatch(line3));
		String line4="bcaa";
		assertTrue( finiteStateMachine.isMatch(line4));	
		assertTrue(notNullStates(finiteStateMachine));
	}
	
	private boolean notNullStates( FiniteStateMachine finiteStateMachine){
		if( finiteStateMachine.getStartState() != null
				&& finiteStateMachine.getEndStates() != null 
				&& finiteStateMachine.getEndStates().size() >= 1
				&& finiteStateMachine.getAllStates() != null 
				&& finiteStateMachine.getAllStates().size() >= 1
				){
			return true;
		}
		return false;
	}
}
