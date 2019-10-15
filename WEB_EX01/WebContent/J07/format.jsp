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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--
	숫자: fmt:formatNumber -> fmt:parseNumber
	날짜: fmt:formatDate   -> fmt:parseDate
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
	<h3>formatNumber</h3>
	<hr/>
	<c:set var="price" value="10000" />
	<fmt:formatNumber value="${price }" type="number" var="numberType"/>
	<br/>
	통화: <fmt:formatNumber value="${price }" type="currency" currencySymbol="$"/>
	<br/>
	퍼센트: <fmt:formatNumber value="${price }" type="percent" groupingUsed="false"/>
	<br/>
	숫자:${numberType}
	<br/>
	패턴: <fmt:formatNumber value="${price }" pattern="##,###.00"/>
	<br/>
	<fmt:parseNumber value="1,234,567.00" pattern="#,###,###.00" var="num"/>${num }
	
	
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>