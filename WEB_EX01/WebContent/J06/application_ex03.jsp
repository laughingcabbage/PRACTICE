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
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
	<h2>application: ServletContext, API, 절대경로 등의 정보를 제공</h2>
	<hr/>
		1. 서버정보: <%=application.getServerInfo() %><br/>
		2. ServletAPI: <%=application.getMajorVersion() + "." + application.getMinorVersion() %><br/>
		3. application_ex03.jsp절대경로: <%=application.getRealPath("application_ex03.jsp") %>
	<script>
		$(document).ready(function(){

		});
	</script>
</body>
</html>