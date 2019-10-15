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
<h3>form</h3>
<hr/>
	<form action="/WEB_EX01/j04_01/viewParam.jsp" method="get">
		이름:<input type="text" name="name" size="10" /><br/>
		주소:<input type="text" name="addr" size="30" /><br/>
		좋아하는 프로그램:<br/>
		<input type="checkbox" name="jsp" value="100"/>JSP<br/>
		<input type="checkbox" name="jsp" value="200"/>JSP_Servlet<br/>
		<input type="checkbox" name="jsp" value="300"/>JSP_JDBC<br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>