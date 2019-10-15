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
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>request_result</h3>
	<hr/>
	<table border="1" cellspacing="1" cellpadding="5">
		<tr>
			<td>이름</td>
			<td><%=request.getParameter("username") %></td>
		</tr>
		<tr>
			<td>직업</td>
			<td><%=request.getParameter("job") %></td>
		</tr>
		<tr>
			<td>관심분야</td>
			<td>
				<%
					String[] favorites = request.getParameterValues("favorite");	
					for(int i=0; i<favorites.length; i++){
						out.print(favorites[i]+",");
					}
				%>
			</td>
		</tr>
	</table>
</body>
</html>