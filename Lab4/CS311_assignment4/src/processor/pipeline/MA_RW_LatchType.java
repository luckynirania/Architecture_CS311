package processor.pipeline;

public class MA_RW_LatchType {
	
	boolean RW_enable;
	int aluResult;
	int WriteAddr;
	int LoadAddr;
	boolean isEnd;
	boolean isLoad;
	int currentIns;
	String currentop;
	boolean Nop;
	int LoadContent;

	String opcode;
	String imm;
	String rs1;
	String rs2;
	String rd;
	
	public MA_RW_LatchType()
	{
		RW_enable = false;
		isEnd = false;
		isLoad = false;
		currentIns = -1;
		currentop = "11111";
		imm = "0000";
		rs1 = "00";
		rs2 = "00";
		rd = "00";
		Nop = false;
		LoadContent = 70000;

	}


	public boolean isNop() {
		return Nop;
	}

	public void setNop(boolean b) {
		this.Nop = b;
	}
	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setisLoad(boolean val) {
		this.isLoad = val;
	}

	public boolean getisLoad() {
		return isLoad;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}

	public void setLoadAddr(int a) {
		this.LoadAddr = a;
	}
	public int getLoadAddr() {
		return LoadAddr;
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
