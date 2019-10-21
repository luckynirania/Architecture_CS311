package generic;

import java.io.*;
import java.util.Currency;

// import com.sun.org.apache.bcel.internal.generic.Instruction;

import generic.Operand.OperandType;
import generic.Instruction.OperationType;


public class Simulator {

	public static byte[] intToFourByteArray(int i) {
  		byte[] result = new byte[4];
		result[0] = (byte) (i >> 24);
		result[1] = (byte) (i >> 16);
		result[2] = (byte) (i >> 8);
		result[3] = (byte) (i /*>> 0*/);
  		return result;
	}
		
	static FileInputStream inputcodeStream = null;

	public static int firstCodeAddress;

	public static byte[] getByteByString(String binaryString) {
		int splitSize = 8;
	
		if(binaryString.length() % splitSize == 0){
			int index = 0;
			int position = 0;
	
			byte[] resultByteArray = new byte[binaryString.length()/splitSize];
			StringBuilder text = new StringBuilder(binaryString);
	
			while (index < text.length()) {
				String binaryStringChunk = text.substring(index, Math.min(index + splitSize, text.length()));
				Integer byteAsInt = Integer.parseInt(binaryStringChunk, 2);
				resultByteArray[position] = byteAsInt.byteValue();
				index += splitSize;
				position ++;
			}
			return resultByteArray;
		}
		else{
			System.out.println("Cannot convert binary string to byte[], because of the input length. '" +binaryString+"' % 8 != 0");
			return null;
		}
	}
	
	public static void setupSimulation(String assemblyProgramFile)
	{	
		firstCodeAddress = ParsedProgram.parseDataSection(assemblyProgramFile);
		ParsedProgram.parseCodeSection(assemblyProgramFile, firstCodeAddress);
		ParsedProgram.printState();
	}	
	
