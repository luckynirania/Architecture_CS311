package processor.pipeline;

import processor.Processor;

public class MemoryAccess {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	
	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
	}
	
	public void performMA()
	{
		if(EX_MA_Latch.isMA_enable()) {
			int aluResult = EX_MA_Latch.aluResult;
			int rs1 = EX_MA_Latch.rs1;
			int rs2 = EX_MA_Latch.rs2;
			int rd = EX_MA_Latch.rd;
			int imm = EX_MA_Latch.imm;
			String opcode = EX_MA_Latch.opcode;

			if(opcode.equals("10110")) { //load
				MA_RW_Latch.isLoad = true;
			}
			if(opcode.equals("10111")) {  //store
				containingProcessor.getMainMemory().setWord(aluResult, rs1);
			}

			MA_RW_Latch.insPC = EX_MA_Latch.insPC;
			System.out.println("MA " + EX_MA_Latch.insPC);

			MA_RW_Latch.aluResult = aluResult;
			MA_RW_Latch.rs1 = rs1;
			MA_RW_Latch.rs2 = rs2;
			MA_RW_Latch.rd = rd;
			MA_RW_Latch.imm = imm;
			MA_RW_Latch.opcode = opcode;
			EX_MA_Latch.setMA_enable(false);
			MA_RW_Latch.setRW_enable(true);
		}
		//TODO
	}

}
