package ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ers.User;
import ers.UserRole;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}
	
	public String findPassword(String username) throws Exception {
		String sql = "SELECT ERS_PASSWORD FROM ERS_USERS WHERE ERS_USERNAME=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			return rs.getString("ERS_PASSWORD");
		}
		return null;
	}
	
	public User findUser(String username) throws Exception {
		String sql = "SELECT ERS_USER_ID AS ID, "
				+ "ERS_USERNAME AS USERNAME, "
				+ "ERS_PASSWORD AS PASSWORD, "
				+ "USER_FIRST_NAME AS FIRST_NAME, "
				+ "USER_LAST_NAME AS LAST_NAME, "
				+ "USER_EMAIL AS EMAIL, "
				+ "USER_ROLE_ID AS ROLE_ID, "
				+ "USER_ROLE AS ROLE FROM ERS_USERS "
				+ "INNER JOIN ERS_USER_ROLES"
				+ " ON ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USER_ROLE_ID"
				+ " WHERE ERS_USERS.ERS_USERNAME=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		User user = new User();
		while(rs.next()) {
			user.setUserId(rs.getInt("ID"));
			user.setUserName(username);
			user.setPassword(rs.getString("PASSWORD"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setEmail(rs.getString("EMAIL"));
			UserRole role = new UserRole(rs.getInt("ROLE_ID"), rs.getString("ROLE"));			
			user.setRole(role);
		}
		return user;
	}
}
