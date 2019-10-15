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
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>request header info</h3>
<hr/>
	<p>HTTP 프로토콜 헤더 정보: 웹브라우저 종료, 선호 언어</p>
	<%
		Enumeration<String> headerParam = request.getHeaderNames();
		while(headerParam.hasMoreElements()){
			String headerName = headerParam.nextElement();
			out.print(headerName + ":" + request.getHeader(headerName) + "<br/>");
		}
	%>
</body>
</html>