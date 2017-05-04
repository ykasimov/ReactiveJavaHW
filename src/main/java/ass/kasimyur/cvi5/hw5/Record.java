package ass.kasimyur.cvi5.hw5;

import com.github.davidmoten.rx.Checked;
import com.github.davidmoten.rx.Transformers;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;


public class Record {

    public static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    public static final String DATE_TIME_PATTERN1 = "EEE, MM. dd. YYYY hh:mm:ss Z";
    public static final String DATE_TIME_PATTERN2 = "MM. dd. YY hh:mm";
    public static final DateTimeParser[] parsers = {DateTimeFormat.forPattern(DATE_TIME_PATTERN1).withLocale(Locale.US).getParser(),
            DateTimeFormat.forPattern(DATE_TIME_PATTERN2).withLocale(Locale.US).getParser()
    };
    public static final DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter();
    public static final String RECORD_DELIMITER = "====";

    public static void main(String[] args) throws IOException, InterruptedException {
        Stream<String> txtStream = Files.lines(Paths.get("data.txt"));
        final String outputFileName = "output.csv";

        BufferedWriter writer = getBufferedWriter(outputFileName);
        assert writer != null;
        Observable.from(txtStream::iterator)
                .compose(Transformers.toListWhile((list, line) -> list.isEmpty() || !line.equals(RECORD_DELIMITER)))
                .flatMap(list -> Observable.just(list)
                                .subscribeOn(Schedulers.io())
                                .filter(lines -> (lines.size() > 2))
                                .filter(chunk -> ((chunk.get(chunk.size() - 1).startsWith("Date:")) && chunk.get(chunk.size() - 2).startsWith("Tel.")))
                                .map(filtered -> filtered.subList(filtered.size() - 2, filtered.size()))
                                .map(Record::verifyFormatDataAndOutputOptionalString)
                                .filter(Optional::isPresent)
                                .map(i -> i.get() + "\n")

                )
                .finallyDo(() -> close(writer))
                .subscribe(Checked.a1(writer::write), Throwable::printStackTrace);
    }

    private static Optional<String> verifyFormatDataAndOutputOptionalString(List<String> input) {
        StringBuilder output = new StringBuilder();
        String date = input.get(1).replace("Date: ", "");
        try {
            output.append(formatter.parseDateTime(date));

        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }


        String phoneNumber = input.get(0).replace("Tel.: ", "");
        //for some reason the google library doesn't separate a country code from a number, if the number starts with 420
        if (phoneNumber.startsWith("420")) {
            phoneNumber = "+" + phoneNumber.replaceAll("\\s+", "");
        }
        output.append(", ");
        Phonenumber.PhoneNumber parsedNumber;
        try {
            parsedNumber = phoneNumberUtil.parse(phoneNumber, "CZ");

            output.append("+");
            output.append(parsedNumber.getCountryCode());
            output.append(parsedNumber.getNationalNumber());
        } catch (NumberParseException e) {
            return Optional.empty();
        }


        return Optional.of(output.toString());

    }

    private static void close(BufferedWriter writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedWriter getBufferedWriter(String path) {
        try {
            File outputFile = new File(path);
            return new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
