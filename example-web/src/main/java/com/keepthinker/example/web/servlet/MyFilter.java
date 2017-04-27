package com.keepthinker.example.web.servlet;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			request.getServletContext().getRequestDispatcher("path").forward(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
