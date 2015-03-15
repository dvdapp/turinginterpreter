package turingmachine;

public class Transition {
	public char currentState;
	public char inputSymbol;
	public char nextState;
	public char writeSymbol;
	public char moveHead;
	
	public Transition(char currentState,char inputSymbol,char nextState,char writeSymbol,char moveHead) {
		this.currentState = currentState;
		this.inputSymbol = inputSymbol;
		this.nextState = nextState;
		this.writeSymbol = writeSymbol;
		this.moveHead = moveHead;
	}
	
	public String show() {
		return this.currentState+", "+this.inputSymbol+", "+this.nextState+", "+this.writeSymbol+", "+this.moveHead;
	}

}
