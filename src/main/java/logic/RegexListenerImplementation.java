package main.java.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import main.java.antlr4.RegexBaseListener;
import main.java.antlr4.RegexParser.ExpressionContext;
import main.java.antlr4.RegexParser.MultipeExpressionGeneralContext;
import main.java.antlr4.RegexParser.MultipleExpressionPlusContext;
import main.java.antlr4.RegexParser.MultipleExpressionStarContext;
import main.java.antlr4.RegexParser.ParenExpressionContext;
import main.java.antlr4.RegexParser.SymbolExpressionContext;
import main.java.model.Action;
import main.java.model.State;

public class RegexListenerImplementation extends RegexBaseListener{
	
	private Map<ParseTree,Expression> map=new HashMap<>();
	private Expression finalExpression;
	
	@Override
	public void exitExpression(ExpressionContext ctx) {
		int leftPartLength=ctx.getChildCount();
		Expression expression=new Expression();
		if(ctx.expression()!=null){
			addRightPartSates(ctx, expression);	
			leftPartLength-=2;
		}
		createAllChildConnection(ctx, leftPartLength);
		addLeftPartStates(ctx, leftPartLength, expression);
		finalExpression=expression;
		map.put(ctx, expression);
	}


	private void addRightPartSates(ExpressionContext ctx, Expression expression) {
		Expression rightPartExpression=map.get(ctx.expression());
		expression.addAllStartStates(rightPartExpression.getStartStates());
		expression.addAllEndStates(rightPartExpression.getEndStates());
	}


	private void addLeftPartStates(ExpressionContext ctx, int leftPartLength,
			Expression expression) {
		Expression startExpression=map.get(ctx.getChild(0));
		expression.addAllStartStates(startExpression.getStartStates());
		int lastLeftPartChildIndex=leftPartLength-1;
		Expression endExpression=map.get(ctx.getChild(lastLeftPartChildIndex));
		expression.addAllEndStates(endExpression.getEndStates());
	}


	private void createAllChildConnection(ExpressionContext ctx, int length) {
		for(int i=1;i<length;i++){
			createChildConnection(ctx, i);
		}
	}


	private void createChildConnection(ExpressionContext ctx, int i) {
		ParseTree child=ctx.getChild(i);
		ParseTree previousChild=ctx.getChild(i-1);
		Expression expression=map.get(child);
		Expression previousExpression=map.get(previousChild);
		List<State> startStates=previousExpression.getEndStates();
		List<State> endStates=expression.getStartStates();
		createActions(startStates, endStates, null);
	}
	
	
	@Override
	public void exitParenExpression(ParenExpressionContext ctx) {
		RuleContext ruleContext=ctx.expression();
		Expression expression=map.get(ruleContext);
		map.put(ctx, expression);
	}
	
	@Override
	public void enterSymbolExpression(SymbolExpressionContext ctx) {
		Character symbol=ctx.getText().charAt(0);
		State previousState=new State();
		State nextState=new State();
		createAction(previousState,nextState,symbol);
		Expression expression=new Expression();
		expression.addStartState(previousState);
		expression.addEndState(nextState);
		map.put(ctx,expression);
	}
	
	private void createActions(List<State> startStates,List<State> endStates,Character symbol) {
		for(int i=0;i<startStates.size();i++){
			for(int j=0;j<endStates.size();j++){
				Action action=new Action();
				action.setActionLetter(symbol);
				action.setNextState(endStates.get(j));
				startStates.get(i).addExitAction(action);
			}
		}
	}
	
	private void createAction(State previousState,State nextState,Character symbol) {
		Action action=new Action();
		action.setActionLetter(symbol);
		action.setNextState(nextState);
		previousState.addExitAction(action);
	}
	
	@Override
	public void exitMultipeExpressionGeneral(MultipeExpressionGeneralContext ctx) {
		Expression expression=map.get(ctx.getChild(0));
		createActions(expression.getEndStates(), expression.getStartStates(), null);
		map.put(ctx, expression);
	}
	
	@Override
	public void exitMultipleExpressionPlus(MultipleExpressionPlusContext ctx) {
		RuleContext ruleContext=ctx.multipeExpressionGeneral();
		Expression expression=map.get(ruleContext);
		map.put(ctx, expression);
		
	}
	
	@Override
	public void exitMultipleExpressionStar(MultipleExpressionStarContext ctx) {
		RuleContext ruleContext=ctx.multipeExpressionGeneral();
		Expression expression=map.get(ruleContext);
		expression.addAllEndStates(expression.getStartStates());
		map.put(ctx, expression);
	}
	
	
	public Expression getFinalExpression(){
		return finalExpression;
	}
	
}
