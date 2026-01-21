package temp;

import java.util.Date;

public class GenerateEmailDemo {
	public static void main(String[] args) {
		Date date = new Date();
		String StringDate = date.toString();
		String noSpaceStringDate = StringDate.replaceAll("\\s","");
		String noCollonStringDate = noSpaceStringDate.replaceAll("\\:","");
		String emailWithTimeStamp = noCollonStringDate + "@gmail.com";
		System.out.println(emailWithTimeStamp);

	}

}
