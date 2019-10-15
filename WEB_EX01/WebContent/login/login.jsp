<%--
  /**
  * @Class Name : login.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2019.07.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2019.07.01
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>Insert title here</title>
<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/js/jquery-ui.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="/WEB_EX01/js/bootstrap.min.js"></script>	
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- main form -->
			<form method="post" name="mainForm" id="mainForm" action="/main/main.do">
				<input type="hidden" name="work_div">
			</form>
			<!-- //main form -->
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Login</h1>
				<div class="account-wall">
					<form action="<c:url value='/member/member.json' />" class="form-signin" method="post" 
					   name="loginForm" id="loginForm" >
						<input type="hidden" name="work_div" value="do_login" id="work_div">
						<input type="text"  name="user_id" id="user_id" class="form-control"
						  placeholder="ID"  autofocus="autofocus" />
						<input  type="password" name="password" id="password" class="form-control"
						  placeholder="Password">  
					</form>
					<button class="btn btn-lg btn-primary btn-block"  id="do_idFind">Find</button>
					<button class="btn btn-lg btn-primary btn-block"  id="do_login">Sign in</button>
				</div>
			</div>
		
		</div>
	</div>
	<script>

		function setChildValue(id){
			console.log('id:'+id);
			console.log('password:'+password);
			$("#user_id").val(id);	
			$("#password").val(password);
		}
		
		$("#do_idFind").on("click", function(){
			//console.log('find');
			var title = "제목";
			var xWidth = window.screen.width/2;
			var xHeight = window.screen.height/2;
			
			console.log(xWidth);
			console.log(xHeight);
			
			var pX = xWidth - 600/2;
			var pY = xHeight - 400/2;
			
 			var status = "toolbar=no, directories=no, scrollabs=no, resizable=no, status=no, menubar=no, width=600, height=400, top="+pY+", left="+pX;
			
 			frm = document.mainForm;
 			
			window.open("", title, status);
			frm.target = title;
			frm.method = "post";
			frm.action="<c:url value='/member/member.do?work_div=do_retrieve'/>";
			frm.submit(); 
		});
		//메인페이지로 이동
		function goMain(){
			//alert('goMain');
			var frm = document.mainForm;
			frm.work_div.value='do_retrieve';
			frm.action="<c:url value='/board/board.do' />";
			frm.submit();
		}
	
		function goLogin(){
			//alert('goLogin');
			    console.log("user_id:"+$("#user_id").val());
			    console.log("password:"+$("#password").val());
			    console.log("work_div:"+$("#work_div").val());
			    
				$.ajax({
					type : "POST",
					url : "<c:url value='/member/member.json' />",
					dataType : "html",
					data : {
						"work_div" : "do_login",
						"user_id" :  $("#user_id").val(),
						"password" :   $("#password").val()
				},
				success : function(data) {
					console.log('data:'+data);
					var jData = JSON.parse(data);
					var msgId = jData.msgId;
					var msgContents = jData.msgContents;					
					if("10"==msgId) {//id확인
						alert(msgContents);
						$("#user_id").focus();
						return;
					}else if("20"==msgId){//비번확인
						alert(msgContents);
						$("#password").focus();
						return;
					}else if("1"==msgId){//성공
						goMain();
					}else{
						alert(msgContents);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  			
		}
	
		$("#do_login").on("click",function(){
			goLogin();
		});
	
		$(document).ready(function(){
			
		});
		
		
	</script>
</body>
</html>