package com.jasonv.tictactoe;

import junit.framework.TestCase;

public class AutoPlayer extends TestCase {
	public void testBoard13()
	{
		int j=0;
		for(long n=123456789;n<999999999;n++)
		{
			if(n%10000000==0)
			{
				System.out.println(n);
			}

			if(hasNoRepeateDigits(n))
			{
				j++;
//				System.out.println(n + " : " + j);
//				if(j>10)
//				{
//					break;
//				}
			}
		}
		System.out.println("done:" + j);
	}

	public void testBoard14()
	{
		System.out.println(hasNoRepeateDigits(123456791));
	}
	private boolean hasNoRepeateDigits(long n)
	{
		String s = Long.toString(n);
		int found[] = new int[s.length()+1];
		int num[] = new int[s.length()];
		for(int i=0;i<s.length();i++)
		{
			num[i]=Integer.parseInt(s.substring(i,i+1));
			if(num[i]==0)
			{
				return false;
			}
			found[i]=0;
		}
		
		for(int i=0;i<s.length();i++)
		{
			int index = num[i];
			found[index]++;
			if(found[index]>1)
			{
				return false;
			}
		}
		return true;	
	}

}
