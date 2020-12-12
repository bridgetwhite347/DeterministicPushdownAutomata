# DeterministicPushdownAutomata
Final project for Theory of Computation CISC4080.  This program takes in a string and determines if the string exists in language L {a^n b^n | n >= 0}.
Deterministic Pushdown Automata Model in Java
A push-down automata which can recognize the context-
free language L={a^n b^n | n ≥ 0}.

Overview
This project uses an object oriented approach to implement a deterministic pushdown automata for the context-free language L={a^n b^n | n ≥ 0}.  The PDA can look-ahead one step.

Method
Determine the look ahead rules of the language.

Construct a state class and a transition class that can be used to define these rules.
The transition class includes 
String input -- expected input for rule
String pop -- element to be popped from the stack
String push -- element to be pushed to the stack
State destination -- the next state for the next transition
int ruleNum -- number of the transition rule
String gRule -- grammar rule if there is one
The state class includes
ArrayList of transitions -- all transitions for a given state
boolean stateEnd -- indicates if the state is the final state
String stateID -- name of the state, used to print
Develop a loop that can iterate through user input, determine which rule should be used, and implement that rule.
Takes in user input using the Java Scanner class.
Uses a do while loop that iterates through while stack is not empty, there is still unread input, and current state is not the final state.
Uses if/else to check for the correct transition.
Runs display function at the end of each iteration.

Conclusion
Java was not the most efficient way to implement a deterministic pushdown automata.
Python would have offered a simpler way to implement a deterministic pushdown automata for this language by using a dictionary.
Debugging
The stack toString function prints the top of the array at the end rather than the beginning.  This was confusing when I first ran the code and tried to fix a problem that wasn’t there.
Differentiating between the stack functions .get(0) and .peek() was difficult.  Determined that .peek() was better to use to access the top of the stack because .get(0) ran into errors when the stack was empty while .peek() did not.
Why an object oriented approach?
Object Oriented approach is intuitive because of all the distinct aspects of a language like alphabet, transitions, and states.  
