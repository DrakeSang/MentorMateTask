package task;

import java.util.Scanner;
import mentorMateLogo.Logo;

public class MentorMateLogo {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter odd number:");
		if(keyboard.hasNextInt()) {
			int logoWidth = keyboard.nextInt();
			if(logoWidth % 2 == 0) {
				System.out.println("You entered even number.");
			}else {
				Logo mentorMate = new Logo(logoWidth);
				String sb = mentorMateLogo(logoWidth, mentorMate);
				System.out.println(sb);
			}
		}else {
			System.out.println("You did not enter number.");
		}
		
		keyboard.close();
	}
	
	public static String mentorMateLogo(int n, Logo logo) {
		StringBuilder sb = new StringBuilder();
		
		String dash = "-";
		String star = "*";
		
		// Printing top line
		logo.createTopOrBottomLine(
				sb, 
				dash,
				star);
		// After creating any line i have to decrease the initial width by 1,but i
		// keep the unchanged original width in another variable.
		// On the left side of the logo the dashes decrease by one from the 
		// initial width until they reach 1.
		
		logo.setWidth(logo.getWidth() - 1);
		
		for(int i = 2; i < logo.getOriginalWidth() + 1; i += logo.getOriginalWidth()/2) {
			// I need to know how many times i will print and what is the step.
			// I need to enter the for cycle always two times. 
			// I start from 2, check if 2 is lesser than the initial width plus 1
			// and the step must be the initial width divided by 2.
			// If width is 3 : 2 < 4 it enters and the step is 3 / 2 = 1
			// then it checks again 3 < 4 it enters and then it goes out
			// If width is 5 : 2 < 6 it enters and the step is 5 / 2 = 2
			// then it checks again 4 < 6 it enters and then it goes out
			// If width is 7: 2 < 8 it enters and the step is 7 / 2 = 3
			// then it checks again 5 < 8 it enters and then it goes out.
			if(i == 2) { 
				// i check if the i is 2 to know that i am on the first set of 
				// middle lines.
				// If the width is 3 i need to print 1 line
				// If the width is 5 i need to print 2 lines
				// If the width is 7 i need to print 3 lines
				// I see that i can make for cycle starting from one until
				// original width divided by 2 and incrementing by 1
				// If i have width 3 : 1 <= 1 it enters and step is 1
				// After incrementing 2 is not <= 1 so it goes out
				// If i have width 5 : 1 <= 2 it enters and then incrementing by 1
				// and it checks again 2 <= 2 it enters and then goes out
				for(int j = 1; j <= logo.getOriginalWidth() / 2; j++) {
					logo.setMiddleLinesAverageNumber(logo.getMiddleLinesAverageNumber() - 2);
					logo.setStartingIncrementingValue(logo.getStartingIncrementingValue() + 2);
					logo.setStartingDecreasingValue(logo.getStartingDecreasingValue() -2);
					logo.printFirstMiddleLines(sb);
					logo.setFinalIncrementingValue(logo.getStartingIncrementingValue());
					logo.setFinalDecreasingValue(logo.getStartingDecreasingValue());
					logo.setWidth(logo.getWidth() -1);
					sb.append("\n");
				}
			}else {
				// Printing the next middle lines.
				// I get i from upper for cycle,because the second times it 
				// enters it is the starting position i need.
				// If width is 3 the second times it enters is 3 (2 + (3 / 2))
				// If width is 5 the second time it enters is 4 (2 + 5 / 2))
				// If width is 7 the second time it enters is 5 (2 + 7 / 2))
				// I have starting position and the end position is the initial 
				// width and the step is 1				
				for(int j = i; j <= logo.getOriginalWidth(); j++) {
					logo.setMiddleLinesAverageNumber(logo.getMiddleLinesAverageNumber() - 2);
					logo.printSecondMiddleLines(sb);
					logo.setFinalDecreasingValue(logo.getFinalDecreasingValue() + 2);
					logo.setFinalIncrementingValue(logo.getFinalIncrementingValue() -2);
					logo.setWidth(logo.getWidth() -1);
					sb.append("\n");
				}
			}
		}
		
		//Printing bottom line
		logo.createTopOrBottomLine(
				sb,
				star,
				dash);
		
		return sb.toString();
		
	}
}