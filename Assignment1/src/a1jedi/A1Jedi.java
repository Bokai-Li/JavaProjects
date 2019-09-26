package a1jedi;

import java.util.Scanner;

public class A1Jedi {

	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);

	}

	public static void process(Scanner s) {
		// Put your code here.
		String[] first_name, last_name;
		String[] letter_grade;
		String[] output, first_initial;
		double[] assignments,aGrades;
		int[] letterGradeCount = new int[11];
		double aTotal,aGTotal;
		int studentCount=0;
		int aCount = 0;
		double[] assignment_grade, participation_grade, midterm, final_exam, weighted_average,normalizedMid, normalizedFin, curved_score_mid, curved_score_fin;
		double maxPPoint, rawPPoint;
		double totalMid, aveMid, totalFin, aveFin, stdMid, stdFin, sumstdm, sumstdf;
		
		
		aTotal = aGTotal = totalMid = totalFin = sumstdm = sumstdf = 0;
		
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
		first_name = new String[studentCount];
		last_name = new String[studentCount];
		first_initial = new String[studentCount];
		normalizedMid = new double[studentCount];
		normalizedFin = new double[studentCount];
		curved_score_mid = new double[studentCount];
		curved_score_fin = new double[studentCount];
		letter_grade = new String[studentCount];
		assignment_grade = new double[studentCount];
		participation_grade= new double[studentCount];
		midterm = new double[studentCount];
		final_exam = new double[studentCount];
		weighted_average = new double[studentCount];
		//input all the information
		for(int i = 0;i < studentCount;i++)
		{
			first_name[i]=s.next();
			first_initial[i]=Character.toString(first_name[i].charAt(0)) + ".";
			last_name[i]=s.next();
			
			rawPPoint=s.nextDouble();
			participation_grade[i] = 100 * (rawPPoint / (maxPPoint * 0.8));
			
			participation_grade[i] = (participation_grade[i] > 100) ? 100 : participation_grade[i];
			aGTotal=0;
			for(int i1 = 0;i1 < aCount;i1++)
			{
				aGrades[i1] = s.nextDouble();
				aGTotal= aGTotal + aGrades[i1];
			}
			
			assignment_grade[i] =100*(aGTotal)/(aTotal);
	
			midterm[i]=s.nextDouble();
			final_exam[i]=s.nextDouble();
			
			totalMid = totalMid + midterm[i];
			totalFin = totalFin + final_exam[i];
		}
		
		aveMid = totalMid / studentCount;
		aveFin = totalFin / studentCount;
		for(int i = 0 ; i < studentCount ; i++)
		{
			sumstdm=sumstdm+Math.pow(midterm[i]-aveMid, 2);
			sumstdf=sumstdf+Math.pow(final_exam[i]-aveFin, 2);
		}
		
		stdMid = Math.sqrt( sumstdm / studentCount);
		stdFin = Math.sqrt( sumstdf / studentCount);
		
		for(int i = 0 ; i < studentCount ; i++)
		{
			double nm, nf;
			nm = (midterm[i]-aveMid)/stdMid;
			nf = (final_exam[i]-aveFin)/stdFin;
			if(nm>1.0 && nm <2.0 && nm > -4.0)
				curved_score_mid[i] = (nm - 1.0)/(2.0 - 1.0)*(100.0 - 94.0) + 94.0;
			else if(nm>0.0)
				curved_score_mid[i] = (nm - 0.0)/(1.0 - 0.0)*(94.0 - 85.0) + 85.0;
			else if(nm>-1.0)
				curved_score_mid[i] = (nm - (-1.0))/(0.0 - (-1.0))*(85.0 - 75.0) + 75.0;
			else if(nm>-1.5)
				curved_score_mid[i] = (nm - (-1.5))/((-1.0) - (-1.5))*(75.0 - 65.0) + 65.0;
			else if(nm>-2.0)
				curved_score_mid[i] = (nm - (-2.0))/((-1.5) - (-2.0))*(65.0 - 55.0) + 55.0;
			else if(nm>-3.0)
				curved_score_mid[i] = (nm - (-3.0))/((-2.0) - (-3.0))*(55.0 - 30.0) + 30.0;
			else
				curved_score_mid[i] = (nm - (-4.0))/((-3.0) - (-4.0))*(30.0 - 0) + 30.0;

			if(nf>1.0 && nf <2.0 && nf > -4.0)
				curved_score_fin[i] = (nf - 1.0)/(2.0 - 1.0)*(100.0 - 94.0) + 94.0;
			else if(nf>0.0)
				curved_score_fin[i] = (nf - 0.0)/(1.0 - 0.0)*(94.0 - 85.0) + 85.0;
			else if(nf>-1.0)
				curved_score_fin[i] = (nf - (-1.0))/(0.0 - (-1.0))*(85.0 - 75.0) + 75.0;
			else if(nf>-1.5)
				curved_score_fin[i] = (nf - (-1.5))/((-1.0) - (-1.5))*(75.0 - 65.0) + 65.0;
			else if(nf>-2.0)
				curved_score_fin[i] = (nf - (-2.0))/((-1.5) - (-2.0))*(65.0 - 55.0) + 55.0;
			else if(nf>3.0)
				curved_score_fin[i] = (nf - (-3.0))/((-2.0) - (-3.0))*(55.0 - 30.0) + 30.0;
			else
				curved_score_fin[i] = (nf - (-4.0))/((-3.0) - (-4.0))*(30.0 - 0) + 0.0;
		
		} 
		for(int i = 0;i < studentCount;i++)
		{
			weighted_average[i] = assignment_grade[i]*0.4 + participation_grade[i]*0.15 +
					curved_score_mid[i]*0.2 +curved_score_fin[i]*0.25;
			switch((int)weighted_average[i])
			{
				case 94:case 95:case 96:case 97:case 98:case 99:case 100:
					letter_grade[i] = "A";
					break;
				case 90:case 91:case 92:case 93:
					letter_grade[i] = "A-";
					break;
				case 86:case 87:case 88:case 89:
					letter_grade[i] = "B+";
					break;
				case 83:case 84:case 85:
					letter_grade[i] = "B";
					break;
				case 80:case 81:case 82:
					letter_grade[i] = "B-";
					break;
				case 76:case 77:case 78:case 79:
					letter_grade[i] = "C+";
					break;
				case 73:case 74:case 75:
					letter_grade[i] = "C";
					break;
				case 70:case 71:case 72:
					letter_grade[i] = "C-";
					break;
				case 65:case 66:case 67:case 68:case 69:
					letter_grade[i] = "D+";
					break;
				case 60:case 61:case 62:case 63:case 64:
					letter_grade[i] = "D";
					break;
			}
			if(weighted_average[i] < 60)
			{
				letter_grade[i] = "F";
			}

		}
		 
		for(int i = 0;i < studentCount;i++)
		{
			switch(letter_grade[i])
			{
			case "A":
				letterGradeCount[0]++;
				break;
			case "A-":
				letterGradeCount[1]++;
				break;
			case "B+":
				letterGradeCount[2]++;
				break;
			case "B":
				letterGradeCount[3]++;
				break;
			case "B-":
				letterGradeCount[4]++;
				break;
			case "C+":
				letterGradeCount[5]++;
				break;
			case "C":
				letterGradeCount[6]++;
				break;
			case "C-":
				letterGradeCount[7]++;
				break;
			case "D+":
				letterGradeCount[8]++;
				break;
			case "D":
				letterGradeCount[9]++;
				break;
			case "F":
				letterGradeCount[10]++;
				break;
				
			}
		}

		System.out.println("A : " + letterGradeCount[0]);
		System.out.println("A-: " + letterGradeCount[1]);
		System.out.println("B+: " + letterGradeCount[2]);
		System.out.println("B : " + letterGradeCount[3]);
		System.out.println("B-: " + letterGradeCount[4]);
		System.out.println("C+: " + letterGradeCount[5]);
		System.out.println("C : " + letterGradeCount[6]);
		System.out.println("C-: " + letterGradeCount[7]);
		System.out.println("D+: " + letterGradeCount[8]);
		System.out.println("D : " + letterGradeCount[9]);
		System.out.println("F : " + letterGradeCount[10]);
		
		
	}


}
