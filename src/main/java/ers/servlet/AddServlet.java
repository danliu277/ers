package ers.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ers.data.DataFacade;

@SuppressWarnings("serial")
@WebServlet("/upload")
@MultipartConfig
public class AddServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(DataFacade facade = new DataFacade()){
			double amount = 0;
			String description = null;
			int type = 0;
			InputStream inputStream = null;
			// Stuff to parse parameter
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024 * 3);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024 * 1024 * 40);
			upload.setSizeMax(1024 * 1024 * 50);
	        // Parse parameters
			for(FileItem item: upload.parseRequest(req)) {
				if(item.getFieldName().equals("amount")) {
					amount = Double.parseDouble(item.getString());
				}
				else if(item.getFieldName().equals("description")) {
					description = item.getString();
				}
				else if(item.getFieldName().equals("receipt")) {
					inputStream = item.getInputStream();
				}
				else if(item.getFieldName().equals("type")) {
					type = item.getString().equals("Lodging") ? 1:
						item.getString().equals("Travel") ? 2 :
						item.getString().equals("Food") ? 3 : 4;
				}
			}
			// Add reimbursement
			facade.addReimbursement( amount, description, inputStream, 
				Integer.parseInt(req.getSession().getAttribute("userId").toString()), type);
			
			// Update employees reimbursement list
			req.getSession().setAttribute("reimb", 
				facade.findUserReimbursement(Integer.parseInt(req.getSession().getAttribute("userId").toString())));
			
			// Set attribute to successfully submitted
			req.setAttribute("reimbSuccess", "true");
			
			// Go back to employeePage
			//resp.sendRedirect("secure/employeePage.jsp");
			req.getRequestDispatcher("secure/employeePage.jsp").forward(req, resp);
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
