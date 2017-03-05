package ers.data;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import ers.ConnectionFactory;
import ers.Reimbursement;
import ers.User;

public class DataFacade implements AutoCloseable{
	private ReimbursementDAO reimbursementDAO;
	private UserDAO userDAO;
	private Connection conn;
	
	public DataFacade(){
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			reimbursementDAO = new ReimbursementDAO(conn);
			userDAO = new UserDAO(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User findUser(String username, String password) throws Exception {
		String temp = userDAO.findPassword(username);
		if(temp == null) {
			System.out.println("Username not found");
			return null;
		}
		if (BCrypt.checkpw(password, temp)) {
			System.out.println("Username found and password matches");
		    return userDAO.findUser(username);
		}
		else{
			System.out.println("Username and password does not match");
		    return null;
		}
	}
	
	public List<Reimbursement> findUserReimbursement(int user) throws Exception{
		return reimbursementDAO.findUserReimbursement(user);
	}
	
	public List<Reimbursement> findAllReimbursement() throws Exception{
		return reimbursementDAO.findAllReimbursement();
	}
	
	public List<Reimbursement> filterReimbursement(int statusId) throws Exception {
		return reimbursementDAO.filterReimbursement(statusId);
	}
	
	public void addReimbursement(double amount, String descript, InputStream receipt, int author, int type) throws Exception {
		reimbursementDAO.addReimbursement(amount, descript, receipt, author, type);
	}
	
	public void updateReimbursement(int reimbId, int resolver, int status) throws SQLException {
		reimbursementDAO.updateReimbursement(reimbId, resolver, status);
	}
	
	public Blob findReceipt(int reimbId) throws SQLException {
		return reimbursementDAO.findReceipt(reimbId);
	}
	
	// Usable with try-with-resources statement or call with statement
	public void close() throws Exception {
		if(conn != null)
			conn.close();
	}
}
