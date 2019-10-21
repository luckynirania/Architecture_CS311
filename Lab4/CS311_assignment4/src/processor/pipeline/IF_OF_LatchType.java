package processor.pipeline;

public class IF_OF_LatchType {
	
	boolean OF_enable;
	int instruction;
	int currentIns;
	String currentop;
	
	public IF_OF_LatchType()
	{
		OF_enable = false;
		currentIns = -1;
		currentop = "11111";
	}

	public boolean isOF_enable() {
		return OF_enable;
	}

	public void setOF_enable(boolean oF_enable) {
		OF_enable = oF_enable;
	}

	public int getInstruction() {
		return instruction;
	}

	public void setInstruction(int instruction) {
		this.instruction = instruction;
	}

	public int getcurrentIns() {
		return currentIns;
	}

	public void setcurrentIns(int instruction) {
		this.currentIns = instruction;
	}

}
