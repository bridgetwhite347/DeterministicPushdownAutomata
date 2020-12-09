/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deterministicpushdownautomata;

/**
 *
 * @author bridg
 */
import java.util.ArrayList;
import java.util.Stack;
public class State {
    	ArrayList<Transition> transitions = new ArrayList<>();
	boolean stateEnd = false;
        String stateID;

	public State(String i) 
	{
		this.transitions = new ArrayList<>();
                stateID = i;
                stateEnd = false;
	}
        public String getStateID(){
            return stateID;
        }

	public void addNewStates(String input, String pop, String push, State destination, int ruleNum, String gRule) 
	{
		transitions.add(new Transition(input, pop, push, destination, ruleNum, gRule));
	}

	public ArrayList<Transition> getTransitions() 
	{
		return transitions;
	}
	
	public boolean isFinalState() 
	{
		return stateEnd;
	}
	
	public void changeStateStatus() 
	{
		stateEnd = true;
	}
}
