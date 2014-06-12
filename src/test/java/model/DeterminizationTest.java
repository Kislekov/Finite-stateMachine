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
	public void shouldWorkAsRegexDo() throws WrongRegexFormatException{
		String regex="a+bc";
		FiniteStateMachine finiteStateMachine=creator.createFromRegex(regex);
		String line1="aaabc";
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
		finiteStateMachine.determine();
		System.out.println(finiteStateMachine.toString());
		assertEquals(Pattern.matches(regex, line1),finiteStateMachine.isMatch(line1));
	}
	
}
