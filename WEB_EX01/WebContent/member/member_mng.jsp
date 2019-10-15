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
<%@page import="com.hr.member.HrMemberVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="com.hr.code.CodeDao"%>
<%@page import="java.util.List"%>
<%@page import="com.hr.code.CodeVO"%>
<%
	HrMemberVO vo = (HrMemberVO) request.getAttribute("vo");
	List<CodeVO> list = (List<CodeVO>)request.getAttribute("lvlList");
	String level = "1";
	if(null!=vo){
		level=vo.getLvl();
	}
	out.print(vo);
%>
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
</head>
<body>
	<h3>회원관리</h3>
	<hr/>
	<!-- button area -->
		<div>
			<input type="button" value="초기화" id="reset_btn" />
			<input type="button" value="등록"  id="save_btn" />
			<input type="button" value="수정"  id="update_btn" />
			<input type="button" value="삭제"  id="del_btn" />
		</div>
	<!-- //button area -->
	<!-- 회원관리 form -->
		<form name="frmMng" id="frmMng" action="/WEB_EX01/member/member.do" method="post">
		<input type="hidden" name="work_div" id="work_div">
		<label>아이디</label>
		<div><input type="text" readonly="readonly" value="${vo.getUserId()}" name="user_id" id="user_id" size="20" maxlength="20" placeholder="영문, 숫자, _만 입력"/></div>
		<label>이름</label>
		<div><input type="text" value="${vo.getName()}" name="name" id="name" size="30" maxlength="30"/></div>
		<label>비번</label>
		<div><input type="password" value="${vo.getPassword() }" name="passwd" id="passwd" size="20" maxlength="20"/></div>
		<label>이메일</label>
		<div><input type="text" value="${vo.getEmail() }" name="email" id="email" size="150" maxlength="320"/></div>
		<label>생년월일</label>
		<div><input type="text" value="${vo.getBirth() }" name="birth" id="birth" size="8" maxlength="8"/></div>
		<label>핸드폰</label>
		<div><input type="text" value="${vo.getCellPhone() }" name="cell_phone" id="cell_phone" size="13" maxlength="13" placeholder="000-0000-0000"/></div>
		<label>성별</label>
		<div>
			<label><input type="radio" name="sex" id="male" value="M" <%if(null!=vo && vo.getGender().equals("M")) out.print("checked='checked' ");%>  />남자</label>
			<label><input type="radio" name="sex" id="female" value="F" <%if(null!=vo && vo.getGender().equals("F")) out.print("checked='checked' ");%>/>여자</label>
		</div>
		<label>등급</label>
		<div>
