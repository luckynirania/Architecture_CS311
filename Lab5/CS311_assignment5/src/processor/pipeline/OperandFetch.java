package processor.pipeline;

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
			String insStr = Integer.toBinaryString(IF_OF_Latch.getInstruction());
			if(IF_OF_Latch.getInstruction() < 0) {
				while(insStr.length() < 32) insStr = "1" + insStr;
			}
			else {
				while(insStr.length() < 32) insStr = "0" + insStr;
			}

			int opcode,rs1,rs2,rd,imm;
			String op = insStr.substring(0, 5);
			opcode = Integer.parseInt(op,2);
			rs1 = 70000;
			rs2 = 70000;
			rd = 70000;
			imm = 70000;
			if(opcode == 0) {
				rs1 = containingProcessor.getRegisterFile().getValue(Integer.parseInt(insStr.substring(5, 10),2));
				rs2 = containingProcessor.getRegisterFile().getValue(Integer.parseInt(insStr.substring(10, 15),2));
				rd = Integer.parseInt(insStr.substring(15, 20),2);
				imm = 70000;
			}
			else if(0 < opcode && opcode < 22) {
				if(opcode % 2 == 0) {
					rs1 = containingProcessor.getRegisterFile().getValue(Integer.parseInt(insStr.substring(5, 10),2));
					rs2 = containingProcessor.getRegisterFile().getValue(Integer.parseInt(insStr.substring(10, 15),2));
					rd = Integer.parseInt(insStr.substring(15, 20),2);
					imm = 70000;
				}
				else {
					rs1 = containingProcessor.getRegisterFile().getValue(Integer.parseInt(insStr.substring(5, 10),2));
					rs2 = 70000;
					rd = Integer.parseInt(insStr.substring(10, 15),2);
					imm = Integer.parseInt(insStr.substring(15, 32),2);
				}
			}
			else {
				if(opcode == 24) {
					rs1 = 70000;
					rs2 = 70000;
					rd = Integer.parseInt(insStr.substring(5, 10),2);
					imm = Integer.parseInt(insStr.substring(10, 32),2);
					if(insStr.substring(10, 32).charAt(0) == '1') {
						imm = imm - 4194304;
					}
				}
				else if(opcode != 29) {
					rs1 = containingProcessor.getRegisterFile().getValue(Integer.parseInt(insStr.substring(5, 10),2));
					rs2 = 70000;
					rd = Integer.parseInt(insStr.substring(10, 15),2);
					imm = Integer.parseInt(insStr.substring(15, 32),2);
					if(insStr.substring(15, 32).charAt(0) == '1') {
						imm = imm - 131072;
					}
				}
				else {
					rs1 = 70000;
					rs2 = 70000;
					rd = 70000;
					imm = 70000;
				}
			}
			System.out.println("OF " + IF_OF_Latch.insPC);// + "\t" + insStr + "\t" + op + "\t" + rs1 + "\t" + rs2 + "\t" + rd + "\t" + imm);
			OF_EX_Latch.opcode = op;
			OF_EX_Latch.rs1 = rs1;
			OF_EX_Latch.rs2 = rs2;
			OF_EX_Latch.rd = rd;
			OF_EX_Latch.imm = imm;
			OF_EX_Latch.insPC = IF_OF_Latch.insPC;
			
			//TODO
			
			IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

}
