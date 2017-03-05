package ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.data.DataFacade;

@SuppressWarnings("serial")
public class ApproveServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(DataFacade facade = new DataFacade()) {
			if(req.getParameter("act").toString().equals("Approve")){
				facade.updateReimbursement(Integer.parseInt(req.getParameter("reimbId").toString()), 
						Integer.parseInt(req.getSession().getAttribute("userId").toString()), 2);
			} else if(req.getParameter("act").toString().equals("Deny")) {
				facade.updateReimbursement(Integer.parseInt(req.getParameter("reimbId").toString()), 
						Integer.parseInt(req.getSession().getAttribute("userId").toString()), 3);
			}
			req.getSession().setAttribute("reimb", facade.findAllReimbursement());
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
