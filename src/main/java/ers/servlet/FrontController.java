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
		System.out.println("front controller");
		String requestURI = req.getRequestURI();
		switch(requestURI) {
			case "ers/login.do": {
				req.getRequestDispatcher("login.do").forward(req, resp);
				break;
			}
			case "/ers/secure/toAdd.do": {
				req.getRequestDispatcher("../toAdd.do").forward(req, resp);
				break;
			}
			case "/ers/secure/add.do": {
				req.getRequestDispatcher("../add.do").forward(req, resp);
				break;
			}
			case "/ers/secure/logout.do": {
				req.getRequestDispatcher("../logout.do").forward(req, resp);
				break;
			}
			case "/ers/secure/approve.do": {
				req.getRequestDispatcher("../approve.do").forward(req, resp);
				break;
			}
			case "/ers/secure/filter.do": {
				req.getRequestDispatcher("../filter.do").forward(req, resp);
				break;
			}
			case "/ers/secure/receipt.do": {
				req.getRequestDispatcher("../receipt.do").forward(req, resp);
				break;
			}
			default: {
				System.out.println(requestURI);
				throw new IllegalArgumentException("Invalid URI");
			}
		}
	}
}
