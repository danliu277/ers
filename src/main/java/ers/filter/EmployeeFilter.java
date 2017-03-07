package ers.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// ???
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if(req.getSession().getAttribute("role").equals("Employee")) {
			chain.doFilter(request, response);
		} else if(req.getSession().getAttribute("role").equals("Manager")) {
			res.sendRedirect("managerPage.jsp");
		}
	}

	@Override
	public void destroy() {
		// ???
	}

}
