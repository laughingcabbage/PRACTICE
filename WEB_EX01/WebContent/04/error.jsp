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
isErrorPage="true"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>에러페이지</h3>
<hr/>
	요청 처리 과정에서 에러가 발생했습니다.<br/>
	빠른 시간에 문제를 처리하도록 하겠습니다.
	<p>
		에러타입:<%=exception.getClass().getName()%><br/>
		에러메시지:<%=exception.getMessage() %>
	</p>
</body>
</html>