package turingmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class turingmachine {

	public static void main(String[] args) throws IOException {
		String inputFile = "/home/david/eclipse-workspace/turinginterpreter/input.txt";
		String outputFile = "/home/david/eclipse-workspace/turinginterpreter/output.txt";
		
		BufferedReader in = new BufferedReader(new FileReader(inputFile)); 
		PrintWriter out = new PrintWriter(new FileWriter(outputFile)); 
		
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
				String line = in.readLine();
				List<String> tchars = Arrays.asList(line.split(" "));
				Integer moveHead = 0;
				
				switch (tchars.get(4)) {
				case "R":
					moveHead = 1;
					break;
				case "L":
					moveHead = -1;
					break;
				default:
					moveHead = 0;
				}
				
				State newCurrentState = new State(Integer.parseInt(tchars.get(0)));
				State newNextState = new State(Integer.parseInt(tchars.get(2)));
				states.add(newCurrentState);
				transitions.add(new Transition(newCurrentState,tchars.get(1).charAt(0),newNextState,tchars.get(3).charAt(0),moveHead));
				break;
			case 'f':
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
				char[] tempInputTape = in.readLine().toCharArray();
				char[] inputTape = new char[tempInputTape.length+1]; //+2 for binary dec
				//inputTape[0] = 'Z'; //for binary dec purposes
				for (int i=0;i<tempInputTape.length;i++){
					inputTape[i] = tempInputTape[i];
				}
				inputTape[tempInputTape.length] = 'Z';
				inputs.add(new InputTape(inputTape));
				break;
			}
		}
		in.close();
		
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
					
					if (currentState.isFinalState)
						inputs.get(i).output = "Accepted";
					
					j = -1;					
				}
			}
			
		}
		
		for (int i=0;i<inputs.size();i++){
			System.out.println(i+1+") "+inputs.get(i).show());
			out.println(i+1+") "+inputs.get(i).show());
		}
		out.close();

	}


}
