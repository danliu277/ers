package ers;

import static org.junit.Assert.*;

import org.junit.Test;

import ers.data.DataFacade;

public class ReimbGen {
	@Test
	public void test() {
		try(DataFacade facade = new DataFacade()) {
			int amount = 1000;
			String des = "I want money";
			User user = facade.findUser("Employee1", "password");
			int type = 4;
			for(int i = 0; i < 5; i++) {
				facade.addReimbursement(amount + i, des, null, user.getUserId(), type);
			}
			user = facade.findUser("Employee2", "password");
			for(int i = 0; i < 5; i++) {
				facade.addReimbursement(amount + i, des, null, user.getUserId(), type);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
