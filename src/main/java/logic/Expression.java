package main.java.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import main.java.model.State;

public class Expression {

	private List<State> startStates=new ArrayList<>();
	private List<State> endStates=new ArrayList<>();	
	
	public List<State> getStartStates() {
		return startStates;
	}
	public void setStartStates(List<State> startStates) {
		this.startStates = startStates;
	}
	public boolean addStartState(State e) {
		return startStates.add(e);
	}
	public void addAllStartStates(Collection<? extends State> c) {
		startStates.addAll(c);
	}
	public void clearStartStates() {
		startStates.clear();
	}
	
	
	public List<State> getEndStates() {
		return endStates;
	}
	public void setEndStates(List<State> finalStates) {
		this.endStates = finalStates;
	}
	public boolean addEndState(State e) {
		return endStates.add(e);
	}
	public void addAllEndStates(Collection<? extends State> c) {
		endStates.addAll(c);
	}
	public void clearEndStates() {
		endStates.clear();
	}
	
	
}
