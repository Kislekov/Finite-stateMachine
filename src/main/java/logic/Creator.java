package main.java.logic;

import main.java.antlr4.RegexLexer;
import main.java.antlr4.RegexParser;
import main.java.model.FiniteStateMachine;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Creator {
	
	public FiniteStateMachine createFromRegex(String regex) throws WrongRegexFormatException{
		ParseTree tree = parseRegex(regex); 
        Expression expression = processRegex(tree);
        FiniteStateMachine finiteStateMachine = createFiniteStateMachine(expression);
        System.out.println(finiteStateMachine.toString());
        return finiteStateMachine;
	}

	private FiniteStateMachine createFiniteStateMachine(Expression expression) {
		FiniteStateMachine finiteStateMachine=new FiniteStateMachine();
        finiteStateMachine.setStartStates(expression.getStartStates());
        finiteStateMachine.setEndStates(expression.getEndStates());
		return finiteStateMachine;
	}

	private Expression processRegex(ParseTree tree) {
		ParseTreeWalker walker = new ParseTreeWalker(); 
        RegexListenerImplementation regexListener=new RegexListenerImplementation();
        walker.walk(regexListener, tree); 
        Expression expression=regexListener.getFinalExpression();
		return expression;
	}

	private ParseTree parseRegex(String regex) throws WrongRegexFormatException{
		try{
			return tryParseRegex(regex);
		}catch(ParseCancellationException e){
			throw new WrongRegexFormatException(e);
		}
	}

	private ParseTree tryParseRegex(String regex) {
		CharStream input=new ANTLRInputStream(regex.toCharArray(), regex.length());
        RegexLexer lexer = new RegexLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RegexParser parser = new RegexParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        ParseTree tree = parser.expression();
		return tree;
	}
}
