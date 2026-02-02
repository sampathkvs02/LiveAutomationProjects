package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
	
	public static String generateBrandNewEmail() {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return LocalDateTime.now().format(formatter) + "@gmail.com";
	}
}
