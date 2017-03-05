package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class HashTest {

	@Test
	public void test() {
		Connection conn = null;
		try{
			// transactions are wrapped into the connection
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false); // enables transaction, turns autocommit off
			
			PreparedStatement stmt1 = conn.prepareStatement("SELECT ERS_PASSWORD FROM ERS_USERS WHERE "
					+ " ERS_USERNAME=?");
			String user = "Employee3";
			stmt1.setString(1, user);
			stmt1.executeQuery();
			ResultSet rs = stmt1.executeQuery();
			
			while(rs.next()) {
				if (BCrypt.checkpw("password", rs.getString("ERS_PASSWORD")))
				    System.out.println("It matches");
				else
				    System.out.println("It does not match");
			}
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
