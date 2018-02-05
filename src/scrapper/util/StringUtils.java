package scrapper.util;

public class StringUtils {
	public static String trimCommas(String input){
		StringBuilder builder = new StringBuilder("");
		String numerals = "0123456789.";
		
		for(int i=0; i<input.length(); i++){
			if(numerals.indexOf(input.charAt(i)) > -1){
				builder.append(input.charAt(i));
			}
		}		
		return builder.toString();
	}
}
