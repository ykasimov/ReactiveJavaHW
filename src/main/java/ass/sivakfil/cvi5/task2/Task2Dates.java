package ass.sivakfil.cvi5.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

import javaslang.Tuple;
import javaslang.control.Try;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Task2Dates {
	
	public static final String DATE_TIME_PATTERN = "EEE, MM. dd. YYYY hh:mm:ss Z";
	
	public static void main(String[] args) throws IOException {
		DateTimeFormatter format = 
				DateTimeFormat.forPattern(DATE_TIME_PATTERN)
				.withLocale(Locale.US);

		// task: handle errors with javaslang Try.of and return most recent date using Joda time date1.isAfter(date2)
		
		Files.lines(Paths.get("dates.txt"))
		.map(line -> DateTime.parse(line, format))
		.forEach(System.out::println);
		
	}
	
}
