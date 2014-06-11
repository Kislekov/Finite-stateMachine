package main.java.model;

public class Action {
	private Character actionLetter;
	private State nextState;
	
	public Character getActionLetter() {
		return actionLetter;
	}
	public void setActionLetter(Character actionLetter) {
		this.actionLetter = actionLetter;
	}
	public State getNextState() {
		return nextState;
	}
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	@Override
	public String toString(){
		String result = "";
		result += "(actionLetter : " + actionLetter + " nextStateId : " + nextState.getId() + ")";
		return result;
	}
}
