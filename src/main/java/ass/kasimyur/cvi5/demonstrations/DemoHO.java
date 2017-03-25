package ass.kasimyur.cvi5.demonstrations;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class DemoHO {
	
	public static void main(String[] args) throws Exception {
		
		Predicate<Integer> isOdd = (i) -> (i % 2) != 0;
		Function<Integer, Integer> inc = (i) -> i + 1;
		BiFunction<Integer, Integer, Integer> add = (a,b) -> a + b;
		
		System.out.println("isOdd: " + isOdd.test(1));
		System.out.println("inc: " + inc.apply(1));
		System.out.println("add: " + add.apply(1,1));
		
		// partial application
		Function<Integer,Integer> inc2 = (y) -> add.apply(y, 1);
		System.out.println("inc2: " + inc2.apply(1));
		
		
		// following two lines are equivalent
		System.out.println("3x inc: " + inc.apply(inc.apply(inc.apply(0))));
		System.out.println("3x inc: " + inc.andThen(inc).andThen(inc).apply(0));
	}
	
}
