package turingmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class turingmachine {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("/home/david/eclipse-workspace/turingmachine/input.txt")); 

		//old stuff
//		String text = "";
//		int temp = 0;
//		int thisState = 0;
//		char[] type = new char[1];
//		char[] inputSymbol = new char[1];
//		int nextState = 0;
//		char[] moveHead = new char[1];
//		ArrayList<State> states = new ArrayList<State>();
		
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		TreeSet<State> states = new TreeSet<State>();
		
		char[] type = new char[1];

		//file parsing
		while (in.ready()) {
			type = Character.toChars(in.read());
			in.read();

			switch(type[0]) {
			case 't':
				char[] arr = in.readLine().toCharArray();
				transitions.add(new Transition(arr[0],arr[2],arr[4],arr[6],arr[8]));
				states.add(new State(arr[0]));
				break;
			case 'f':
				//states.add(new State(Character.toChars(in.read())[0],'Z'));
				states.add(new State(Character.toChars(in.read())[0], true));
				break;
			case 'i':
				//input tape
				break;

			}
		}
		in.close();
	
		//check that everything is there
		System.out.println("Transitions:");
		for (int i=0;i<transitions.size();i++){
			System.out.println(transitions.get(i).show());
		}
		System.out.println("States:");
		Iterator statarator = states.iterator();
		//for (int i=0;i<states.size();i++){
		while(statarator.hasNext()){
			System.out.println(((State)statarator.next()).show());
		}
		
		//turing machine logic
		
	}
	

}
