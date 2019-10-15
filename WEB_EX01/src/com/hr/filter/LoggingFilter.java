package com.hr.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, description = "로깅", urlPatterns = { "/member/*", "/board/*" })
public class LoggingFilter implements Filter {

	final Logger LOG = Logger.getLogger(LoggingFilter.class);
	
    /**
     * Default constructor. 
     */
    public LoggingFilter() {
    	LOG.debug("=====================");
    	LOG.debug("LoggingFilter");
    	LOG.debug("=====================");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.debug("=====================");
    	LOG.debug("destroy");
    	LOG.debug("=====================");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.debug("=====================");
    	LOG.debug("destroy");
    	LOG.debug("=====================");
    	
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("=====================");
    	LOG.debug("init");
    	LOG.debug("=====================");
	}

}
