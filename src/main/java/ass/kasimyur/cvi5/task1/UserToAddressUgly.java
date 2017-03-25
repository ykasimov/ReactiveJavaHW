package ass.kasimyur.cvi5.task1;

public class UserToAddressUgly {
	
	/**
	 * @param user can be null
	 * @return address
	 */
	public static String userToStreet(User user) {
		if(user != null) {
			Address addr = user.getAddress();
			if(addr != null) {
				String street = addr.getStreet();
				if(street != null) {
					return street;
				}
			}
		}
		throw new IllegalArgumentException("Invalid user!");
	}
	
}
