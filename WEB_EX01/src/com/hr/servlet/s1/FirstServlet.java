package com.hr.servlet.s1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FirstServlet extends HttpServlet{
/*
	서블릿
	-*.java 단독으로 동작할 수 없다. WAS(tomcat, JEUS, weblogic, websphere, JBOSS)
	-상속(HttpServlet)
	-초기화 init() -> 작업수행 doGet(), doPost() -> 종료:destroy()
	-서블릿 클래스 -> 서블릿 라이프 사이클 -> 서블릿 매핑 작업 -> 웹브라우저에서 호출
	
	Servlet, ServletConfig(interface)
		^
		|
	GenericServlet
		^
		|
	HttpServlet
	
	서블릿 매핑 작업(anotation)
	1.web.xml
	 1.1. <servlet>, <servlet-mapping>
	 1.2. <servlet>
	 		<servlet-name>s1_FirstServlet</servlet-name>
	 		<servlet-class>com.hr.servlet.s1.FirstServlet</servlet-class>
	 	  </servlet>
	 	  
	 	  <servlet-mapping><!-- servlet-name URL-->
	 	    <servlet-name>s1_FirstServlet</servlet-name>
	 	    <url-pattern>/s1/firstservlet.nhn</url-pattern>
	 	  </servlet-mapping>
*/
	private final Logger LOG = Logger.getLogger(FirstServlet.class);
	
	public FirstServlet(){
		LOG.debug("============================");
		LOG.debug("=FirstServlet=");
		LOG.debug("============================");	
	}
	
	@Override
	public void init() throws ServletException {
		LOG.debug("============================");
		LOG.debug("=init=");
		LOG.debug("============================");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.debug("============================");
		LOG.debug("=doGet=");
		LOG.debug("============================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.debug("============================");
		LOG.debug("=doPost=");
		LOG.debug("============================");
	}

	@Override
	public void destroy() {
		LOG.debug("============================");
		LOG.debug("=destroy=");
		LOG.debug("============================");
	}


	
	
	
}
