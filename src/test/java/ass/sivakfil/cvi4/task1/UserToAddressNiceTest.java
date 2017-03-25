package ass.sivakfil.cvi4.task1;

import java.util.Optional;

import org.junit.Assert;
import org.testng.annotations.Test;

import ass.kasimyur.cvi5.task1.Address;
import ass.kasimyur.cvi5.task1.User;
import ass.kasimyur.cvi5.task1.UserToAddressNice;

public class UserToAddressNiceTest {

	@Test
	public void userToStreet() {
		Assert.assertFalse(UserToAddressNice.userToStreet(null).isPresent());
		Assert.assertFalse(UserToAddressNice.userToStreet(new User(null)).isPresent());
		Assert.assertFalse(UserToAddressNice.userToStreet(new User(new Address(null))).isPresent());
		
		Optional<String> addr = UserToAddressNice.userToStreet(new User(new Address("addr")));
		Assert.assertTrue(addr.isPresent());
		Assert.assertEquals("addr", addr.get());
	}
}
