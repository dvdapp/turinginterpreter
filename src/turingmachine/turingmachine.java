package turingmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

public class turingmachine {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("/home/david/eclipse-workspace/turinginterpreter/input.txt")); 

		Integer inputTapeCounter = 0;
		
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		Set<State> states = new TreeSet<State>();
		Map<Integer, char[]> inputs = new HashMap<Integer, char[]>();
		
		char[] type = new char[1];

		//file parsing
		while (in.ready()) {
			type = Character.toChars(in.read());
			in.read();
			

			switch(type[0]) {
			case 't':
				char[] tchars = in.readLine().toCharArray();
				transitions.add(new Transition(tchars[0],tchars[2],tchars[4],tchars[6],tchars[8]));
				states.add(new State(tchars[0]));
				break;
			case 'f':
				//TODO: Deal with multiple final states
				char[] finalStates = in.readLine().toCharArray();
				states.add(new State(finalStates[0], true));
				break;
			case 'i':
				//input tape
				char[] inputTape = in.readLine().toCharArray();
				if (!inputs.containsValue(inputTape))
					inputs.put(inputTapeCounter, inputTape);
				inputTapeCounter++;
				break;
			}
		}
		in.close();
		
		//turing machine logic

		//transition logic
		for (int i=0;i<transitions.size();i++){
			System.out.println(transitions.get(i).show());
		}
		
		//state logic
		Iterator<State> statarator = states.iterator();
		while(statarator.hasNext()){
			System.out.println(((State)statarator.next()).show());
		}
		
		//input tapes logic
		for (int i=0;i<inputs.size();i++){
			System.out.println(inputs.get(i));
		}
		
	}
	

}
