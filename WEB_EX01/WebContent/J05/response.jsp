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
<h3>response: 요청에 대한 응답 정보 처리</h3>
<hr/>
	<p>1.헤더정보: 웹브라우저 캐시 제어<br/>
		1.1.Cache: a.jsp를 다시 호출할 때 a.jsp에 변경사항이 없는 경우 처음 브라우저에 저장된 a.jsp를 보여준다.<br/>
		1.2.Cache-Control, Pragma, Expires 
		<%
			//HTTP 1.1에서 지원하는 header정보: 웹브라우저는 응답결과를 캐시하지 않는다.
			response.setHeader("Cache-Control", "no-cache");
			//웹브라우저는 응답결과를 캐시하지 않는다. (브라우저 앞/뒤로 가기시 캐시 방지용)
			response.addHeader("Cache-Control", "no-store");
			//HTTP 1.0에서 지원하는 header정보: 웹브라우저는 응답결과를 캐시하지 않는다.
			response.setHeader("Pragma", "no-cache");
			//HTTP 1.0에서 응답결과의 만료일: 1970년 1월 1일 기준.
			response.setDateHeader("Expires", 1L);
		%>
	</p>
	<p>2.리다이렉트: 페이지 이동</p>

</body>
</html>