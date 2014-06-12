package test.java.model;

import static org.junit.Assert.assertEquals;
import main.java.logic.Creator;
import main.java.logic.WrongRegexFormatException;
import main.java.model.FiniteStateMachine;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class DeterminizationTest {

	
	private Creator creator;

	@Before
	public void init(){
		creator=new Creator();
	}
	
	@Test
	public void test1() throws WrongRegexFormatException{
		String regex="a+bc";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		String line1="aaabc";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		finiteStateMachine.determine();
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
	}
	
	@Test
	public void test2() throws WrongRegexFormatException{
		String regex="a*a*as(da)*sd|(sd*sf)*a*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		finiteStateMachine.determine();
		String line1="aaaaaaaaadadasd";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		String line2="sddddsfssfa";
		assertEquals(Pattern.matches(regex, line2), finiteStateMachine.isMatch(line2));
		String line3="asdadadadaasdasd";
		assertEquals(Pattern.matches(regex, line3), finiteStateMachine.isMatch(line3));
		String line4="sdsasdsasfasdfsaszdasd";
		assertEquals(Pattern.matches(regex, line4), finiteStateMachine.isMatch(line4));
	}
	
	@Test
	public void test3() throws WrongRegexFormatException{
		String regex="a*|bca*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		finiteStateMachine.determine();
		String line1="aaaaaaaaa";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		String line2="bc";
		assertEquals(Pattern.matches(regex, line2), finiteStateMachine.isMatch(line2));
		String line3="bca";
		assertEquals(Pattern.matches(regex, line3), finiteStateMachine.isMatch(line3));
		String line4="bcaa";
		assertEquals(Pattern.matches(regex, line4), finiteStateMachine.isMatch(line4));		
	}
}
