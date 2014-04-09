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
		this.exitActions = exitActions;
	}
	public long getId() {
		return id;
	}
	public void addExitAction(Action exitAction) {
		exitActions.add(exitAction);
	}
	
	
}
