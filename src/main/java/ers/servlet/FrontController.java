package ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		switch(requestURI) {
			case "/ers/login.do": {
				resp.sendRedirect("login.do");
				break;
			}
			case "ers/toAdd.do": {
				resp.sendRedirect("toAdd.do");
				break;
			}
			case "ers/logout.do": {
				resp.sendRedirect("logout.do");
				break;
			}
			case "ers/approve.do": {
				resp.sendRedirect("approve.do");
				break;
			}
			case "ers/filter.do": {
				resp.sendRedirect("filter.do");
				break;
			}
			case "ers/receipt.do": {
				resp.sendRedirect("receipt.do");
				break;
			}
			default: {
				System.out.println(requestURI);
				throw new IllegalArgumentException("Invalid URI");
			}
		}
	}
}
