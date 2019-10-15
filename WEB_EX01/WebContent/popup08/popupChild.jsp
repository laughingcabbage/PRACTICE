<%--
/**
* @Class Name : useOutObject.jsp
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
<%@page import="com.hr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("utf-8"); %>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	String name = StringUtil.nvl(request.getParameter("name"), "");
	String id   = StringUtil.nvl(request.getParameter("id"), "");
	LOG.debug("name:"+name);
	LOG.debug("id:"+id);
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
	<h2>이름:<%=name %></h2>
	<h2>id:<%=id %></h2>
	<input type="button" value="닫기" onclick="javascript:closeWindow();">
	<script>
		function closeWindow(){
			window.opener.setChildValue("좋은 아침입니다.");
			window.self.close();
		}
		
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>