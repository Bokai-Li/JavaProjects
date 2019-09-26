package a1adept;

import java.util.Scanner;

public class A1Adept {
	
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
		
	}
	
	public static void process(Scanner s) {
		// Put your code here.
		String first_name, last_name;
		String letter_grade = "N/A";
		String[] output;
		double[] assignments,aGrades;
		double aTotal,aGTotal;
		int studentCount=0;
		int aCount = 0;
		double assignment_grade, participation_grade, midterm, final_exam, weighted_average;
		double maxPPoint, rawPPoint;
		aTotal = aGTotal = 0;
		
		s = new Scanner(System.in);
		
		aCount = s.nextInt();
		assignments = new double[aCount];
		aGrades = new double[aCount];
		for(int i = 0;i < aCount;i++)
		{
			assignments[i] = s.nextDouble();
			aTotal= aTotal + assignments[i];
		}
		
		maxPPoint = s.nextDouble();
		studentCount=s.nextInt();
		output = new String[studentCount];
		
		for(int i = 0;i < studentCount;i++)
		{
			first_name=s.next();
			String first_initial=Character.toString(first_name.charAt(0)) + ".";
			last_name=s.next();
			
			rawPPoint=s.nextDouble();
			participation_grade = 100 * (rawPPoint / (maxPPoint * 0.8));
			
			participation_grade = (participation_grade > 100) ? 100 : participation_grade;
			aGTotal=0;
			for(int i1 = 0;i1 < aCount;i1++)
			{
				aGrades[i1] = s.nextDouble();
				aGTotal= aGTotal + aGrades[i1];
			}
			
			assignment_grade =100*(aGTotal)/(aTotal);
	
			midterm=s.nextDouble();
			final_exam=s.nextDouble();
			
			weighted_average = assignment_grade*0.4 + participation_grade*0.15 +
								midterm*0.2 +final_exam*0.25;
			switch((int)weighted_average)
			{
				case 94:case 95:case 96:case 97:case 98:case 99:case 100:
					letter_grade = "A";
					break;
				case 90:case 91:case 92:case 93:
					letter_grade = "A-";
					break;
				case 86:case 87:case 88:case 89:
					letter_grade = "B+";
					break;
				case 83:case 84:case 85:
					letter_grade = "B";
					break;
				case 80:case 81:case 82:
					letter_grade = "B-";
					break;
				case 76:case 77:case 78:case 79:
					letter_grade = "C+";
					break;
				case 73:case 74:case 75:
					letter_grade = "C";
					break;
				case 70:case 71:case 72:
					letter_grade = "C-";
					break;
				case 65:case 66:case 67:case 68:case 69:
					letter_grade = "D+";
					break;
				case 60:case 61:case 62:case 63:case 64:
					letter_grade = "D";
					break;
			}
			if(weighted_average < 60)
			{
				letter_grade = "F";
			}
			output[i]=first_initial+" "+ last_name + " " + letter_grade;
		}
		for(int i = 0;i < studentCount;i++)
		{
			System.out.println(output[i]);
		}
	}

}
