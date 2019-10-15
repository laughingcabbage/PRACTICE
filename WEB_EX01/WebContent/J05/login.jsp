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
<title>Insert title here</title>
</head>
<body>
<h3>login</h3>
<hr/>
<%
	//http://localhost:8080/WEB_EX01/J05/login.jsp?user_id=j라고 하면 잘못된 ID입니다. 라고 나옴
	//http://localhost:8080/WEB_EX01/J05/login.jsp?user_id=hr라고 하면 index로 이동
	String user_id = request.getParameter("user_id");
	if(user_id.equals("hr")){
		response.sendRedirect("index.jsp");
	}else{
		out.print("잘못된 ID입니다.");
	}
%>
</body>
</html>