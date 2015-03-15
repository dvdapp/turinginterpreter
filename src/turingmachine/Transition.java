package turingmachine;

public class Transition {
	public State currentState;
	public char inputSymbol;
	public State nextState;
	public char writeSymbol;
	public int moveHead;
	
	public Transition(State currentState,char inputSymbol,State nextState,char writeSymbol,int moveHead) {
		this.currentState = currentState;
		this.inputSymbol = inputSymbol;
		this.nextState = nextState;
		this.writeSymbol = writeSymbol;
		this.moveHead = moveHead;
	}
	
	public String show() {
		return "("+this.currentState.show()+")"+", "+this.inputSymbol+", "+"("+this.nextState.show()+")"+", "+this.writeSymbol+", "+this.moveHead;
	}

}
