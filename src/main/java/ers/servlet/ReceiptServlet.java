package ers.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.data.DataFacade;

@SuppressWarnings("serial")
public class ReceiptServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(DataFacade facade = new DataFacade()) {
			Blob image = facade.findReceipt(Integer.parseInt(req.getParameter("reimbId")));
			byte[] imgData = image.getBytes(1, (int)image.length());
			resp.setContentType("image/jpeg");
			OutputStream output = resp.getOutputStream();
			output.write(imgData);
			output.flush();
			output.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
