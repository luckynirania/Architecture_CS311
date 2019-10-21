package processor.pipeline;

import java.math.BigInteger;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	
	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}
	
	public void performOF()
	{
		if(IF_OF_Latch.isOF_enable())
		{
			//TODO
			int inst = IF_OF_Latch.getInstruction();
			BigInteger z = new BigInteger("4294967296");
			if(inst < 0) {
				z = z.add(BigInteger.valueOf(inst));
				inst = z.intValue();
			}
			String ins = Integer.toBinaryString(inst);
			while(ins.length() < 32) ins = "0" + ins;
			System.out.println(inst);
			System.out.println(ins);

			String opcode = ins.substring(0, 5);
			String imm = "00000";
			String rs1 = "0000";
			String rs2 = "0000";
			String rd = "0000";
			int op = Integer.parseInt(opcode,2);

			if(op == 0) {
				rs1 = ins.substring(5,10);
				rs2 = ins.substring(10,15);
				rd = ins.substring(15, 20);
			}
			else if(0 < op && op < 22) {
				rs1 = ins.substring(5,10);
				if(op % 2 == 0) {
					rs2 = ins.substring(10,15);
					rd = ins.substring(15, 20);
				}
				else {
					rd = ins.substring(10,15);
					imm = ins.substring(15, 32);
				}
			}
			else if(op == 22 || op == 23 || (24 < op && op < 29)) {
				rs1 = ins.substring(5,10);
				rd = ins.substring(10,15);
				imm = ins.substring(15, 32);
			} 
			else {
				rd = ins.substring(5,10);
				imm = ins.substring(10, 32);
			}

			System.out.println(op + " " + rs1 + " " + rs2 + " " + rd + " "  + imm);



			OF_EX_Latch.setopcode(opcode);
			System.out.println("in opft " + OF_EX_Latch.getopcode());
			OF_EX_Latch.setrd(rd);
			OF_EX_Latch.setrs1(rs1);
			OF_EX_Latch.setrs2(rs2);
			OF_EX_Latch.setimm(imm);

			// System.out.println(opcode);
			// System.out.println();
			// System.out.println(rs1);
			// System.out.println();
			// System.out.println(rs2);
			// System.out.println();
			// System.out.println(rd);
			// System.out.println();
			// System.out.println(imm);
			// System.out.println("in opft " + OF_EX_Latch.getopcode());
			IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

}
