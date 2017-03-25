package ass.kasimyur.cvi5.demonstrations;

import java.util.Optional;

public class Demo1 {
	public static void main(String[] args) {
		// Optional of null is Empty
		Optional<String> opt = Optional.ofNullable(null);
		System.out.println(opt.isPresent());
		System.out.println(opt);
		// When optional maps to null, it turns into Empty
		System.out.println(Optional.of("ahoj")
		.map(m -> null)
		.orElse("N/A"));
	}
}
