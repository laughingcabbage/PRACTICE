<%--
/**
* @Class Name : dbcp_connection.jsp
* @Description : Sample Register 화면
* @Modification Information
*
* 수정일 수정자 수정내용
* ------- -------- ---------------------------
* 2019.07.01 최초 생성
*
* author 실행환경 개발팀
* since 2019.07.01
*
* Copyright (C) 2009 by KandJang All right reserved.
*/
--%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	Connection conn = null;
	try{
		Context init = new InitialContext();
		LOG.debug("1.init:"+init);
		
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/oracleDB");
		LOG.debug("2.ds:"+ds);
		
		conn = ds.getConnection();
		out.print(conn);
		LOG.debug("3.conn:"+conn);
	}catch(Exception e){
		LOG.debug("3.conn:"+conn);
		LOG.debug("3.e:"+e.getMessage());
	}finally{
		if(null!=conn){
			try{
				conn.close();
			}catch(Exception e){
				
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="/WEB_EX01/js/jquery-ui.css">
	<link rel="shortcut icon" href="/WEB_EX01/image/favicon.ico">
	<!-- 부트스트랩 -->
    <link href="/WEB_EX01/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/js/jquery-ui.js"></script>
</head>
<body>
	<h3></h3>
	<hr/>
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>