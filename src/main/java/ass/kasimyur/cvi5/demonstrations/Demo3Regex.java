package ass.kasimyur.cvi5.demonstrations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javaslang.Tuple;

public class Demo3Regex {
	
	private static final String EMAIL_PATTERN = "([a-zA-Z._]+)@([a-zA-Z._]+)";

	public static void main(String[] args) throws IOException {
		Pattern pat = Pattern.compile(EMAIL_PATTERN);
		
		Files.lines(Paths.get("emails.txt"))
		.map(line -> pat.matcher(line))
		.filter(matcher -> matcher.find())
		.map(matcher -> Tuple.of(matcher.group(1), matcher.group(2)))
		.forEach(System.out::println);
	}
	
}
