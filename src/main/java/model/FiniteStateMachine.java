package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class FiniteStateMachine {
	
	private List<State> startStates=new ArrayList<>();
	private List<State> endStates=new ArrayList<>();
	
	public List<State> getStartStates() {
		return startStates;
	}
	public void setStartStates(List<State> startStates) {
		this.startStates = startStates;
	}
	public List<State> getEndStates() {
		return endStates;
	}
	public void setEndStates(List<State> endStates) {
		this.endStates = endStates;
	}
	
	public boolean isMatch(String string){
		int startIndex=0;
		for (State startState : startStates) {
			if(isEndConditionsMet(startState, string, startIndex)){
				return true;
			}
			if(isStateMatch(string, startIndex, startState)){
				return true;
			}
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
		for(State state : startStates){
			result += state.toString() + "\n\t";
		}
		result += "\nendStates :\n\t";
		for(State state : endStates){
			result += state.toString() + "\n\t";
		}
		result += "]";
		return result;
	}
}
