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
<!-- jquery validation -->
<script src="/WEB_EX01/js/jquery.validate.js"></script>
<script src="/WEB_EX01/js/additional-methods.js"></script>
<script src="/WEB_EX01/js/messages_ko.js"></script>

</head>
<body>
	<h3>validate.js</h3>
	<hr/>
	<form id="mngFrm">
		<input type="text" name="tet01" required="required" /><br/>
		<input type="text" name="tet02"  /><br/>
		<input type="text" name="tet03" required="required" /><br/>
		<input type="submit" value="저장" />
	</form>
	<script>
		$(document).ready(function(){
			$("form").validate();
		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>