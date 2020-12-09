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
public class Transition {
	String input;
	String pop;
	String push;
        State destination;
        int ruleNum;
        String gRule;

	public Transition()
	{
		input = null;
		pop = null;
		push = null;
	}

	public Transition(String input, String pop, String push, State destination, int ruleNum, String gRule) 
	{
		this.input = input;
		this.pop = pop;
		this.push = push;
                this.destination = destination;
                this.ruleNum = ruleNum;
                this.gRule = gRule;
	}

	public String getInput() 
	{
            return input;
	}

	public String getPop()
	{
            return pop;
	}

	public String getPush() 
	{
            return push;
	}
        public int getRuleNum(){
            return ruleNum;
        }
        public State getDestination(){
            return destination;
        }
        public String getgRule(){
            return gRule;
        }
   }

