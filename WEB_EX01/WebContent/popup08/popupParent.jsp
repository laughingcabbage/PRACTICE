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
	<h3>popup parent</h3>
	<hr/>
	<form action="" method="post" name="frmData" id="frmData">
		<input type="text" name="name" id="name" value="이상무"/><br/>
		<input type="text" name="id" id="id" value="james"/><br/>	
		<input type="button" onclick="javascript:openPopup(this.form);" value="콜팝업" />
		<input type="button" onclick="javascript:centerPopup(this.form);" value="센터팝업" />
	</form>
	<script>
	
		function centerPopup(frm){
			//alert('popup');
			var title = "제목";
			var xWidth = window.screen.width/2;
			var xHeight = window.screen.height/2;
			
			console.log(xWidth);
			console.log(xHeight);
			
			var pX = xWidth - 240/2;
			var pY = xHeight - 200/2;
			
 			var status = "toolbar=no, directories=no, scrollabs=no, resizable=no, status=no, menubar=no, width=240, height=200, top="+pY+", left="+pX;
			
			window.open("", title, status);
			frm.target = title;
			frm.method = "get";
			frm.action="<c:url value='/popup08/popupChild.jsp'/>";
			frm.submit(); 
		}
	
		function setChildValue(name){
			console.log('name:'+name);
			$("#name").val(name);	
		}
		
		function openPopup(frm){
			//alert('popup');
			var title = "제목";
			var status = "toolbar=no, directories=no, scrollabs=no, resizable=no, status=no, menubar=no, width=240, height=200, top=0, left=20";
			
			window.open("", title, status);
			frm.target = title;
			frm.method = "get";
			frm.action="<c:url value='/popup08/popupChild.jsp'/>";
			frm.submit();
		}
		
		
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>