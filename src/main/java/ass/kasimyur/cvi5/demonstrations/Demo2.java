package ass.kasimyur.cvi5.demonstrations;

import java.io.IOException;
import java.util.stream.IntStream;

import javaslang.control.Try;

public class Demo2 {

	public static void main(String[] args) {
		IntStream.range(0, 10).boxed()
		.map(i -> Try.of(() -> incEven(i)))
		.forEach(System.out::println);
	}
	
	public static int incEven(int i) throws IOException {
		if(i % 2 == 0) {
			return i+1;
		}else {
			throw new IOException("Number is odd!");
		}
	}
	
}
