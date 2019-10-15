package com.hr.servlet.s1;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ThirdServlet
 */
@WebServlet(description = "세번째", urlPatterns = { "/s1/thirdservlet.do" })
public class ThirdServlet extends HttpServlet {
	
	private final Logger LOG = Logger.getLogger(ThirdServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThirdServlet() {
       LOG.debug("ThirdServlet()");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		LOG.debug("init()");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		LOG.debug("destroy()");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		LOG.debug("doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		LOG.debug("doPost()");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("service()");
		doGet(request,response);
	}

	
}
