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
<%@ page buffer="1kb" autoFlush="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>autoFlush</h3>
<hr/>
<p>buffer=8kb autoFlush=true 자동으로 버퍼 출력</p>
<%
	//autoFlush가 false로 되어 있어서 buffer가 찼는데 비워지지 않기 때문에 overflow 에러가 난다. 
	//java.io.IOException: Error: JSP Buffer overflow
	for(int i=0; i<10000; i++){
		out.print("20190710");
	}
%>
</body>
</html>