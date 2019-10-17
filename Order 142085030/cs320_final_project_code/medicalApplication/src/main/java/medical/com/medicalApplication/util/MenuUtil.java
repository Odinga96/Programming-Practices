package medical.com.medicalApplication.util;

import java.util.Scanner;
/**
 * 
 * This class creates menu prompts based on prompt one and prompt two
 *
 */
public class MenuUtil {

	public static Pair createTwoItemMenu(Scanner scanner, String promptOne, String promptTwo) {
		System.out.println(promptOne);
		String one = scanner.next();

		System.out.println(promptTwo);
		String two = scanner.next();

		return new Pair(one, two);
	}

}
