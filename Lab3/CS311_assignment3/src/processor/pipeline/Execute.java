package processor.pipeline;

import processor.Processor;

public class Execute {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performEX() {
		if(OF_EX_Latch.isEX_enable()) {
			System.out.println("in exft " + OF_EX_Latch.getopcode());
			int aluResult = 0;
			// if(OF_EX_Latch.getrs1() == "") OF_EX_Latch.rs1 = "000";
			// if(OF_EX_Latch.getrs2() == "") OF_EX_Latch.rs2 = "000";
			// if(OF_EX_Latch.getrd() == "") OF_EX_Latch.rd = "000";
			// if(OF_EX_Latch.getimm() == "") OF_EX_Latch.imm = "000";
			int rs1val = containingProcessor.getRegisterFile().getValue(Integer.parseInt(OF_EX_Latch.getrs1(),2));
			int rs2val = containingProcessor.getRegisterFile().getValue(Integer.parseInt(OF_EX_Latch.getrs2(),2));
			int rdval = containingProcessor.getRegisterFile().getValue(Integer.parseInt(OF_EX_Latch.getrd(),2));
			System.out.println(OF_EX_Latch.getimm()); 
			int immval = (short)Integer.parseInt(OF_EX_Latch.getimm(),2);
			int WriteAddr = 70000;
			System.out.println("in execute " + " " + rs1val + " " + rs2val + " " + rdval + " " + immval);
			switch(OF_EX_Latch.getopcode()) {
				case "00000" : {
					aluResult = rs1val + rs2val;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "00001" : {
					aluResult = rs1val + Integer.parseInt(OF_EX_Latch.getimm(),2);
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "00010" : {
					aluResult = rs1val - rs2val;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "00011" : {
					aluResult = rs1val - Integer.parseInt(OF_EX_Latch.getimm(),2);
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "00100" : {
					aluResult = rs1val * rs2val;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "00101" : {
					aluResult = rs1val * Integer.parseInt(OF_EX_Latch.getimm(),2);
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "00110" : {
					if(rs2val != 0)	{
						aluResult = rs1val / rs2val;
						containingProcessor.getRegisterFile().setValue(31, rs1val % rs2val);
						WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					}
					break;
				}
				case "00111" : {
					if(immval != 0)	{
						aluResult = rs1val / Integer.parseInt(OF_EX_Latch.getimm(),2);
						containingProcessor.getRegisterFile().setValue(31, rs1val % immval);
						WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					}
					break;
				}
				case "01000" : {
					aluResult = rs1val & rs2val;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01001" : {
					aluResult = rs1val & Integer.parseInt(OF_EX_Latch.getimm(),2);
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01010" : {
					aluResult = rs1val | rs2val;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01011" : {
					aluResult = rs1val | Integer.parseInt(OF_EX_Latch.getimm(),2);
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01100" : {
					aluResult = rs1val ^ rs2val;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01101" : {
					aluResult = rs1val ^ Integer.parseInt(OF_EX_Latch.getimm(),2);
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01110" : {
					if(rs2val > rs1val) aluResult = 1;
					else aluResult = 0;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}
				case "01111" : {
					if(rs2val > rs1val) aluResult = 1;
					else aluResult = 0;
					WriteAddr = Integer.parseInt(OF_EX_Latch.getrd(),2);
					break;
				}

				case "10000" : {
					aluResult = rs1val << rs2val;
					String q = Integer.toBinaryString(rs1val);
					while(q.length() != 5) q = "0" + q;
					String x31 = q.substring(5-rs2val, 5);
					containingProcessor.getRegisterFile().setValue(31, Integer.parseInt(x31,2));
					break;
				}
				case "10001" : {
					aluResult = rs1val << Integer.parseInt(OF_EX_Latch.getimm(),2);
					String q = Integer.toBinaryString(immval);
					while(q.length() != 5) q = "0" + q;
					String x31 = q.substring(5-immval, 5);
					containingProcessor.getRegisterFile().setValue(31, Integer.parseInt(x31,2));
					break;
				}
				case "10010" : {
					aluResult = rs1val >>> rs2val;
					String q = Integer.toBinaryString(rs1val);
					while(q.length() != 5) q = "0" + q;
					String x31 = q.substring(0, rs2val);
					containingProcessor.getRegisterFile().setValue(31, Integer.parseInt(x31,2));
					break;
				}
				case "10011" : {
					aluResult = rs1val >>> Integer.parseInt(OF_EX_Latch.getimm(),2);
					String q = Integer.toBinaryString(immval);
					while(q.length() != 5) q = "0" + q;
					String x31 = q.substring(0, immval);
					containingProcessor.getRegisterFile().setValue(31, Integer.parseInt(x31,2));
					break;
				}
				case "10100" : {
					aluResult = rs1val >> rs2val;
					String q = Integer.toBinaryString(rs1val);
					while(q.length() != 5) q = "0" + q;
					String x31 = q.substring(0, rs2val);
					containingProcessor.getRegisterFile().setValue(31, Integer.parseInt(x31,2));
					break;
				}
				case "10101" : {
					aluResult = rs1val >> Integer.parseInt(OF_EX_Latch.getimm(),2);
					String q = Integer.toBinaryString(immval);
					while(q.length() != 5) q = "0" + q;
					String x31 = q.substring(0, immval);
					containingProcessor.getRegisterFile().setValue(31, Integer.parseInt(x31,2));
					break;
				}

				case "10110" : {
					aluResult = containingProcessor.getMainMemory().getWord(rs1val + Integer.parseInt(OF_EX_Latch.getimm(),2));
					EX_MA_Latch.setisLoad(true);
					EX_MA_Latch.setLoadAddr(Integer.parseInt(OF_EX_Latch.getrd(),2));
					System.out.println(rs1val + Integer.parseInt(OF_EX_Latch.getimm(),2) + " " + aluResult + " " + EX_MA_Latch.getLoadAddr());
					break;
				}
				case "10111" : {
					aluResult = rs1val;
					EX_MA_Latch.setisStore(true);
					EX_MA_Latch.StoreAddr = rdval + Integer.parseInt(OF_EX_Latch.getimm(),2);
					break;
				}

				case "11000" : {
					EX_IF_Latch.setjmpjmpAddr(rdval + immval);
					break;
				}
				case "11001" : {
					if(rs1val == rdval) EX_IF_Latch.setjmpjmpAddr(immval);
					break;
				}
				case "11010" : {
					if(rs1val != rdval) EX_IF_Latch.setjmpjmpAddr(immval); 
					break;
				}
				case "11011" : {
					if(rs1val < rdval) EX_IF_Latch.setjmpjmpAddr(immval); 
					break;
				}
				case "11100" : {
					if(rs1val > rdval) EX_IF_Latch.setjmpjmpAddr(immval); 
					break;
				}
				case "11101" : {
					EX_MA_Latch.isEnd = true;
					break;
				}
				default : break;

			}
			EX_MA_Latch.WriteAddr = WriteAddr;
			EX_MA_Latch.aluResult = aluResult;

			OF_EX_Latch.setEX_enable(false);
			EX_MA_Latch.setMA_enable(true);
		}
		//TODO
	}

}
