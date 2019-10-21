package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean EX_enable;
	String opcode;
	String imm;
	String rs1;
	String rs2;
	String rd;

	
	public OF_EX_LatchType() {
		EX_enable = false;
		imm = "0000";
		rs1 = "00";
		rs2 = "00";
		rd = "00";
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}

	public void setopcode(String opcode) {
		this.opcode = opcode;
	}

	public String getopcode() {
		return opcode;
	}

	public void setimm(String opcode) {
		this.imm = opcode;
	}

	public String getimm() {
		return imm;
	}

	public void setrs1(String opcode) {
		this.rs1 = opcode;
	}

	public String getrs1() {
		return rs1;
	}
	public void setrs2(String opcode) {
		this.rs2 = opcode;
	}

	public String getrs2() {
		return rs2;
	}

	public void setrd(String opcode) {
		this.rd = opcode;
	}

	public String getrd() {
		return rd;
	}
	
}
