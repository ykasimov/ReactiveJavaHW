package ass.kasimyur.cvi5.task3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task3 {
	
	public static void main(String[] args) {
		List<Integer> numbers = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		System.out.println(sumEvenNumbers(numbers));
		System.out.println(sumOddNumbers(numbers));
		System.out.println(mulEvenNumbers(numbers));
		
		// TODO: use genericAggregate and obtain same results as with three different functions above
	}
	
	// TODO: fill this in!
	/*private static int genericAggregate(List<Integer> nums, SomeFunction aggregation, SomeFunction filter) {
		return 0;
	}*/

	private static int sumEvenNumbers(List<Integer> numbers) {
		int acc = 0;
		for(int i : numbers) {
			if(i % 2 == 0) {
				acc += i;
			}
		}
		return acc;
	}
	
	private static int sumOddNumbers(List<Integer> numbers) {
		int acc = 0;
		for(int i : numbers) {
			if(i % 2 != 0) {
				acc += i;
			}
		}
		return acc;
	}
	
	private static int mulEvenNumbers(List<Integer> numbers) {
		int acc = 1;
		for(int i : numbers) {
			if(i % 2 == 0) {
				acc *= i;
			}
		}
		return acc;
	}
	
}
