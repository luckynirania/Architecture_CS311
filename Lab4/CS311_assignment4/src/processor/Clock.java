package processor;

public class Clock {
	static long currentTime = 0;
	static long num_stall = 0;
	static long num_wrongpath = 0;
	public static void incrementClock()
	{
		currentTime++;
	}
	
	public static long getCurrentTime()
	{
		return currentTime;
	}

	public static void incrementstall()
	{
		num_stall++;
	}
	
	public static long getnumstall()
	{
		return num_stall;
	}
	public static void incrementwrongpath()
	{
		num_wrongpath++;
	}
	
	public static long getwrongpath()
	{
		return num_wrongpath;
	}
}