<!-- 			<select name="lvl" id="lvl">
				<option value="1">일반사용자</option>
				<option value="9">관리자</option>
			</select> -->
			<%=StringUtil.makeSelectBox(list, "lvl", level, false)%>
		</div>
		<label>등록자ID</label>
		<div><input type="text" value="${vo.getRegId() }" name="reg_id" id="reg_id" size="20" maxlength="20" readonly="readonly"/></div>
		<label>등록일</label>
		<div><input type="text" value="${vo.getRegDt() }" name="reg_dt" id="reg_dt" size="10" maxlength="10" readonly="readonly" /></div>
		<label>수정자ID</label>
		<div><input type="text" value="${vo.getModId() }" name="mod_id" id="mod_id" size="20" maxlength="20" readonly="readonly"/></div>
		<label>수정일</label>
		<div><input type="text" value="${vo.getModDt() }" name="mod_dt" id="mod_dt" size="10" maxlength="10" readonly="readonly"/></div>
	<!-- //회원관리 form -->
	</form>
	<script>
		//삭제function
		function doDel(){
			if(false==confirm('삭제하시겠습니까?')) return;
			var frm = document.frmMng;
			frm.work_div.value="do_delete";
			frm.action = "/WEB_EX01/member/member.do";
			frm.submit();
		}
		//삭제 버튼이벤트
		$("#del_btn").on("click",function(){
			console.log('del_btn');
			console.log($("#user_id").val());
			//validation
			var userId = $("#user_id").val();
			if(null==userId || userId.trim().length==0){
				$("#user_id").focus();
				alert('사용자ID를 입력하세요.');
				return;
			}
			
			if(false==confirm(userId+'을(를) 삭제하시겠습니까?')) return;
			
			//ajax
			$.ajax({
					type:"POST",
					url:"/WEB_EX01/member/member.do",
					dataType:"html",
					data:{
						"work_div":"do_delete",
						"user_id":$("#user_id").val()
				},
				success: function(data){
					var jData = JSON.parse(data);
					console.log(jData.msgId+"|"+jData.msgContents);
					if(null!= jData && jData.msgId==1){
						alert(jData.msgContents);
						window.location.href="/WEB_EX01/member/member.do?work_div=do_retrieve";
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
		
		$("#update_btn").on("click", function(){
			//alert('update');	
			//validation:필수 값들 체크
			var userId = $("#user_id").val();
			if(null==userId || userId.trim().length==0){
				$("#user_id").focus();
				alert('사용자ID를 입력하세요.');
				return;
			}
			
			//"work_div":"do_update"
			$("#work_div").val("do_update"); //work_div를 do_update로 set
			var param = $("#frmMng").serialize(); //form의 값을 모두 다 가져오는 것
			alert(param);
			
			if(false==confirm(userId+'을(를) 수정하시겠습니까?')) return;
			
			//ajax
			   $.ajax({
					     type:"POST",
					     url:"/WEB_EX01/member/member.do",
					     dataType:"html",
					     data:param,
					success: function(data){
							var jData = JSON.parse(data);
					     	if(null != jData && jData.msgId=="1"){
					      	alert(jData.msgContents);
					      	window.location.href="/WEB_EX01/member/member.do?work_div=do_retrieve";
					     	}else{
					      	alert(jData.msgId+"|"+jData.msgContents);
					     	}
					},
					complete:function(data){
					     
					},
					error:function(xhr,status,error){
					     alert("error:"+error);
					}
				}); 
			//--ajax
		});
		
		$("#save_btn").on("click", function(){
			//validation:필수 값들 체크
			var userId = $("#user_id").val();
			if(null==userId || userId.trim().length==0){
				$("#user_id").focus();
				alert('사용자ID를 입력하세요.');
				return;
			}
			
			//"work_div":"do_insert"
			$("#work_div").val("do_insert"); //work_div를 set
			var param = $("#frmMng").serialize(); //form의 값을 모두 다 가져오는 것
			alert(param);
			
			if(false==confirm(userId+'을(를) 등록하시겠습니까?')) return;
			
			//ajax
			   $.ajax({
					     type:"POST",
					     url:"/WEB_EX01/member/member.do",
					     dataType:"html",
					     data:param,
					success: function(data){
							var jData = JSON.parse(data);
					     	if(null != jData && jData.msgId=="1"){
					      	alert(jData.msgContents);
					      	window.location.href="/WEB_EX01/member/member.do?work_div=do_retrieve";
					     	}else{
					      	alert(jData.msgId+"|"+jData.msgContents);
					     	}
					},
					complete:function(data){
					     
					},
					error:function(xhr,status,error){
					     alert("error:"+error);
					}
				}); 
			//--ajax
		});
		
		
		//초기화
		$("#reset_btn").on("click", function(){
			$("#user_id").removeAttr("readonly");
			$("#reg_id").removeAttr("readonly");
			//user_id를 ""으로 set
			$("#user_id").val("");
			$("#name").val("");
			$("#passwd").val("");
			$("#email").val("");
			$("#birth").val("");
			$("#cell_phone").val("");
			$("#reg_id").val("");
			
		});
		
		$(document).ready(function(){
			console.log('mode:'+'${mode}');
			
			if(null!='${mode}' && 'insert'=='${mode}'){
				console.log('2 mode:'+'${mode}');
				$("#user_id").removeAttr("readonly");
				$("#reg_id").removeAttr("readonly");
			}
		});
		
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>