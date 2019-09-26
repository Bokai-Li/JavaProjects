package a2adept;

import java.util.Scanner;

public class A2Adept {
	public static void  main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);
	}
	public static int[] countBin(int[] ranges, int[] pixels)
	{
		int[] count = new int[ranges.length];
		for(int k = 0; k<pixels.length;k++)
		{
			for(int g = 0; g < ranges.length-1; g++)
				if(pixels[k]>=ranges[g] && pixels[k]<ranges[g+1])
					count[g]++;
			if (pixels[k]>ranges[ranges.length-1])
				count[ranges.length-1]++;
		}
		
		return count;
	}
	public static int[] calculatePercentage(int[] count, int totalCount)
	{
		int[] percentageOutput = new int[count.length];
		double fraction;
		for(int i=0; i < count.length; i++)
		{
			fraction = (double)count[i]/totalCount;
			percentageOutput[i]= (int) (fraction* 100+ 0.5);
		}
		
		
		return percentageOutput;
	}
	public static void process(Scanner s)
	{
		int bin_width, width, height, numOfRanges;
		int[] divideNums;//array of bin numbers
		int[] input;
		int[] rangeCount;//array of count of numbers in each range
		int[] percentage;
		bin_width = s.nextInt();
		width = s.nextInt();
		height = s.nextInt();
		
		input = new int[width*height];
		percentage = new int[width*height];
		
		for(int j=0; j< input.length;j++)
		{
			input[j] = s.nextInt();
		}
		
		numOfRanges = (int) Math.ceil(100.0/bin_width);
		divideNums = new int[numOfRanges];
		for(int i = 0; i < numOfRanges; i++)
		{
			divideNums[i] = bin_width * i;
		}
		
		rangeCount = countBin(divideNums, input);
		percentage = calculatePercentage(rangeCount, width*height);
		
		for(int i = 0; i < divideNums.length; i++)
		{
			if(divideNums[i]<10)
				System.out.print(" " + divideNums[i] +":");
			else
				System.out.print(divideNums[i] +":");
			
			for(int p=0; p<percentage[i];p++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		
	}
}
