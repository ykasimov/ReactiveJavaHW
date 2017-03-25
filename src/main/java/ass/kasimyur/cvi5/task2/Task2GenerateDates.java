package ass.kasimyur.cvi5.task2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Task2GenerateDates {
	
	public static void main(String[] args) throws IOException {
		String file = IntStream.range(0, 100)
		.boxed()
		.map(i -> RandomUtils.nextFloat(0, 1))
		.map(i -> (i < 0.7f) ? generateDate() : RandomStringUtils.randomAlphanumeric(25))
		.collect(Collectors.joining("\n"));
		
		Files.write(Paths.get("dates.txt"), file.getBytes(Charset.forName("UTF-8")));
		System.out.println("Generated");
	}
	
	public static String generateDate() {
		return new DateTime(
			RandomUtils.nextInt(1990, 2020),
			RandomUtils.nextInt(1,12),
			RandomUtils.nextInt(1,25),
			RandomUtils.nextInt(1,23),
			RandomUtils.nextInt(1,50),
			RandomUtils.nextInt(1,50)
		).toString(DateTimeFormat.forPattern(Task2Dates.DATE_TIME_PATTERN).withLocale(Locale.US));
	}
	
}
