package ers;

import static org.junit.Assert.*;

import org.junit.Test;

import ers.data.DataFacade;

public class FacadeTest {

	/*@Test
	public void findUser() {
		try(DataFacade facade = new DataFacade()) {
			System.out.println(facade.findUser("Manager1", "password"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}*/
	
	/*@Test
	public void createReimbursement() {
		try(DataFacade facade = new DataFacade()) {
			facade.addReimbursement(1000, "I want money", null, 
					facade.findUser("Employee1", "password"), 4);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}*/
	
	@Test
	public void updateReimbursement() {
		try(DataFacade facade = new DataFacade()) {
			facade.updateReimbursement(1, facade.findUser("Manager1", "password").getUserId(), 2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/*@Test
	public void getUserReimbursement() {
		try(DataFacade facade = new DataFacade()) {
			User user = facade.findUser("Employee1", "password");
			System.out.println(facade.findUserReimbursement(user));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}*/
	
	/*@Test
	public void getAllReimbursement() {
		try(DataFacade facade = new DataFacade()) {
			System.out.println(facade.findAllReimbursement());
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}*/
	
	/*@Test
	public void filterReimbursement() {
		try(DataFacade facade = new DataFacade()) {
			System.out.println(facade.filterReimbursement(1));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}*/
}
