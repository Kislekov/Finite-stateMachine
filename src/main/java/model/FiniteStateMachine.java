package main.java.model;

import java.util.ArrayList;
import java.util.List;

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
		String result = "";
		result += "[ startStates :\n\t";
		result=startState.toString();
		result += "\nendStates :\n\t";
		for(State state : endStates){
			result += state.toString() + "\n\t";
		}
		result += "]";
		return result;
	}
}
