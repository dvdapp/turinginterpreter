package turingmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class turingmachine {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("/home/david/eclipse-workspace/turinginterpreter/input2.txt")); 

		ArrayList<Transition> transitions = new ArrayList<Transition>();
		TreeSet<State> states = new TreeSet<State>();
		ArrayList<InputTape> inputs = new ArrayList<InputTape>();
		List<String> finalStates = new ArrayList<String>();

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
				State newCurrentState = new State(Character.getNumericValue(tchars[0]));
				State newNextState = new State(Character.getNumericValue(tchars[4]));
				states.add(newCurrentState);
				//states.add(newNextState);
				transitions.add(new Transition(newCurrentState,tchars[2],newNextState,tchars[6],moveHead));
				break;
			case 'f':
				//TODO: Deal with multiple final states
				String tempFinalStates = in.readLine();
				finalStates = Arrays.asList(tempFinalStates.split(" "));
				for (int i=0;i<finalStates.size();i++){
					State newFinalState = new State(Integer.parseInt(finalStates.get(i)), true);
					for (int j=0;j<transitions.size();j++){
						if(transitions.get(j).nextState.equals(newFinalState))
							transitions.get(j).nextState = newFinalState;
					}
					states.add(newFinalState);
				}
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
		for (int i=0;i<transitions.size();i++){
			System.out.println(transitions.get(i).show());
		}
		
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
					
					//System.out.println(currentState.show());
					
					if (currentState.isFinalState)
						inputs.get(i).output = "Accepted";
					
//					if (transitions.get(j).moveHead == 0) {
//						inputs.get(i).output = "Accepted";
//					}
					
					j = -1;					
				}
			}
			
		}
		
		Iterator<State> statarator = states.iterator();
		while(statarator.hasNext()){
			System.out.println(statarator.next().show());				
		}
		
		for (int i=0;i<inputs.size();i++){
			System.out.println(inputs.get(i).show());
		}

	}


}
