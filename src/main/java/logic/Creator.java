package main.java.logic;

import java.util.List;

import main.java.antlr4.RegexLexer;
import main.java.antlr4.RegexParser;
import main.java.model.Action;
import main.java.model.FiniteStateMachine;
import main.java.model.State;

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
        return finiteStateMachine;
	}

	private FiniteStateMachine createFiniteStateMachine(Expression expression) {
		FiniteStateMachine finiteStateMachine=new FiniteStateMachine();
		createStartStateConnection(finiteStateMachine.getStartState(), expression.getStartStates());
		finiteStateMachine.setEndStates(expression.getEndStates());
		expression.getAllStates().add(finiteStateMachine.getStartState());
		finiteStateMachine.setAllStates(expression.getAllStates());
		return finiteStateMachine;
	}

	private void createStartStateConnection(State startState,List<State> nextStates){
		for(State nextState:nextStates){
			Action exitAction=new Action();
			exitAction.setNextState(nextState);
			startState.addExitAction(exitAction);
		}
		
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
		}catch(Throwable e){
			throw new WrongRegexFormatException(e);
		}
	}

	private ParseTree tryParseRegex(String regex) {
		CharStream input=new ANTLRInputStream(regex.toCharArray(), regex.length());
        RegexLexer lexer = new RegexLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ExcErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RegexParser parser = new RegexParser(tokens);
        parser.removeErrorListeners();
        parser.setErrorHandler(new BailErrorStrategy());
        parser.addErrorListener(new ExcErrorListener());
        ParseTree tree = parser.expression();
		return tree;
	}
}
