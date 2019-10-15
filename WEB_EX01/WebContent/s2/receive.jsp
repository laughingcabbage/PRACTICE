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
<%@page import="com.hr.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	//String name = (String) request.getAttribute("name");
	BoardVO vo = (BoardVO) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/WEB_EX01/js/jquery-ui.css">
<link rel="shortcut icon" href="/WEB_EX01/image/favicon.ico">
<title>Insert title here</title>
<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/js/jquery-ui.js"></script>
</head>
<body>
	<h2>receive.jsp</h2>
	<hr/>
	seq=<%=vo.getSeq() %><br/>
	contents=<%=vo.getContents() %>
	<script>
		$(document).ready(function(){

		});
	</script>
</body>
</html>