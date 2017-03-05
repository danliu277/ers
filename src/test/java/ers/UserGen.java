package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class UserGen {

	@Test
	public void test() {
		Connection conn = null;
		try{
			// transactions are wrapped into the connection
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false); // enables transaction, turns autocommit off
			
			PreparedStatement stmt1 = null;
			String userName = null;
			String firstName = null;
			String email = null;
			String hashed = null;
			for(int i = 1; i <= 2; i++) {
				if(i == 1)
					firstName = "1st";
				else
					firstName = "2nd";
				userName = "Manager" + String.valueOf(i);
				email = "email" + String.valueOf(i);
				stmt1 = conn.prepareStatement("INSERT INTO ERS_USERS VALUES"
						+ "(1, ?, ?, ?, 'Man', ?, 1)");
				hashed = BCrypt.hashpw("password", BCrypt.gensalt());
				stmt1.setString(1, userName);
				stmt1.setString(2, hashed);
				stmt1.setString(3, firstName);
				stmt1.setString(4, email);
				stmt1.executeUpdate();
				conn.commit(); // Everything was successful
			}
			for(int i = 1; i <=6; i++) {
				if(i == 1)
					firstName = "1st";
				else if(i == 2)
					firstName = "2nd";
				else if(i == 3)
					firstName = "3rd";
				else
					firstName = String.valueOf(i) + "th";
				userName = "Employee" + String.valueOf(i);
				email = "email" + String.valueOf(i + 2);
				stmt1 = conn.prepareStatement("INSERT INTO ERS_USERS VALUES"
						+ "(1, ?, ?, ?, 'Emp', ?, 2)");
				hashed = BCrypt.hashpw("password", BCrypt.gensalt());
				stmt1.setString(1, userName);
				stmt1.setString(2, hashed);
				stmt1.setString(3, firstName);
				stmt1.setString(4, email);
				stmt1.executeUpdate();
				conn.commit(); // Everything was successful
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
