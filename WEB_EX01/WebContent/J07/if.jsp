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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	속성        필수       기본값     설명
	test    Y    없음        조건
	var     N    없음        test결과가 true인지 false인지 저장
	scope   N    page   변수가 저장될 범위
 -->
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
	<h3>&lt;c:if&gt;</h3>
	<hr/>
	<c:set var="msg" value="JSP"/>
	msg:<c:out value="${msg}"></c:out><br/>
	name:<c:out value="${param.name}"></c:out><br/>
	<c:if test="${msg==param.name}" var="result">
		TEST:result:<c:out value="${result}" />
	</c:if>
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>