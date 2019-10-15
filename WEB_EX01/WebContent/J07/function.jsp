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
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<h3>function</h3>
	<hr/>
	<c:set var="str01" value="lunch 즐거운 점심.칼국수"/>
	<c:set var="str02" value="파전"/>
	length(str01): ${fn:length(str01) }<br/>
	toUpperCase(str01): ${fn:toUpperCase(str01) }<br/>
	substring(str01): ${fn:substring(str01,3,6) }<br/>
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>