package test.java.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.logic.Creator;
import main.java.logic.WrongRegexFormatException;
import main.java.model.FiniteStateMachine;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class CreatorTest {

	
	private Creator creator;

	@Before
	public void init(){
		creator=new Creator();
	}
		
	
	@Test(expected=WrongRegexFormatException.class)
	public void  shouldThrowParseRegexExcpetion() throws WrongRegexFormatException{
		String regex="((sdfg*df)d|fs|d|a*s|d(sa|d(sda)*)";
		creator.createFromRegex(regex);
	}
	
	@Test(expected=WrongRegexFormatException.class)
	public void  shouldThrowParseRegexExcpetion2() throws WrongRegexFormatException{
		String regex="a*\\\\\\a\\44$$";
		creator.createFromRegex(regex);
	}
	
	@Test
	public void shouldWorkAsRegexDo() throws WrongRegexFormatException{
		String regex="ab*ba(L|D)+";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		String line1="abbbbbbaaaaaaLDL";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		String line2="abbba";
		assertEquals(Pattern.matches(regex, line2), finiteStateMachine.isMatch(line2));
		String line3="abaL";
		assertEquals(Pattern.matches(regex, line3), finiteStateMachine.isMatch(line3));
		String line4="abbbaaaaLDLDLb";
		assertEquals(Pattern.matches(regex, line4), finiteStateMachine.isMatch(line4));
		assertTrue(notNullStates(finiteStateMachine));
	}
	
	@Test
	public void shouldWorkAsRegexDo2() throws WrongRegexFormatException{
		String regex="(sdfg*dfd|fs|d|a*s|d(sa|dsda)*)";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		String line1="sdfgggdfd";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		String line2="dsadsdasadsdsda";		
		assertEquals(Pattern.matches(regex, line2), finiteStateMachine.isMatch(line2));
		String line3="ddsdasda";	
		assertEquals(Pattern.matches(regex, line3), finiteStateMachine.isMatch(line3));		
		assertTrue(notNullStates(finiteStateMachine));
	}
	
	
	@Test
	public void shouldWorkAsRegexDo3() throws WrongRegexFormatException{
		String regex="a*a*as(da)*sd|(sd*sf)*a*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
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
	public void shouldWorkAsRegexDo4() throws WrongRegexFormatException{
		String regex="a*|bca*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		String line1="aaaaaaaaa";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		String line2="bc";
		assertEquals(Pattern.matches(regex, line2), finiteStateMachine.isMatch(line2));
		String line3="bca";
		assertEquals(Pattern.matches(regex, line3), finiteStateMachine.isMatch(line3));
		String line4="bcaa";
		assertEquals(Pattern.matches(regex, line4), finiteStateMachine.isMatch(line4));	
		assertTrue(notNullStates(finiteStateMachine));
	}
	
	@Test
	public void shouldCreateAllStatementList() throws Exception{
		String regex="a*|bca*";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		assertNotNull(finiteStateMachine.getAllStates());
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
