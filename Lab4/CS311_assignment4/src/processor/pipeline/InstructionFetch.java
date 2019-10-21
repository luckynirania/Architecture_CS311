package processor.pipeline;

import processor.Processor;
import java.math.BigInteger;

public class InstructionFetch {
	
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performIF()
	{
		if(IF_EnableLatch.isIF_enable())
		{
			System.out.print("IF ");
			int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
			if(EX_IF_Latch.getjmpAddr() != 70000) {
				// System.out.print("Get set jmp : ");
				containingProcessor.getRegisterFile().setProgramCounter(EX_IF_Latch.getjmpAddr() + currentPC - 2);
				EX_IF_Latch.setjmpjmpAddr(70000);
			}
			currentPC = containingProcessor.getRegisterFile().getProgramCounter();
			// System.out.println("currpc " + currentPC);
			int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
			
			int inst = newInstruction;
			BigInteger z = new BigInteger("4294967296");
			if(inst < 0) {
				z = z.add(BigInteger.valueOf(inst));
				inst = z.intValue();
			}
			String ins = Integer.toBinaryString(inst);
			while(ins.length() < 32) ins = "0" + ins;
			// System.out.println(inst);
			// System.out.println(ins);

			String opcode = ins.substring(0, 5);
			System.out.println(currentPC);
			if(opcode.equals("11101")) {
				IF_EnableLatch.setIF_enable(false);
			}

			IF_OF_Latch.setInstruction(newInstruction);
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);

			IF_OF_Latch.setcurrentIns(currentPC);
			
			
			IF_OF_Latch.setOF_enable(true);
		}
	}

}