	public static void assemble(String objectProgramFile)
	{
		//TODO your assembler code
		Instruction cur_ins = new Instruction();
		System.out.println(ParsedProgram.code.size());

		FileOutputStream out = null;
		byte[] dump = new byte[4];

		try {
			//1. open the objectProgramFile in binary mode
			out = new FileOutputStream(objectProgramFile);
			

			//2. write the firstCodeAddress to the file
			dump = intToFourByteArray(firstCodeAddress);
			out.write(dump, 0, 4);


			//3. write the data to the file
			for(int i = 0;i < ParsedProgram.data.size();i++) {
				dump = null;
				dump = intToFourByteArray(ParsedProgram.data.get(i));
				out.write(dump, 0, 4);
			}


			//4. assemble one instruction at a time, and write to the file
			String opcode = new String();
			String rs1 = new String();
			String rs2 = new String();
			String rd = new String();
			String imm = new String();
			String ins = new String();
	
			for(int i = 0;i < ParsedProgram.code.size();i++) {
				cur_ins = ParsedProgram.getInstructionAt(firstCodeAddress + i);

				switch(cur_ins.getOperationType()) {
					//R3I type
					case add : 
					case sub : 
					case mul : 
					case div : 
					case and : 
					case or : 
					case xor : 
					case slt : 
					case sll : 
					case srl : 
					case sra : {
						opcode = Integer.toBinaryString(cur_ins.operationType.ordinal());
						while(opcode.length() != 5) opcode = "0" + opcode;
						rs1 = Integer.toBinaryString(cur_ins.sourceOperand1.value);
						while(rs1.length() != 5) rs1 = "0" + rs1;

						rs2 = Integer.toBinaryString(cur_ins.sourceOperand2.value);
						while(rs2.length() != 5) rs2 = "0" + rs2;

						rd = Integer.toBinaryString(cur_ins.destinationOperand.value);
						while(rd.length() != 5) rd = "0" + rd;
						
						ins = opcode + rs1 + rs2 + rd + "000000000000";
						System.out.print(cur_ins.operationType.toString() + " ");
						System.out.println(ins);

						dump = getByteByString(ins);
						out.write(dump);
						dump = null;

						break;
						
					}


					//R2I type
					case addi :
					case subi :
					case muli :
					case divi : 
					case andi : 
					case ori : 
					case xori : 
					case slti : 
					case slli : 
					case srli : 
					case srai :
					case load :
					case store : {
						opcode = Integer.toBinaryString(cur_ins.operationType.ordinal());
						while(opcode.length() < 5) opcode = "0" + opcode;

						rs1 = Integer.toBinaryString(cur_ins.sourceOperand1.value);
						while(rs1.length() < 5) rs1 = "0" + rs1;
						
						int tem = cur_ins.sourceOperand2.value;
						System.out.print("tem ");
						System.out.println(tem);
						if(tem >= 0) {
							imm = Integer.toBinaryString(tem);
							while(imm.length() < 17) imm = "0" + imm;
						}
						else {
							imm = Integer.toBinaryString(-1 * tem);
							while(imm.length() < 17) imm = "1" + imm;
						}
						System.out.println(imm);
						

						rd = Integer.toBinaryString(cur_ins.destinationOperand.value);
						while(rd.length() < 5) rd = "0" + rd;
						
						ins = opcode + rs1 + rd + imm;
						System.out.print(cur_ins.operationType.toString() + " ");
						System.out.println(ins);
						
						dump = getByteByString(ins);
						out.write(dump);
						dump = null;

						break;
					}
					case beq : 
					case bne : 
					case blt : 
					case bgt : {
						opcode = Integer.toBinaryString(cur_ins.operationType.ordinal());
						while(opcode.length() < 5) opcode = "0" + opcode;

						rs1 = Integer.toBinaryString(cur_ins.sourceOperand1.value);
						while(rs1.length() < 5) rs1 = "0" + rs1;

						rd = Integer.toBinaryString(cur_ins.sourceOperand2.value);
						while(rd.length() < 5) rd = "0" + rd;

						int dest = ParsedProgram.symtab.get(cur_ins.destinationOperand.labelValue);
						int src = firstCodeAddress + i;
						int diff = dest - src;
						if(diff > 0) {
							String temp = new String();
							imm = Integer.toBinaryString(diff);
							while(imm.length() < 17) imm = "0" + imm;
							System.out.print("temp ");
							System.out.println(temp);
						}
						else {
							String temp = new String();
							imm = Integer.toBinaryString(-1*diff);
							while(imm.length() < 17) imm = "1" + imm;
							System.out.print("temp ");
							System.out.println(temp);
						}
						System.out.println(imm);
						
						ins = opcode + rs1 + rd + imm;
						System.out.print(cur_ins.operationType.toString() + " ");
						System.out.println(ins);
						
						dump = getByteByString(ins);
						out.write(dump);
						dump = null;

						break;
					}

					case jmp : {
						opcode = Integer.toBinaryString(cur_ins.operationType.ordinal());
						while(opcode.length() < 5) opcode = "0" + opcode;

						rd = "00000";

						int dest = ParsedProgram.symtab.get(cur_ins.destinationOperand.labelValue);
						int src = firstCodeAddress + i;
						int diff = dest - src;
						if(diff > 0) {
							String temp = new String();
							imm = Integer.toBinaryString(diff);
							while(imm.length() < 17) imm = "0" + imm;
							System.out.print("temp ");
							System.out.println(temp);
						}
						else {
							String temp = new String();
							imm = Integer.toBinaryString(-1*diff);
							while(imm.length() < 22) imm = "1" + imm;
							System.out.print("temp ");
							System.out.println(temp);
						}
						System.out.println(imm);
						
						ins = opcode + rd + imm;
						
						dump = getByteByString(ins);
						System.out.print(cur_ins.operationType.toString() + " ");
						System.out.println(ins);
						out.write(dump);
						dump = null;

						break;
					}

					case end :	{
						ins = "11101000000000000000000000000000";
						System.out.print(cur_ins.operationType.toString() + " ");
						System.out.println(ins);

						dump = getByteByString(ins);
						out.write(dump);
						dump = null;

						break;
					}

					default: Misc.printErrorAndExit("unknown instruction!!");
					
				}

			}


			//5. close the file
			out.close();
		}
		catch (IOException e) {
            Misc.printErrorAndExit(e.toString());
        }	
		
		
	}
	
}
