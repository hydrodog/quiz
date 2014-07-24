import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexExample {
	public static void main(String[] args) {
		String something="";
		HashMap<String,String> tokens = new HashMap<String,String>();
		tokens.put("sin", something);
		tokens.put("cos", something);
		tokens.put("tan", something);
		
//		String s3 = "x y+2 * tan";
		String s3 = "-x y+2 * tan";
		String[] tokens1 = s3.split("\\W+");
		for (int i = 0; i < tokens1.length; i++)
			System.out.println(tokens1[i]);
		
		String s2 = "tan x - sin(x) + sqrt(1.2) / [a12412726]*2 + [x]*[y]-[z]/4";

		String s = "[a12412726]*2 + [x]*[y]-[z]/4";
//		Pattern pattern = Pattern.compile("\\[(\\w+)\\]");

		String[] symbols = {"sin", "cos", "tan", "sqrt"};
		String[] operators = {"+", "-", "*", "/"};
		String ored = symbols[0];
		for (int i = 1; i < symbols.length; i++)
			ored += "|" + symbols[i];
		System.out.println(ored);
		Pattern pattern = Pattern.compile("\\w+|\\+|\\-|\\*|\\/|sin|cos|tan|");
		Matcher m = pattern.matcher(s);
		//System.out.println(m.)
//		for (int i = 1; i < m.groupCount(); i++)
	//		System.out.println(m.group(i));
	}
}
