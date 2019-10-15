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
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->    
	<link rel="stylesheet" href="/WEB_EX01/js/jquery-ui.css" >
	<!-- 부트스트랩 -->
    <link href="/WEB_EX01/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/WEB_EX01/image/favicon.ico">
<title>게시관리</title>
<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/js/jquery-ui.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</head>
<body>
	<h3></h3>
	<hr/>
	
	
	<form class="form-horizontal" name="formMailFrm" id="formMailFrm" role="form" method="post" action=<c:url value='/mail/mail.do' />>
		<input type="hidden" name="work_div" value="do_send_mail" />
		<div class="form-group">
		<label for="name" class="col-sm-2 control-label">제목</label>
		<div class="col-sm-9">
		<input type="text" class="form-control" id="title" name="title" placeholder="제목" value="">
		</div>
		</div>
		<div class="form-group">
		<label for="email" class="col-sm-2 control-label">받는사람</label>
		<div class="col-sm-9">
		<input type="email" class="form-control" id="email" name="email" placeholder="example@domain.com" value="">
		</div>
		</div>
		<div class="form-group">
		<label for="message" class="col-sm-2 control-label">Message</label>
		<div class="col-sm-9">
		<textarea class="form-control" rows="4" name="message" id="message"></textarea>
		</div>
		</div>
		<div class="form-group">
		<div class="col-sm-9 col-sm-offset-2">
		<! Will be used to display an alert to the user>
		</div>
		</div>
	</form>
	<input id="mailSend" name="mailSend" type="button" value="Send" class="btn btn-primary" />
	<script>
		$("#mailSend").on("click", function(){
			//alert('mailsend');
			
			var title = $("#title").val();
			if(null==title || title.trim().length == 0){
				$("#title").focus();
				alert('제목을 입력하세요.');
				return;
			}
			
			var email = $("#email").val();
			if(null==email || email.trim().length == 0){
				$("#email").focus();
				alert('받는 사람의 이메일을 입력하세요.');
				return;
			}
			
			var message = document.formMailFrm.message.value;
			
			if(false == confirm(email+"에 mail을 전송하시겠습니까?")) return;
			
			//ajax
			$.ajax({
					type:"POST",
					url:"<c:url value='/mail/mail.do' />",
					dataType:"html",
					data:{
						"work_div":"do_send_mail",
						"title":title,
						"email":email,
						"message":message
				},
				success: function(data){
					var jData = JSON.parse(data);
					console.log(jData.msgId+"|"+jData.msgContents);
					if(null!= jData && jData.msgId==1){
						alert(jData.msgContents);
					}else{
						alert(jData.msgId+"|"+jData.msgContents);
					}
					//alert("success:"+data);
				},
				complete: function(data){
					
				},
				error: function(xhr, status, error){
					alert("error:"+error);
				}
			});
			//--ajax
			
			
		});
		
		$(document).ready(function(){

		});
	</script>
</body>
</html>