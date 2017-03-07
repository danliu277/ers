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

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// ???
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("userId") == null) {
			chain.doFilter(request, response);
		} else if(req.getSession().getAttribute("role").equals(1)) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("secure/managerPage.jsp");
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("secure/employeePage.jsp");
		}
	}

	@Override
	public void destroy() {
		// ????
	}

}
