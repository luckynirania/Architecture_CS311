package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	
	public RegisterWrite(Processor containingProcessor, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			
			//TODO
			if(MA_RW_Latch.isNop()==true){

			}
			else{
				if(MA_RW_Latch.WriteAddr != 70000) {
					containingProcessor.getRegisterFile().setValue(MA_RW_Latch.WriteAddr, MA_RW_Latch.aluResult);
				}
				if(MA_RW_Latch.isLoad == true) {
					MA_RW_Latch.isLoad = false;
					// System.out.println("in memacc cond " + EX_MA_Latch.getLoadAddr() + " " + EX_MA_Latch.aluResult);
					containingProcessor.getRegisterFile().setValue(MA_RW_Latch.getLoadAddr(), MA_RW_Latch.LoadContent);
				}
				if(MA_RW_Latch.isEnd == true) {
					Simulator.setSimulationComplete(true);
				} 
				// else IF_EnableLatch.setIF_enable(true);

				System.out.println("RW " + MA_RW_Latch.currentIns + " " + MA_RW_Latch.opcode + " " + MA_RW_Latch.rs1 + " " + MA_RW_Latch.rs2 + " " + MA_RW_Latch.rd + " " + MA_RW_Latch.imm);
				// if instruction being processed is an end instruction, remember to call Simulator.setSimulationComplete(true);
				
				// MA_RW_Latch.setRW_enable(false);
			}
		}
	}

}
