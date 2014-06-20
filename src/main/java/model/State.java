package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class State {
	
	private static long ID_GENERATOR=0;
	private long generateId(){
		ID_GENERATOR++;
		return ID_GENERATOR;
	}
	
	private long id=generateId();
	private List<Action> exitActions=new ArrayList<>();
	
	public List<Action> getExitActions() {
		return exitActions;
	}
	public void setExitActions(List<Action> exitActions) {
		List<Action> newActions = new ArrayList<>();
		newActions.addAll(exitActions);
		this.exitActions = newActions;
	}
	public long getId() {
		return id;
	}
	public void addExitAction(Action exitAction) {
		exitActions.add(exitAction);
	}
	
	@Override
	public String toString(){
		String result = "\n\t" + id + "\n";
		for(Action action : exitActions){
			result += "\n\t --" + action.getActionLetter() + "--> " + action.getNextState().id ;
		}
		return result;
	}
	
}
