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
<style>
	#uploadArea{
		width  : 450px;
		border : 1px solid black;
		margin : auto;
	}
	.td_title{
		font-size: 20;
		text-align: center;
	}
</style>
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
	<h3></h3>
	<hr/>
	<div id="uploadArea" >
		<form action="/WEB_EX01/upload.do" method="post" enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<td colspan="2" class="td_title">UploadForm</td>
				</tr>
				<tr>
					<td><label for="name">올린 사람:</label></td>
					<td><input type="text" name="name" id="name"/></td>
				</tr>
				<tr>
					<td><label for="title">제목:</label></td>
					<td><input type="text" name="title" id="title"/></td>
				</tr>
				<tr>
					<td><label for="fileName01">파일명01:</label></td>
					<td><input type="file" name="fileName01" id="fileName01"/></td>
				</tr>
				<tr>
					<td><label for="fileName02">파일명02:</label></td>
					<td><input type="file" name="fileName02" id="fileName02"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="전송"></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>