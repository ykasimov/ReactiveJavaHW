package ass.kasimyur.cvi5.hw5;

import io.reactivex.Observable;
import org.joda.time.DateTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Record {

	protected DateTime time;
	protected int telephone;
	final static Pattern pattern = Pattern.compile("\\d+");

	public static void main(String[] args) throws IOException {
		Stream<String> txtStream = Files.lines(Paths.get("data.txt"));
		Observable.fromIterable(txtStream::iterator).takeWhile(pattern);
	}


}
