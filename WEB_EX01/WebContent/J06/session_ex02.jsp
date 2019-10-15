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
	<h2></h2>
	<hr/>
	<%
		//로그아웃: session.invalidate();
		/*
			이 파일을 열어서 session이라고 검색해 보십시오.
			<session-config>
	  			<session-timeout>360</session-timeout>
			</session-config>
		*/
	
		//최초의 세션일 때 설정
		if(session.isNew()){
			out.print("<script>alert('세션이 해제되어 다시 설정합니다.');</script>");
			session.setAttribute("login", "OK JSP");
		}
	%>
	1. 세션id: <%=session.getId()%><br/>
	2. 세션유지시간: <%=session.getMaxInactiveInterval()%><br/>
	3. 로그인 세션 값: <%=session.getAttribute("login") %>
	
	<script>
		/*
			session: http는 비연결형 프로토콜인 관계로 한 페이지가 출력된 다음에는 서버와 클라이언트 간의 연결이 끊어진다.
					  따라서 한 번 로그인한 사용자의 정보를 로그아웃할 때까지 보관해야 할 경우에 사용.
					  두 가지 방법(쿠키(브라우저에 보관), 세션(서버에 보관))이 있다. 
		*/
	
		$(document).ready(function(){

		});
	</script>
</body>
</html>