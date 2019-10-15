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
<%--
내장객체: jsp에서 선언 없이 사용할 수 있는 객체 (*는 자주 사용되는 것들)
	*request: 요청에 관련된 객체
	*response: 사용자 요청에 대한 응답 객체 
	pageContext: jsp실행에 대한 context 정보를 알려 줌
	*session: 클라이언트 session 정보 처리
	application: 웹 서버의 애플리케이션 정보 처리
	*out: 사용자에게 전달하기 위한 처리
	config: jsp환경 초기화
	page: jsp class파일에 대한 정보
	*exception: 예외처리
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
<h3>request:javax.servlet.http.HttpServletRequest</h3>
<p>클라이언트(브라우저)에서 서버로 전달되는 정보를 처리하기 위해 사용
   html form을 통해 입력된 값을 jsp로 가져올 때 사용.
</p>
<hr/>
클라이언트 ip:<%=request.getRemoteAddr() %><br/>
요청정보길이:<%=request.getContentLength() %><br/>
컨텍스트 경로:<%=request.getContextPath() %><br />
요청정보 전송방식:<%=request.getProtocol() %><br />
요청정보 URI:<%=request.getRequestURI() %><br />
서버이름:<%=request.getServerName() %><br/>
서버포트:<%=request.getServerPort() %>
</body>
</html>