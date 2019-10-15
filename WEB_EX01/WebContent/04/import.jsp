<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//스크립트릿: 자바코드 실행
	Calendar cal=Calendar.getInstance();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Import</title>
</head>
<body>
	<h2>Import:java의 import와 동일한 역할</h2>
	<hr/>
	오늘은: <%=cal.get(Calendar.YEAR)%>년  <%=cal.get(Calendar.MONTH)+1%>월 <%=cal.get(Calendar.DATE)%>일
</body>
</html>