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
import java.util.Scanner;
public class DeterministicPushdownAutomata {
    
    static int step = 0;
    static State [] states = new State[5];
    static State current;
    static Transition thisTr = new Transition();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("This program takes in a string and determines if the string exists in language L {a^n b^n | n >= 0}");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scan.next();
        
        if(!validInput(input)){
            System.out.println("Not a valid string in the language.");
            return;
        }
        
        Stack<Character> stack = new Stack<>();
        ArrayList<Character> unreadInput = new ArrayList<>();
        
        for(int i = 0; i < input.length(); i++){
            unreadInput.add(input.charAt(i));
        }
        
        createStates();
        
        current = states[0];
        thisTr = current.getTransitions().get(0);
        boolean inLang = true;
        
        // Headers for Table.
        System.out.println(String.format("%15s %15s %60s %60s %25s %25s", "Step", "State", "Unread Input", "Stack", "Δ Rule", "Grammar Rule"));
        
        display(current, unreadInput.toString(), stack, 0, thisTr.getgRule());
        
        do{
            
                if(current == states[0]){
                    stack.push(current.getTransitions().get(0).getPush().charAt(0));
                }
                else if(current == states[1]){
                    if(null == unreadInput.get(0)){
                        System.out.println("String is not in language.");
                        return;
                    }
                    else switch (unreadInput.get(0)) {
                        case 'a':
                            unreadInput.remove(0);
                            thisTr = current.getTransitions().get(0); 
                            break;
                        case 'b':
                            unreadInput.remove(0);
                            thisTr = current.getTransitions().get(1);
                            break;
                        case '$':
                            unreadInput.remove(0);
                            thisTr = current.getTransitions().get(2);
                            break;
                        default:
                            System.out.println("String is not in language.");
                            return;
                    }
                }
                else if(current == states[2]){
                    if(stack.peek() == 'a'){
                        stack.pop();
                        thisTr = current.getTransitions().get(0);
                    }
                    else if(stack.peek() == 'S'){
                        stack.pop();
                        thisTr = current.getTransitions().get(1);
                        stack.push(thisTr.getPush().charAt(2));
                        stack.push(thisTr.getPush().charAt(1));
                        stack.push(thisTr.getPush().charAt(0));
                    }
                }
                else if(current == states[3]){
                    if(stack.peek() == 'b'){
                        stack.pop();
                        thisTr = current.getTransitions().get(0);
                    }
                    else if(stack.peek() == 'S'){
                        stack.pop();
                        thisTr = current.getTransitions().get(1);
                    }
                }
                else if(current == states[4]){
                    if(stack.isEmpty() && unreadInput.isEmpty()){
                     display(current, unreadInput.toString(), stack, thisTr.getRuleNum(), thisTr.getgRule());
                    }
                    else{
                        System.out.println("String not in language.");
                    }
                }
                else{
                    System.out.println("String not in language");
                    return;
                }
                display(current, unreadInput.toString(), stack, thisTr.getRuleNum(), thisTr.getgRule());
                current = thisTr.getDestination();
        }while(!unreadInput.isEmpty() || !stack.isEmpty() || !current.isFinalState());
        
        display(current, unreadInput.toString(), stack, thisTr.getRuleNum(), thisTr.getgRule());
    }     
    private static void display(State s, String input, Stack<Character> stack, int rule, String gRule) {

        // Setup unread inputs.
        String unreadInput = input;

        if(!stack.isEmpty()){
            System.out.println(String.format("%15s %15s %60s %60s %25s %25s", step, s.getStateID(), unreadInput, stack.toString(), rule, gRule));
        }
        else{
            System.out.println(String.format("%15s %15s %60s %60s %25s %25s", step, s.getStateID(), unreadInput, "e", rule, gRule));
        }

        step++; // Increments each time display is called.
    }
    private static void createStates(){
        State p = new State("p");
        State q = new State("q");
        State qa = new State("qa");
        State qb = new State("qb");
        State q$ = new State("q$");
        
        p.addNewStates("e", "e", "S", q, 1, "");
        
        q.addNewStates("a", "e", "e", qa, 2, "");
        q.addNewStates("b", "e", "e", qb, 4, "");
        q.addNewStates("$", "e", "e", q$, 6, "");
        
        qa.addNewStates("e", "a", "e", q, 3, "");
        qa.addNewStates("e", "S", "aSb", qa, 7, "S -> aSb");
        
        qb.addNewStates("e", "b", "e", q, 5, "");
        qb.addNewStates("e", "S", "e", qb, 8, "S -> e");
        
        q$.changeStateStatus();
        
        states[0] = p;
        states[1] = q;
        states[2] = qa;
        states[3] = qb;
        states[4] = q$;
    }
    private static boolean validInput(String s){ // checks if inputted string is valid
        boolean x;
        int aCount = 0;
        int bCount = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != 'a' && s.charAt(i) != 'b' && s.charAt(i) != '$'){ // are the characters valid?
                return false;
            }
            if(s.charAt(i) == 'a'){ //are the number of a's and b's equal?
                aCount++;
            }
            if(s.charAt(i) == 'b'){
                bCount++;
            }
        }
        return aCount == bCount;
    }
    
}