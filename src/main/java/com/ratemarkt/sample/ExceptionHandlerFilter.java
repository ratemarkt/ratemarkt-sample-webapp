package com.ratemarkt.sample;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.ratemarkt.errors.RemoteError;

public class ExceptionHandlerFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (RemoteError e) {
			e.printStackTrace();
			((HttpServletResponse) response).sendError(500, String.format("Error Code: %s, Message: %s Detail: %s",
					e.getErrorCode(), e.getCaption(), e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			((HttpServletResponse) response).sendError(500, e.getMessage());
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
