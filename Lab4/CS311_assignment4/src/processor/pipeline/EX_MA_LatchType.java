package processor.pipeline;

public class EX_MA_LatchType {
	
	boolean MA_enable;
	int aluResult;
	boolean isLoad;
	boolean isStore;
	int LoadAddr;
	int StoreAddr;
	boolean isWrite;
	int WriteAddr;
	boolean isEnd;
	int currentIns;
	String currentop;
	boolean Nop;

	String opcode;
	String imm;
	String rs1;
	String rs2;
	String rd;
	
	public EX_MA_LatchType()
	{
		MA_enable = false;
		isLoad = false;
		isStore = false;
		WriteAddr = 70000;
		isEnd = false;
		currentIns = -1;
		currentop = "11111";
		imm = "0000";
		rs1 = "00";
		rs2 = "00";
		rd = "00";
		Nop = false;

	}
	public boolean isNop() {
		return Nop;
	}

	public void setNop(boolean b) {
		this.Nop = b;
	}
	public boolean isMA_enable() {
		return MA_enable;
	}

	public void setMA_enable(boolean mA_enable) {
		MA_enable = mA_enable;
	}

	public void setisLoad(boolean val) {
		this.isLoad = val;
	}

	public boolean getisLoad() {
		return isLoad;
	}

	public void setisStore(boolean val) {
		this.isStore = val;
	}

	public boolean getisStore() {
		return isStore;
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
