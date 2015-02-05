package ckCommonUtils;

public class CKDebugPrinting
{
	private static boolean verbose=true;
	
	public static void setVerbosePrinting(boolean val)
	{
		verbose=val;
	}
	
	public static void println(String S)
	{
		if(verbose)
		{
			System.out.println(S);
		}
	}
}
