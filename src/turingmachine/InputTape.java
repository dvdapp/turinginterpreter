package turingmachine;

public class InputTape {
	public char[] inputTape;
	public String output;
	
	public InputTape() {}
	
	public InputTape(char[] inputTape){
		this.inputTape = new char[inputTape.length];
		for (int i=0;i<inputTape.length;i++){
			this.inputTape[i] = inputTape[i];
		}
		this.output = "Rejected";
	}
	
	public InputTape(char[] inputTape, String output) {
		this.inputTape = new char[inputTape.length];
		for (int i=0;i<inputTape.length;i++){
			this.inputTape[i] = inputTape[i];
		}
		this.output = output;
	}
	
	public String show() {
		String displayTape = "";
		for (int i=0;i<this.inputTape.length-1;i++){
			displayTape += this.inputTape[i];
		}
		return displayTape +"\n"+ this.output;
	}
}
