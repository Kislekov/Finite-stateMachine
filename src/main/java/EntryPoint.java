package main.java;

import main.java.logic.Creator;
import main.java.logic.WrongRegexFormatException;
import main.java.model.FiniteStateMachine;

public class EntryPoint {

	public static void main(String[] args) {
		if( args != null && args.length >= 1){
			Creator creator = new Creator();
			FiniteStateMachine finiteStateMachine = null;
			try {
				finiteStateMachine = creator.createFromRegex(args[0]);
				System.out.println("Constructed machine:");
				System.out.println(finiteStateMachine.toString());
				finiteStateMachine.determine();
				System.out.println("Determinated machine:");
				System.out.println(finiteStateMachine.toString());
				finiteStateMachine.minimize();
				System.out.println("Minimized machine:");
				System.out.println(finiteStateMachine.toString());
				if( args.length > 1){
					System.out.println("Test string : " + args[1]);
					if( finiteStateMachine.isMatch(args[1])){
						System.out.println("Matched : TRUE");
					}
					else{
						System.out.println("Matched : FALSE");
					}
				}
				System.out.println(args[0]);
				if(args.length > 1){
					System.out.println(args[1]);
				}
			} catch (WrongRegexFormatException e) {
				System.out.println("Syntax error in regex.");
			}
			
		} 
		else{
			System.out.println("You should pass regex and test string (at will) to check it.");
		}
	}
}
