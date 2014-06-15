package main.java.logic.minimization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.java.model.Action;
import main.java.model.State;

public class MinimizationUtils {

	private MinimizationUtils() {
	}

	public static Set<Set<State>> devideSetByOneSymbol( 
			Set<State> group, 
			Set<Set<State>> P, 
			List<Character> incomingCharacters
			){
		Set<Set<State>> resultSet =  new HashSet<>();
		///// KEY - group of next states for the same character, VALUE - set of states
		Map<Set<State>, Set<State>> mapForCharacter = new HashMap<>();
		State nextState = null;
		Set<State> nextStateGroup = null;
		Set<State> mapValue = null;
		for( Character character : incomingCharacters){
			Set<State> emptySet = new HashSet<>();
			for( State state : group){
				nextState = getNextStateForCharacter( state, character );
				nextStateGroup = findGroupContainingState(nextState, P);
				if( nextStateGroup == null){
					nextStateGroup = emptySet;
				}
				mapValue = mapForCharacter.get(nextStateGroup);
				if( mapValue != null){
					mapValue.add(state);
				}
				else{
					mapValue = new HashSet<>();
					mapValue.add(state);
					mapForCharacter.put(nextStateGroup, mapValue);
				}
			}
			///// if there's more than 2 values in map it means, that group should be devided into this new groups.
			if(mapForCharacter.size() >= 2){
				resultSet.addAll(mapForCharacter.values());
				return resultSet;
			}
			mapForCharacter.clear();
		}		
		return null;
	}
	
	public static Set<State> findGroupContainingState( State state, Set<Set<State>> P){
		Set<State> resultGroup = null;
		if( state != null ){
			for( Set<State> group : P){
				if( group.contains(state)){
					resultGroup = group;
					break;
				}
			}
		}		
		return resultGroup;
	}
	
	public static State getNextStateForCharacter( State startState, Character character){
		State resultState = null;
		for( Action exitAction : startState.getExitActions()){
			if( exitAction.getActionLetter().equals(character)){
				resultState = exitAction.getNextState();
				break;
			}
		}		
		return resultState;
	}
}
