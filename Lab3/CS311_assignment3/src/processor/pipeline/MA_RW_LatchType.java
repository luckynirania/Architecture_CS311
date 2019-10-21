package processor.pipeline;

public class MA_RW_LatchType {
	
	boolean RW_enable;
	int aluResult;
	int WriteAddr;
	boolean isEnd;
	
	public MA_RW_LatchType()
	{
		RW_enable = false;
		isEnd = false;
	}

	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}

}
