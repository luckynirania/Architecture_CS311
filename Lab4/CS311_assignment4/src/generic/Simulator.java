package generic;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import processor.Clock;
import processor.Processor;

public class Simulator {
		
	static Processor processor;
	static boolean simulationComplete;
	
	
	public static void setupSimulation(String assemblyProgramFile, Processor p) throws FileNotFoundException
	{
		Simulator.processor = p;
		loadProgram(assemblyProgramFile);
		
		simulationComplete = false;
	}
	
	static void loadProgram(String assemblyProgramFile) throws FileNotFoundException
	{
		System.out.println("inside load program "+assemblyProgramFile);
		DataInputStream instr = new DataInputStream(new BufferedInputStream(new FileInputStream(assemblyProgramFile)));
		try{
			int n=instr.readInt();
			int i;
			for(i=0;i<n;i++){
				int temp = instr.readInt();
				processor.getMainMemory().setWord(i,temp);
			}
			int pc =i;
			processor.getRegisterFile().setProgramCounter(pc);

			while(instr.available()>0){
				int temp = instr.readInt();
				processor.getMainMemory().setWord(i,temp);
				i++;
			}
			processor.getRegisterFile().setValue(0,0);
			processor.getRegisterFile().setValue(1,65535);
			processor.getRegisterFile().setValue(2,65535);

			instr.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/*
		 * TODO
		 * 1. load the program into memory according to the program layout described
		 *    in the ISA specification
		 * 2. set PC to the address of the first instruction in the main
		 * 3. set the following registers:
		 *     x0 = 0
		 *     x1 = 65535
		 *     x2 = 65535
		 */
	}
	
	public static void simulate()
	{
		int i = 0;
		while(simulationComplete == false)
		{
			processor.getRWUnit().performRW();
			if(simulationComplete == true) break;
			processor.getMAUnit().performMA();
			processor.getEXUnit().performEX();
			processor.getOFUnit().performOF();
			processor.getIFUnit().performIF();
			System.out.println( i + " --------------------------------------------------------------");
			Clock.incrementClock();
			//if(i==10)break;
			i++;
		}
		
		// TODO
		Statistics stat = new Statistics();
		System.out.println("clock value ---------------" + i);
		System.out.println("num of stalls "+Clock.getnumstall());
		System.out.println("num of wrong path "+Clock.getwrongpath());
		stat.setNumberOfCycles((int)Clock.getCurrentTime());
		stat.setNumberOfInstructions((int)Clock.getCurrentTime()/5);
		// set statistics
	}
	
	public static void setSimulationComplete(boolean value)
	{
		simulationComplete = value;
	}
}
