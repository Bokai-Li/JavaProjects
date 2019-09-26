package a2novice;

import java.util.Scanner;

public class A2Novice {
	public static void  main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);
	}
	
	public static String pixelValueToCharacter(int pixelNumber)
	{
		if(pixelNumber<=9)
			return "#";
		else if(pixelNumber <= 19)
			return "M";
		else if(pixelNumber <= 29)
			return "X";
		else if(pixelNumber <= 39)
			return "D";
		else if(pixelNumber <= 49)
			return "<";
		else if(pixelNumber <= 59)
			return ">";
		else if(pixelNumber <= 69)
			return "s";
		else if(pixelNumber <= 79)
			return ":";
		else if(pixelNumber <= 89)
			return "-";
		else 
			return " ";
		
		
	}
	
	
	public static void process(Scanner s)
	{
		int[][] pixelnum;
		int min, max; 
		double average;
		int width, height;
		
		double total = 0.0;
		
		width = s.nextInt();
		height = s.nextInt();
		
		pixelnum = new int[width][height];

		
		pixelnum[0][0] = s.nextInt();
		min = pixelnum[0][0];
		max = pixelnum[0][0];
		total = pixelnum[0][0];
				
		for(int col = 0; col < width; col++)
			for(int row = 0; row < height; row++) {
				if(!(col == 0 && row == 0)) {
					pixelnum[col][row] = s.nextInt();
					total = total + pixelnum[col][row];
					if(pixelnum[col][row] < min)
						min = pixelnum[col][row];
					if(pixelnum[col][row] > max)
						max = pixelnum[col][row];
				}
			}
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				System.out.print(pixelValueToCharacter(pixelnum[col][row]));
			}
			System.out.println();
		}
		average = (total) / (width*height);
		System.out.println("Min value = " + min);
		System.out.println("Max value = " + max);
		System.out.println("Average value = " + average);
		
		
	}	
}
