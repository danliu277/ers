package ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.data.DataFacade;

@SuppressWarnings("serial")
public class FilterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(DataFacade facade = new DataFacade()) {
			String filter = req.getParameter("filter");
			if(filter.equals("All")) {
				req.getSession().setAttribute("reimb", facade.findAllReimbursement());
			} else if(filter.equals("Pending")) {
				req.getSession().setAttribute("reimb", facade.filterReimbursement(1));
			} else if(filter.equals("Approved")) {
				req.getSession().setAttribute("reimb", facade.filterReimbursement(2));
			} else if(filter.equals("Denied")) {
				req.getSession().setAttribute("reimb", facade.filterReimbursement(3));
			}
			resp.sendRedirect("managerPage.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
