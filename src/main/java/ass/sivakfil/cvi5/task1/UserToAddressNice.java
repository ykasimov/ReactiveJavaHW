package ass.sivakfil.cvi5.task1;

import java.util.Optional;

public class UserToAddressNice {

	public static Optional<String> userToStreet(User user) {

		return Optional.ofNullable(user).map(User::getAddress).map(Address::getStreet);

		// HINT:
		/* Optional.ofNullable(user).m... */
	}
	
}
