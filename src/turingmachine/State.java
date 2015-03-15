package turingmachine;

public class State implements Comparable<State> {
	public char currentState;
	public boolean isFinalState;
	
	public State() {}
	
	public State (char currentState, boolean isFinalState) {
		this.currentState = currentState;
		this.isFinalState = isFinalState;
	}
	
	public State(char currentState) {
		this.currentState = currentState;
	}
	
	public String show() {
		return this.currentState +", "+ this.isFinalState;
	}
	
	@Override 
	public boolean equals(Object other) {
	    if (!(other instanceof State)) {
	      return false;
	    }
	    State otherState = (State) other;
	    return currentState == otherState.currentState;
	  }

	  @Override 
	  public int compareTo(State otherState) {
	    if (currentState < otherState.currentState) {
	      return -1;
	    }
	    return currentState == otherState.currentState ? 0 : 1;
	  }

}
