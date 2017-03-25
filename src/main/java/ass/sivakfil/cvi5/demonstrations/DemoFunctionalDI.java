package ass.sivakfil.cvi5.demonstrations;

import java.util.function.BiFunction;
import java.util.function.Function;

class User {
	protected String name;

	public User(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}

class UserDatabase {
	
	public User findUser(String name) {
		return new User(name);
	}
	
}

class DependencyInjectionContainer {
	
	public static <T,R> Function<T,R> injectUdb(BiFunction<UserDatabase, T, R> bif) {
		return (t) -> bif.apply(new UserDatabase(), t);
	}
	
}

public class DemoFunctionalDI {
	
	public static void main(String[] args) {
		// build application using DI configuration ... 
		Function<String, User> findUser = DependencyInjectionContainer.injectUdb(
				DemoFunctionalDI::findUserInDatabase
		);
		
		// use configured application resources
		System.out.println(findUser.apply("DarthVader18"));
	}
	
	public static User findUserInDatabase(UserDatabase db, String username) {
		return db.findUser(username);
	}
}
