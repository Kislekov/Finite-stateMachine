package main.java.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import static main.java.logic.determinization.DeterminizationUtils.*;
import static main.java.logic.minimization.MinimizationUtils.*;

public class FiniteStateMachine {
	
	private State startState=new State();
	private List<State> endStates=new ArrayList<>();
	private List<State> allStates=new ArrayList<>();
	
	public State getStartState() {
		return startState;
	}
	public void setStartState(State startState) {
		this.startState = startState;
	}
	public List<State> getEndStates() {
		return endStates;
	}
	public void setEndStates(List<State> endStates) {
		this.endStates = endStates;
	}
	
	public List<State> getAllStates() {
		return allStates;
	}
	public void setAllStates(List<State> allStates) {
		this.allStates = allStates;
	}
	
	public int getNumberOfStates(){
		return allStates.size();
	}
	
	public boolean isMatch(String string){
		int startIndex=0;
		if(isStateMatch(string, startIndex, startState)){
			return true;
		}
		return false;
	}
	
	private boolean isStateMatch(String string,int index,State state){
		if(isEndConditionsMet(state, string, index)){
			return true;
		}
		int nextIndex=index+1;
		for (Action action : state.getExitActions()) {
			Character actionLetter=action.getActionLetter();
			if(actionLetter==null){
				if(isStateMatch(string, index, action.getNextState())){
					return true;
				}
			}else if(index<string.length()&&actionLetter.equals(string.charAt(index))){
				if(isStateMatch(string, nextIndex, action.getNextState())){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isEndConditionsMet(State state,String string,int index){
		if(index>=string.length()&&endStates.contains(state)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		String result = "\n\n\tStart state :\n";
		result += "\t" + startState.toString() + "\n";
		result += "\n\n\tEnd states :\n";
		for(State state : endStates){
			result += "\t" + state.toString() + "\n";
		}
		result += "\n\n\tAll states :\n\t";
		for(State state : allStates){
			result += "\t" + state.toString() + "\n";
		}
		return result;
	}
	
	public void determine(){
		List<List<Set<State>>> Dtran = new ArrayList<>();
		List<Character> incomingCharacters = getIncomingCharacters();
		Set<Set<State>> Dstates = new HashSet<>();
		Stack<Set<State>> unsignedState = new Stack<>();
		Set<State> T = null;
		Set<State> U = null;
		Set<State> newStartState = eClosure(startState);
		Dstates.add( newStartState);
		unsignedState.add(newStartState);
		while( ! unsignedState.isEmpty() ){
			T = unsignedState.pop();
			List<Set<State>> tableRow = new ArrayList<>();
			Dtran.add(tableRow);
			tableRow.add(T);
			for( Character a : incomingCharacters){
				U = eClosure( move(T, a));
				if( U.isEmpty()){
					tableRow.add(null);
				}
				else{
					if( ! Dstates.contains(U)){
						Dstates.add(U);
						unsignedState.push(U);
					}
					tableRow.add(U);
				}
			}
		}
		//BUILD NEW MACHINE
		Map< Set<State>, State> map = new HashMap<>();
		for( List<Set<State>> tableRow : Dtran){
			map.put(tableRow.get(0), new State());
		}
		List<Set<State>> tableRow = null;
		for(int indexRow = 0 ; indexRow < Dtran.size(); ++indexRow){
			tableRow = Dtran.get(indexRow);
			for(int indexColumn = 1 ; indexColumn < incomingCharacters.size() + 1; ++indexColumn){
				if( tableRow.get(indexColumn) != null){
					Action action = new Action();
					action.setActionLetter(incomingCharacters.get(indexColumn - 1));
					action.setNextState(map.get(tableRow.get(indexColumn)));
					(map.get(tableRow.get(0))).addExitAction(action);
				}
			}
		}
		//ADD NEW START AND END STATES
		startState = map.get((Dtran.get(0)).get(0));
		List<State> newEndStates = new ArrayList<>();
		for( Set<State> newState : Dstates){
			for( State oldState : newState){
				if( endStates.contains(oldState)){
					newEndStates.add(map.get(newState));		
					break;
				}
			}
		}
		endStates = newEndStates;
		//ADD ALL NEW STATES
		allStates = new ArrayList<>(map.values());
		return ;
	}
	
	private List<Character> getIncomingCharacters() {
		List<Character> resultList = new ArrayList<>();
		for( State state : allStates){
			for( Action action : state.getExitActions()){
				if( action.getActionLetter() != null && ! resultList.contains(action.getActionLetter())){
					resultList.add( action.getActionLetter());
				}				
			}
		}
		return resultList;
	}
	
	public void minimize(){
		Set<Set<State>> devidedSets = null;
		Set<Set<State>> P = new HashSet<>();
		Set<State> endStates = new HashSet<>();
		endStates.addAll(this.endStates);
		P.add(endStates);
		Set<State> difference = getAllEndDifference();
		if( difference != null){
			P.add(difference);
		}		
		List<Character> incomingCharacters = getIncomingCharacters();
		boolean pWasDevided = true;
		while( pWasDevided ){
			pWasDevided = false;
			for( Set<State> G : P){
				devidedSets = devideSetByOneSymbol(G, P, incomingCharacters);
				if( devidedSets != null){
					pWasDevided = true;
					P.remove(G);
					P.addAll(devidedSets);
				}
				if( pWasDevided ){
					break;
				}
			}
		}
		////Choose delegates and connect them.
		State delegate = null;
		State nextState = null;
		State nextDelegate = null;
		Set<State> nextStateGroup = null;
		List<Action> newExitActions = new ArrayList<>();
		for( Set<State> G : P){
			delegate = G.iterator().next();
			for( Character character : incomingCharacters){
				for( Action action : delegate.getExitActions()){
					if( action.getActionLetter().equals(character)){
						nextState = action.getNextState();
						nextStateGroup = findGroupContainingState(nextState, P);
						nextDelegate = nextStateGroup.iterator().next();
						Action newActionForDelegates = new Action();
						newActionForDelegates.setActionLetter(character);
						newActionForDelegates.setNextState(nextDelegate);
						newExitActions.add(newActionForDelegates);
					}
				}
			}
			delegate.setExitActions(newExitActions);
			newExitActions.clear();
		}
		/////Create new start, end and all states.
		List<State> newAllStates = new ArrayList<>();
		List<State> newEndStates = new ArrayList<>();
		State newStartState = null;
		for( Set<State> G : P ){
			delegate = G.iterator().next();
			newAllStates.add(delegate);
			if( G.contains(this.startState)){
				newStartState = delegate;
			}
			if( this.endStates.contains(delegate)){
				newEndStates.add(delegate);
			}
		}
		this.startState = newStartState;
		this.endStates = newEndStates;
		this.allStates = newAllStates;		
	}
	
	private Set<State> getAllEndDifference(){
		Set<State> result = new HashSet<>();
		result.addAll(allStates);
		result.removeAll(endStates);
		if( result.size() == 0 ){
			return null;
		}
		return result;
	}
}
