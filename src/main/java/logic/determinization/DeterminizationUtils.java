package main.java.logic.determinization;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import main.java.model.Action;
import main.java.model.State;

public class DeterminizationUtils {

	private DeterminizationUtils(){
		
	}
	
	public static Set<State> eClosure ( State s){
		Set<State> resultSet = new HashSet<>();
		resultSet.add(s);
		for( Action action : s.getExitActions()){
			if( action.getActionLetter() == null){
				resultSet.add(action.getNextState());
			}
		}
		return resultSet;
	}
	
	public static Set<State> eClosure ( Set<State> T){
		Set<State> resultSet = new HashSet<>();
		Stack<State> stack = new Stack<>();
		stack.addAll(T);
		resultSet.addAll(T);
		State t = null;
		State u = null;
		while( ! stack.isEmpty() ){
			t = stack.pop();
			for( Action action : t.getExitActions()){
				if( action.getActionLetter() == null){
					u = action.getNextState();
					if( ! resultSet.contains( u )){
						resultSet.add(u);
						stack.push(u);
					}
				}
			}
		}
		return resultSet;
	}
	
	public static Set<State> move ( Set<State> T, Character a){
		Set<State> resultSet = new HashSet<>();
		for( State t : T){
			for( Action action : t.getExitActions()){
				if( action.getActionLetter() != null 
						&& ( action.getActionLetter()).equals(a) ){
					resultSet.add( action.getNextState() );
				}
			}
		}
		return resultSet;
	}
	
}
