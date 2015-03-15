package turingmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class turingmachine {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("/home/david/eclipse-workspace/turinginterpreter/input.txt")); 

		ArrayList<Transition> transitions = new ArrayList<Transition>();
		TreeSet<State> states = new TreeSet<State>();
		ArrayList<InputTape> inputs = new ArrayList<InputTape>();

		char[] type = new char[1];

		//file parsing
		while (in.ready()) {
			type = Character.toChars(in.read());
			in.read();

			switch(type[0]) {
			case 't':
				char[] tchars = in.readLine().toCharArray();
				Integer moveHead = 0;
				switch (tchars[8]) {
				case 'R':
					moveHead = 1;
					break;
				case 'L':
					moveHead = -1;
					break;
				default:
					moveHead = 0;
				}
				State newCurrentState = new State(tchars[0]);
				State newNextState = new State(tchars[4]);
				states.add(newCurrentState);
				transitions.add(new Transition(newCurrentState,tchars[2],newNextState,tchars[6],moveHead));
				break;
			case 'f':
				//TODO: Deal with multiple final states
				char[] finalStates = in.readLine().toCharArray();
				states.add(new State(finalStates[0], true));
				break;
			case 'i':
				//input tape
				char[] tempInputTape = in.readLine().toCharArray();
				char[] inputTape = new char[tempInputTape.length+1];
				for (int i=0;i<tempInputTape.length;i++){
					inputTape[i] = tempInputTape[i];
				}
				inputTape[tempInputTape.length] = 'Z';
				inputs.add(new InputTape(inputTape));
				break;
			}
		}
		in.close();

		//turing machine logic
		
		//print transition just because
//		for (int i=0;i<transitions.size();i++){
//			System.out.println(transitions.get(i).show());
//		}
		
		//loop through all input tapes, and accept/reject in place
		for (int i=0;i<inputs.size();i++){

			char[] inputTape = inputs.get(i).inputTape; //get the first input tape
			int headPoint = 0; //head points at first element 
			State currentState = states.first(); //firstState
			
			for (int j=0;j<transitions.size();j++){
				
				if (transitions.get(j).currentState.equals(currentState)
						&& transitions.get(j).inputSymbol == inputTape[headPoint]){
					
					currentState = transitions.get(j).nextState;
					inputTape[headPoint] = transitions.get(j).writeSymbol;
					headPoint += transitions.get(j).moveHead;
					
					if (transitions.get(j).moveHead == 0) {
						inputs.get(i).output = "Accepted";
					}
					
					j = -1;					
				}
			}
			
		}
		
		
		for (int i=0;i<inputs.size();i++){
			System.out.println(inputs.get(i).show());
		}

	}


}
