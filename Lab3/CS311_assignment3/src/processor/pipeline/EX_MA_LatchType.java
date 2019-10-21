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
	
	public EX_MA_LatchType()
	{
		MA_enable = false;
		isLoad = false;
		isStore = false;
		WriteAddr = 70000;
		isEnd = false;
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

}
