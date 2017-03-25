package ass.kasimyur.cvi5.task1;

import java.util.Arrays;
import java.util.List;

public class UserRepo {
	public List<User> findUsers() {
		return Arrays.asList(
				new User(new Address("21 Street")),
				new User(new Address(null)),
				new User(null),
				null
		);
	}
}
