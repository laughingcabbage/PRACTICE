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
	아이디:<input type="text" name="user_id" id="user_id"/><br/>
	비밀번호:<input type="password" name="password" id="password"/><br/>
	비밀번호 확인:<input type="password" name="repassword" id="repassword"/><br/>
	나이:<input type="text" name="age" id="age"/><br/>
	이메일:<input type="text" name="email" id="email"/><br/>
	홈페이지:<input type="text" name="url" id="url"/><br/>
		<input type="submit" value="저장" />
	</form>
	<script>
		$(document).ready(function(){
			$("form").validate({
				//validation 종료 후 submit직전에 할 작업
				submitHandler:function(){
					var f = confirm('회원가입 하시겠습니까?');
					if(f){
						return true;
					}else{
						return false;
					}
				},
				//규칙
				rules:{
					user_id:{
						required:true,
						minlength:5
					},
					password:{
						required:true,
						minlength:3
					},
					repassword:{
						required:true,
						equalTo:password
					},
					age:{
						digits:true
					},
					email:{
						required:true,
						minlength:5,
						email:true
					},
					url:{
						url:true
					}
				},
				//룰이 어긋났을 때 출력 메시지
				message:{
					user_id:{
						required:'필수 입력값입니다.',
						minlength:'최소{0}글자 이상이어야 합니다.'
					},
					password:{
						required:'필수 입력값입니다.',
						minlength:'최소{0}글자 이상이어야 합니다.'
					},
					repassword:{
						required:'필수 입력값입니다.',
						minlength:'최소{0}글자 이상이어야 합니다.',
						equalTo:'비밀번호를 확인하세요.'
					},
					age:{
						digits:'숫자만 입력 가능합니다.'
					},
					email:{
						required:'필수 입력값입니다.',
						minlength:'최소{0}글자 이상이어야 합니다.',
						email:'유효하지 않은 E-Mail 주소입니다.'
					},
					url:{
						url:'유효하지 않은 URL입니다.'
					}
				}
			});
		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>