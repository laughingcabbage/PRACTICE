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
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 
	<는 &lt;
	>는 &gt;
	&는 &amp;
	'는 &#039;
	"는 &034;
 -->
	<h3></h3>
	<hr/>
	<%
		FileReader reader = null;
		try{
			String path = request.getParameter("path");
			reader = new FileReader(getServletContext().getRealPath(path));
	%>
	<pre>
		소스코드:<%=path %>
		<c:out value="<%=reader %>" escapeXml="true"></c:out>
	</pre>
	<%
		}catch(IOException e){
			
	%>
		에러<%=e.getMessage() %>
	<%
		}finally{
			if(null!=reader){
				try{
					reader.close();
				}catch(IOException io){
					
				}
			}
		}
	%>
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>