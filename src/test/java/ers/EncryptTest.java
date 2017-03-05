package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class EncryptTest {

	@Test
	public void test() {
		Connection conn = null;
		try{
			// transactions are wrapped into the connection
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false); // enables transaction, turns autocommit off
			
			PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO ERS_USERS VALUES"
					+ "(1, 'Employee6', ?, '5th', 'Emp', 'email6', 1)");
			String hashed = BCrypt.hashpw("password", BCrypt.gensalt());
			stmt1.setString(1, hashed);
			stmt1.executeUpdate();
			conn.commit(); // Everything was successful
			
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
