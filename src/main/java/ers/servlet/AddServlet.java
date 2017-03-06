package ers.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ers.data.DataFacade;

@SuppressWarnings("serial")
@MultipartConfig
public class AddServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(DataFacade facade = new DataFacade()){
			InputStream inputStream = null;
			if(ServletFileUpload.isMultipartContent(req)) {
				Part filePart = req.getPart("receipt");
				if(filePart != null) {
					inputStream = filePart.getInputStream();
				}
			}
			System.out.println(req.getParameter("amount"));
			System.out.println(req.getParameter("type"));
			facade.addReimbursement( Double.parseDouble(req.getParameter("amount")), 
				req.getParameter("description"), 
				inputStream, 
				Integer.parseInt(req.getSession().getAttribute("userId").toString()),
				req.getParameter("type").toString().equals("Lodging") ? 1 :
				req.getParameter("type").equals("Travel") ? 2 :
				req.getParameter("type").toString().equals("Food") ? 3 : 4);
			req.getSession().setAttribute("reimb", 
				facade.findUserReimbursement(Integer.parseInt(req.getSession().getAttribute("userId").toString())));
			resp.sendRedirect("secure/employeePage.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET: " + req.getParameter("amount"));
		this.doPost(req, resp);
	}
}
