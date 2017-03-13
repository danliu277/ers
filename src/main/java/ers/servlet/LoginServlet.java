package ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
			// Find user
			User user = facade.findUser(req.getParameter("user"), req.getParameter("pass"));
			// If username and password matches
			if(user != null) {
				// Create session attributes for user information
				HttpSession session = req.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("username", req.getParameter("user"));
				session.setAttribute("name", user.getFirstName() + " " + user.getLastName());
				session.setAttribute("role", user.getrole().getRole());
				// Create cookie for username if remember me is checked
				if(req.getParameter("remember") != null) {
					boolean exists = false;
					Cookie[] cookies = req.getCookies();
					// Parse through cookies
					for(Cookie e : cookies) {
						// If cookie exixts rewrite cookie
						if(e.getName().equals("user")) {
							e.setValue(session.getAttribute("username").toString());
							e.setPath("/");
							resp.addCookie(e);
							exists = true;
						}
					}
					// If cookie does not exists create cookie
					if(!exists) {
						Cookie cookie = new Cookie("user", session.getAttribute("username").toString());
						cookie.setPath("/");
						// Lasts a day
						cookie.setMaxAge(60*60*24);
						resp.addCookie(cookie);
					}
				}
				// If user is an employee send to employee page
				if(user.getrole().getRoleId() == 1) {
					// Find and set session attribute to users reimbursement
					session.setAttribute("reimb", facade.findAllReimbursement());
					resp.sendRedirect("secure/managerPage.jsp");
				}
				// If user is a manager send to manager page
				else {
					// Find and set session attribute to all reimbursements
					session.setAttribute("reimb", facade.findUserReimbursement(user.getUserId()));
					resp.sendRedirect("secure/employeePage.jsp");
				}
			}
			// If username and password does not match
			else {
				req.setAttribute("please_login", "match");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}
