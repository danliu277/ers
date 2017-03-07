package ers.data;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ers.Reimbursement;
import ers.Status;
import ers.Type;
import ers.User;
import ers.UserRole;

public class ReimbursementDAO {
	private Connection conn;

	// Use given connection
	public ReimbursementDAO(Connection conn) {
		this.conn = conn;
	}
	
	// Returns list of reimbursement given a userId
	public List<Reimbursement> findUserReimbursement(int user) throws SQLException {
		String sql = "SELECT REIMBURSEMENT.REIMB_ID AS ID, "
				+ "REIMBURSEMENT.REIMB_AMOUNT AS AMOUNT, "
				+ "REIMBURSEMENT.REIMB_SUBMITTED AS SUBMITTED, "
				+ "REIMBURSEMENT.REIMB_RESOLVED AS RESOLVED, "
				+ "REIMBURSEMENT.REIMB_DESCRIPTION AS DESCRIPTION, "
				+ "REIMBURSEMENT.REIMB_RECEIPT AS RECEIPT, "
				+ "REIMBURSEMENT.REIMB_AUTHOR AS AUTHOR_ID, "
				+ "AUTHOR.ERS_USERNAME AS AUTHOR_USERNAME, "
				+ "AUTHOR.ERS_PASSWORD AS AUTHOR_PASSWORD, "
				+ "AUTHOR.USER_FIRST_NAME AS AUTHOR_FIRST_NAME, "
				+ "AUTHOR.USER_LAST_NAME AS AUTHOR_LAST_NAME, "
				+ "AUTHOR.USER_EMAIL AS AUTHOR_EMAIL, "
				+ "AUTHOR.USER_ROLE_ID AS AUTHOR_ROLE_ID, "
				+ "AUTHOR_ROLE.USER_ROLE AS AUTHOR_ROLE, "
				+ "REIMBURSEMENT.REIMB_RESOLVER AS RESOLVER_ID, "
				+ "RESOLVER.ERS_USERNAME AS RESOLVER_USERNAME, "
				+ "RESOLVER.ERS_PASSWORD AS RESOLVER_PASSWORD, "
				+ "RESOLVER.USER_FIRST_NAME AS RESOLVER_FIRST_NAME, "
				+ "RESOLVER.USER_LAST_NAME AS RESOLVER_LAST_NAME, "
				+ "RESOLVER.USER_EMAIL AS RESOLVER_EMAIL, "
				+ "RESOLVER.USER_ROLE_ID AS RESOLVER_ROLE_ID, "
				+ "RESOLVER_ROLE.USER_ROLE AS RESOLVER_ROLE, "
				+ "REIMBURSEMENT.REIMB_STATUS_ID AS STATUS_ID, "
				+ "STATUS.REIMB_STATUS AS STATUS, "
				+ "REIMBURSEMENT.REIMB_TYPE_ID AS TYPE_ID, "
				+ "TYPE.REIMB_TYPE AS TYPE "
				+ "FROM ERS_REIMBURSEMENT REIMBURSEMENT "
				+ "INNER JOIN ERS_REIMBURSEMENT_TYPE TYPE " 
				+ "ON REIMBURSEMENT.REIMB_TYPE_ID=TYPE.REIMB_TYPE_ID " 
				+ "INNER JOIN ERS_REIMBURSEMENT_STATUS STATUS "
				+ "ON REIMBURSEMENT.REIMB_STATUS_ID=STATUS.REIMB_STATUS_ID "
				+ "INNER JOIN ERS_USERS AUTHOR "
				+ "ON REIMBURSEMENT.REIMB_AUTHOR=AUTHOR.ERS_USER_ID "
				+ "INNER JOIN ERS_USER_ROLES AUTHOR_ROLE "
				+ "ON AUTHOR.USER_ROLE_ID=AUTHOR_ROLE.ERS_USER_ROLE_ID "
				+ "LEFT JOIN ERS_USERS RESOLVER "
				+ "ON REIMBURSEMENT.REIMB_RESOLVER = RESOLVER.ERS_USER_ID "
				+ "LEFT JOIN ERS_USER_ROLES RESOLVER_ROLE "
				+ "ON RESOLVER.USER_ROLE_ID=RESOLVER_ROLE.ERS_USER_ROLE_ID " 
				+ "WHERE REIMBURSEMENT.REIMB_AUTHOR=? "
				+ "ORDER BY STATUS DESC, ID ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, user);
		ResultSet rs = stmt.executeQuery();
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement reimbursement = new Reimbursement();
			reimbursement.setReimbId(rs.getInt("ID"));
			reimbursement.setAmount(rs.getDouble("AMOUNT"));
			reimbursement.setSubmitted(rs.getTimestamp("SUBMITTED"));
			reimbursement.setResolved(rs.getTimestamp("RESOLVED"));
			reimbursement.setDescript(rs.getString("DESCRIPTION"));
			reimbursement.setReceipt(rs.getBlob("RECEIPT"));
			
			// Set author
			User tempUser = new User();
			tempUser.setUserId(rs.getInt("AUTHOR_ID"));
			tempUser.setUserName(rs.getString("AUTHOR_USERNAME"));
			tempUser.setPassword(rs.getString("AUTHOR_PASSWORD"));
			tempUser.setFirstName(rs.getString("AUTHOR_FIRST_NAME"));
			tempUser.setLastName(rs.getString("AUTHOR_LAST_NAME"));
			tempUser.setEmail(rs.getString("AUTHOR_EMAIL"));
			// Set role of author
			UserRole role = new UserRole(rs.getInt("AUTHOR_ROLE_ID"), rs.getString("AUTHOR_ROLE"));
			tempUser.setRole(role);
			reimbursement.setAuthor(tempUser);
			
			// Set resolver
			if(rs.getString("RESOLVER_USERNAME") == null) {
				reimbursement.setResolver(new User());
			} else {
				User tempUser2 = new User();
				tempUser2.setUserId(rs.getInt("RESOLVER_ID"));
				tempUser2.setUserName(rs.getString("RESOLVER_USERNAME"));
				tempUser2.setPassword(rs.getString("RESOLVER_PASSWORD"));
				tempUser2.setFirstName(rs.getString("RESOLVER_FIRST_NAME"));
				tempUser2.setLastName(rs.getString("RESOLVER_LAST_NAME"));
				tempUser2.setEmail(rs.getString("RESOLVER_EMAIL"));
				// Set role of author
				UserRole role2 = new UserRole(rs.getInt("RESOLVER_ROLE_ID"), rs.getString("RESOLVER_ROLE"));
				tempUser2.setRole(role2);
				reimbursement.setResolver(tempUser2);
			}
			
			reimbursement.setStatus(new Status(rs.getInt("STATUS_ID"), rs.getString("STATUS")));
			reimbursement.setType(new Type(rs.getInt("TYPE_ID"), rs.getString("TYPE")));
			list.add(reimbursement);
		}
		return list;
	}
	
	// Returns list of all reimbursements 
	public List<Reimbursement> findAllReimbursement() throws SQLException {
		String sql = "SELECT REIMBURSEMENT.REIMB_ID AS ID, "
				+ "REIMBURSEMENT.REIMB_AMOUNT AS AMOUNT, "
				+ "REIMBURSEMENT.REIMB_SUBMITTED AS SUBMITTED, "
				+ "REIMBURSEMENT.REIMB_RESOLVED AS RESOLVED, "
				+ "REIMBURSEMENT.REIMB_DESCRIPTION AS DESCRIPTION, "
				+ "REIMBURSEMENT.REIMB_RECEIPT AS RECEIPT, "
				+ "REIMBURSEMENT.REIMB_AUTHOR AS AUTHOR_ID, "
				+ "AUTHOR.ERS_USERNAME AS AUTHOR_USERNAME, "
				+ "AUTHOR.ERS_PASSWORD AS AUTHOR_PASSWORD, "
				+ "AUTHOR.USER_FIRST_NAME AS AUTHOR_FIRST_NAME, "
				+ "AUTHOR.USER_LAST_NAME AS AUTHOR_LAST_NAME, "
				+ "AUTHOR.USER_EMAIL AS AUTHOR_EMAIL, "
				+ "AUTHOR.USER_ROLE_ID AS AUTHOR_ROLE_ID, "
				+ "AUTHOR_ROLE.USER_ROLE AS AUTHOR_ROLE, "
				+ "REIMBURSEMENT.REIMB_RESOLVER AS RESOLVER_ID, "
				+ "RESOLVER.ERS_USERNAME AS RESOLVER_USERNAME, "
				+ "RESOLVER.ERS_PASSWORD AS RESOLVER_PASSWORD, "
				+ "RESOLVER.USER_FIRST_NAME AS RESOLVER_FIRST_NAME, "
				+ "RESOLVER.USER_LAST_NAME AS RESOLVER_LAST_NAME, "
				+ "RESOLVER.USER_EMAIL AS RESOLVER_EMAIL, "
				+ "RESOLVER.USER_ROLE_ID AS RESOLVER_ROLE_ID, "
				+ "RESOLVER_ROLE.USER_ROLE AS RESOLVER_ROLE, "
				+ "REIMBURSEMENT.REIMB_STATUS_ID AS STATUS_ID, "
				+ "STATUS.REIMB_STATUS AS STATUS, "
				+ "REIMBURSEMENT.REIMB_TYPE_ID AS TYPE_ID, "
				+ "TYPE.REIMB_TYPE AS TYPE "
				+ "FROM ERS_REIMBURSEMENT REIMBURSEMENT "
				+ "INNER JOIN ERS_REIMBURSEMENT_TYPE TYPE " 
				+ "ON REIMBURSEMENT.REIMB_TYPE_ID=TYPE.REIMB_TYPE_ID " 
				+ "INNER JOIN ERS_REIMBURSEMENT_STATUS STATUS "
				+ "ON REIMBURSEMENT.REIMB_STATUS_ID=STATUS.REIMB_STATUS_ID "
				+ "INNER JOIN ERS_USERS AUTHOR "
				+ "ON REIMBURSEMENT.REIMB_AUTHOR=AUTHOR.ERS_USER_ID "
				+ "INNER JOIN ERS_USER_ROLES AUTHOR_ROLE "
				+ "ON AUTHOR.USER_ROLE_ID=AUTHOR_ROLE.ERS_USER_ROLE_ID "
				+ "LEFT JOIN ERS_USERS RESOLVER "
				+ "ON REIMBURSEMENT.REIMB_RESOLVER = RESOLVER.ERS_USER_ID "
				+ "LEFT JOIN ERS_USER_ROLES RESOLVER_ROLE "
				+ "ON RESOLVER.USER_ROLE_ID=RESOLVER_ROLE.ERS_USER_ROLE_ID "
				+ "ORDER BY STATUS DESC, ID ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement reimbursement = new Reimbursement();
			reimbursement.setReimbId(rs.getInt("ID"));
			reimbursement.setAmount(rs.getDouble("AMOUNT"));
			reimbursement.setSubmitted(rs.getTimestamp("SUBMITTED"));
			reimbursement.setResolved(rs.getTimestamp("RESOLVED"));
			reimbursement.setDescript(rs.getString("DESCRIPTION"));
			reimbursement.setReceipt(rs.getBlob("RECEIPT"));
			
			// Set author
			User tempUser = new User();
			tempUser.setUserId(rs.getInt("AUTHOR_ID"));
			tempUser.setUserName(rs.getString("AUTHOR_USERNAME"));
			tempUser.setPassword(rs.getString("AUTHOR_PASSWORD"));
			tempUser.setFirstName(rs.getString("AUTHOR_FIRST_NAME"));
			tempUser.setLastName(rs.getString("AUTHOR_LAST_NAME"));
			tempUser.setEmail(rs.getString("AUTHOR_EMAIL"));
			// Set role of author
			UserRole role = new UserRole(rs.getInt("AUTHOR_ROLE_ID"), rs.getString("AUTHOR_ROLE"));
			tempUser.setRole(role);
			reimbursement.setAuthor(tempUser);
			
			// Set resolver
			if(rs.getString("RESOLVER_USERNAME") == null) {
				reimbursement.setResolver(new User());
			} else {
				User tempUser2 = new User();
				tempUser2.setUserId(rs.getInt("RESOLVER_ID"));
				tempUser2.setUserName(rs.getString("RESOLVER_USERNAME"));
				tempUser2.setPassword(rs.getString("RESOLVER_PASSWORD"));
				tempUser2.setFirstName(rs.getString("RESOLVER_FIRST_NAME"));
				tempUser2.setLastName(rs.getString("RESOLVER_LAST_NAME"));
				tempUser2.setEmail(rs.getString("RESOLVER_EMAIL"));
				// Set role of author
				UserRole role2 = new UserRole(rs.getInt("RESOLVER_ROLE_ID"), rs.getString("RESOLVER_ROLE"));
				tempUser2.setRole(role2);
				reimbursement.setResolver(tempUser2);
			}
			
			reimbursement.setStatus(new Status(rs.getInt("STATUS_ID"), rs.getString("STATUS")));
			reimbursement.setType(new Type(rs.getInt("TYPE_ID"), rs.getString("TYPE")));
			list.add(reimbursement);
		}
		return list;
	}
	
	// Returns list of all reimbursements that matches filterStatusId
	public List<Reimbursement> filterReimbursement(int filterStatus) throws SQLException {
		String sql = "SELECT REIMBURSEMENT.REIMB_ID AS ID, "
				+ "REIMBURSEMENT.REIMB_AMOUNT AS AMOUNT, "
				+ "REIMBURSEMENT.REIMB_SUBMITTED AS SUBMITTED, "
				+ "REIMBURSEMENT.REIMB_RESOLVED AS RESOLVED, "
				+ "REIMBURSEMENT.REIMB_DESCRIPTION AS DESCRIPTION, "
				+ "REIMBURSEMENT.REIMB_RECEIPT AS RECEIPT, "
				+ "REIMBURSEMENT.REIMB_AUTHOR AS AUTHOR_ID, "
				+ "AUTHOR.ERS_USERNAME AS AUTHOR_USERNAME, "
				+ "AUTHOR.ERS_PASSWORD AS AUTHOR_PASSWORD, "
				+ "AUTHOR.USER_FIRST_NAME AS AUTHOR_FIRST_NAME, "
				+ "AUTHOR.USER_LAST_NAME AS AUTHOR_LAST_NAME, "
				+ "AUTHOR.USER_EMAIL AS AUTHOR_EMAIL, "
				+ "AUTHOR.USER_ROLE_ID AS AUTHOR_ROLE_ID, "
				+ "AUTHOR_ROLE.USER_ROLE AS AUTHOR_ROLE, "
				+ "REIMBURSEMENT.REIMB_RESOLVER AS RESOLVER_ID, "
				+ "RESOLVER.ERS_USERNAME AS RESOLVER_USERNAME, "
				+ "RESOLVER.ERS_PASSWORD AS RESOLVER_PASSWORD, "
				+ "RESOLVER.USER_FIRST_NAME AS RESOLVER_FIRST_NAME, "
				+ "RESOLVER.USER_LAST_NAME AS RESOLVER_LAST_NAME, "
				+ "RESOLVER.USER_EMAIL AS RESOLVER_EMAIL, "
				+ "RESOLVER.USER_ROLE_ID AS RESOLVER_ROLE_ID, "
				+ "RESOLVER_ROLE.USER_ROLE AS RESOLVER_ROLE, "
				+ "REIMBURSEMENT.REIMB_STATUS_ID AS STATUS_ID, "
				+ "STATUS.REIMB_STATUS AS STATUS, "
				+ "REIMBURSEMENT.REIMB_TYPE_ID AS TYPE_ID, "
				+ "TYPE.REIMB_TYPE AS TYPE "
				+ "FROM ERS_REIMBURSEMENT REIMBURSEMENT "
				+ "INNER JOIN ERS_REIMBURSEMENT_TYPE TYPE " 
				+ "ON REIMBURSEMENT.REIMB_TYPE_ID=TYPE.REIMB_TYPE_ID " 
				+ "INNER JOIN ERS_REIMBURSEMENT_STATUS STATUS "
				+ "ON REIMBURSEMENT.REIMB_STATUS_ID=STATUS.REIMB_STATUS_ID "
				+ "INNER JOIN ERS_USERS AUTHOR "
				+ "ON REIMBURSEMENT.REIMB_AUTHOR=AUTHOR.ERS_USER_ID "
				+ "INNER JOIN ERS_USER_ROLES AUTHOR_ROLE "
				+ "ON AUTHOR.USER_ROLE_ID=AUTHOR_ROLE.ERS_USER_ROLE_ID "
				+ "LEFT JOIN ERS_USERS RESOLVER "
				+ "ON REIMBURSEMENT.REIMB_RESOLVER = RESOLVER.ERS_USER_ID "
				+ "LEFT JOIN ERS_USER_ROLES RESOLVER_ROLE "
				+ "ON RESOLVER.USER_ROLE_ID=RESOLVER_ROLE.ERS_USER_ROLE_ID " 
				+ "WHERE REIMBURSEMENT.REIMB_STATUS_ID=? "
				+ "ORDER BY ID ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filterStatus);
		ResultSet rs = stmt.executeQuery();
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement reimbursement = new Reimbursement();
			reimbursement.setReimbId(rs.getInt("ID"));
			reimbursement.setAmount(rs.getDouble("AMOUNT"));
			reimbursement.setSubmitted(rs.getTimestamp("SUBMITTED"));
			reimbursement.setResolved(rs.getTimestamp("RESOLVED"));
			reimbursement.setDescript(rs.getString("DESCRIPTION"));
			reimbursement.setReceipt(rs.getBlob("RECEIPT"));
			
			// Set author
			User tempUser = new User();
			tempUser.setUserId(rs.getInt("AUTHOR_ID"));
			tempUser.setUserName(rs.getString("AUTHOR_USERNAME"));
			tempUser.setPassword(rs.getString("AUTHOR_PASSWORD"));
			tempUser.setFirstName(rs.getString("AUTHOR_FIRST_NAME"));
			tempUser.setLastName(rs.getString("AUTHOR_LAST_NAME"));
			tempUser.setEmail(rs.getString("AUTHOR_EMAIL"));
			
			// Set role of author
			UserRole role = new UserRole(rs.getInt("AUTHOR_ROLE_ID"), rs.getString("AUTHOR_ROLE"));
			tempUser.setRole(role);
			reimbursement.setAuthor(tempUser);
			
			// Set resolver
			if(rs.getString("RESOLVER_USERNAME") == null) {
				reimbursement.setResolver(new User());
			} else {
				User tempUser2 = new User();
				tempUser2.setUserId(rs.getInt("RESOLVER_ID"));
				tempUser2.setUserName(rs.getString("RESOLVER_USERNAME"));
				tempUser2.setPassword(rs.getString("RESOLVER_PASSWORD"));
				tempUser2.setFirstName(rs.getString("RESOLVER_FIRST_NAME"));
				tempUser2.setLastName(rs.getString("RESOLVER_LAST_NAME"));
				tempUser2.setEmail(rs.getString("RESOLVER_EMAIL"));
				// Set role of author
				UserRole role2 = new UserRole(rs.getInt("RESOLVER_ROLE_ID"), rs.getString("RESOLVER_ROLE"));
				tempUser2.setRole(role2);
				reimbursement.setResolver(tempUser2);
			}
			
			reimbursement.setStatus(new Status(rs.getInt("STATUS_ID"), rs.getString("STATUS")));
			reimbursement.setType(new Type(rs.getInt("TYPE_ID"), rs.getString("TYPE")));
			list.add(reimbursement);
		}
		return list;
	}
	
	// Insert reimbursement into database given amount, description, receipt, authorId, and typeId
	public void addReimbursement(double amount, String descript, InputStream receipt, int author, int type) throws SQLException {
		String sql = "INSERT INTO ERS_REIMBURSEMENT VALUES(1, ?, ?, NULL, ?, ?, ?, NULL, 1, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setDouble(1, amount);
		// Set timestamp to current timestamp
		stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		stmt.setString(3, descript);
		// If no receipt given set to null
		if(receipt == null)
			stmt.setNull(4, java.sql.Types.BLOB);
		else
			stmt.setBlob(4, receipt);
		stmt.setInt(5, author);
		stmt.setInt(6, type);
		stmt.executeUpdate();
		conn.commit();
	}
	
	// Update status given reimbursementId, resolverId, and statusId
	public void updateReimbursement(int reimbId, int resolver, int status) throws SQLException {
		String sql = "UPDATE ERS_REIMBURSEMENT "
				+ "SET REIMB_STATUS_ID=?, "
				+ "REIMB_RESOLVER=?, "
				+ "REIMB_RESOLVED=?"
				+ "WHERE REIMB_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, status);
		stmt.setInt(2, resolver);
		// Set resolved time to current time
		stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		stmt.setInt(4, reimbId);
		stmt.executeUpdate();
		conn.commit();
	}

	// Return blob of receipt
	public Blob findReceipt(int reimbId) throws SQLException {
		String sql = "SELECT REIMB_RECEIPT "
				+ "FROM ERS_REIMBURSEMENT "
				+ "WHERE REIMB_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimbId);
		ResultSet rs = stmt.executeQuery();
		Blob image = null;
		while(rs.next()) {
			image = rs.getBlob("REIMB_RECEIPT");
		}
		return image;
	}
}
