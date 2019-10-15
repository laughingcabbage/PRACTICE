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
	<h3>OUT내장객체</h3>
	<hr/>
	<!-- 
		out: 출력에 관련된 객체
			 JspWriter객체의 참조변수이고 버퍼, 출력
		out.print(content); content를 화면에 출력
	 -->
	 0.out.println: <% out.print("out.print"); %><br/>
	 1.설정된 버퍼크기: <%=out.getBufferSize()%> <!-- 1.설정된 버퍼크기: 8192 --><br/>
	 2.남아있는 버퍼크기: <%=out.getRemaining()%> <!-- 2.남아있는 버퍼크기: 7881 --><br/>
	 3.버퍼 플러쉬: <% out.flush(); %><br/>
	 4.남아있는 버퍼크기: <%=out.getRemaining()%>
</body>
</html>