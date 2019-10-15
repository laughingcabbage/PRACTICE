/**
 * @Class Name : Hello.java
 * @Description : Hello
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.07.02           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2018.07.10 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 */
package com.hr.servlet.s1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author sist
 *
 */
public class SecondServlet extends HttpServlet {

	private final Logger LOG = Logger.getLogger(SecondServlet.class);
	public SecondServlet(){
		LOG.debug("SecondServlet()");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.debug("doGet()");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.debug("doPost()");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.debug("service()");
	}

	@Override
	public void destroy() {
		LOG.debug("destroy()");
	}

	@Override
	public void init() throws ServletException {
		LOG.debug("init()");
	}
	
	
}
