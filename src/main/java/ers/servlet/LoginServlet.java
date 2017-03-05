package ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ers.User;
import ers.data.DataFacade;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", req.getParameter("user"));
		try(DataFacade facade = new DataFacade()) {
			User user = facade.findUser(req.getParameter("user"), req.getParameter("pass"));
			if(user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("username", req.getParameter("user"));
				session.setAttribute("name", user.getFirstName() + " " + user.getLastName());
				session.setAttribute("role", user.getrole().getRole());
				session.setAttribute("facade", facade);
				if(user.getrole().getRoleId() == 1) {
					session.setAttribute("reimb", facade.findAllReimbursement());
					resp.sendRedirect("managerPage.jsp");
				}
				else {
					session.setAttribute("reimb", facade.findUserReimbursement(user.getUserId()));
					resp.sendRedirect("employeePage.jsp");
				}
			}
			else {
				req.setAttribute("val", "fail");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login get called");
		this.doPost(req, resp);
	}
}
