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
<%@page import="com.hr.board.BoardVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="com.hr.code.CodeDao"%>
<%@page import="java.util.List"%>
<%@page import="com.hr.code.CodeVO"%>

<%
	//절대경로:<%=application.getRealPath("board_mng.jsp")
	BoardVO vo = (BoardVO) request.getAttribute("vo");
	List<CodeVO> list = (List<CodeVO>)request.getAttribute("lvlList");
	//out.print(vo);
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
	<h3>BOARD</h3>
	<hr/>
	<!-- button area -->
		<div>
			<input type="button" value="초기화" id="reset_btn" />
			<input type="button" value="조회" id="listTo_btn" />
			<input type="button" value="등록"  id="save_btn" />
			<input type="button" value="수정"  id="update_btn" />
			<input type="button" value="삭제"  id="del_btn" />
		</div>
	<!-- //button area -->
	<!-- 회원관리 form -->
		<form name="frmMng" id="frmMng" action="/WEB_EX01/board/board.do" method="post">
		<input type="hidden" name="work_div" id="work_div">
		<input type="hidden" name="seq" id="seq" value="${vo.seq}">
		<label>제목</label>
		<div><input type="text" value="${vo.title}" name="title" id="title" size="30" maxlength="100" /></div>
		<label>작성자</label>
		<div><input type="text" value="${vo.regId}" name="reg_id" id="reg_id" size="30" maxlength="30"/></div>
		<label>등록일</label>
		<div><input type="text" value="${vo.regDt }" name="reg_dt" id="reg_dt" size="8" maxlength="8"/></div>
		<label>조회수</label>
		<div><input type="text" value="${vo.readCnt}" name="read_cnt" id="read_cnt" size="8" maxlength="8"/></div>
		<label>내용</label>
		<div><textarea cols="40" rows="5" name="contents" id="contents">${vo.contents }</textarea></div>
	<!-- //회원관리 form -->
	</form>
	<script>
		
		//목록으로 돌아가기function
		function moveToList(){
			if(false==confirm('조회로 이동하시겠습니까?')) return;
			
			var frm = document.frmMng;
			frm.work_div.value="do_move_to_list";
			frm.action="/WEB_EX01/board/board.do";
			frm.submit();
		}
		
		//목록으로 돌아가기
		$("#listTo_btn").on("click", function(){
			moveToList();
		});
		
		//삭제function
		function doDel(){
			if(false==confirm('삭제하시겠습니까?')) return;
			var frm = document.frmMng;
			frm.work_div.value="do_delete";
			frm.action = "/WEB_EX01/board/board.do";
			frm.submit();
		}
		
		//삭제 버튼이벤트
		$("#del_btn").on("click",function(){
			console.log('del_btn');
			console.log($("#seq").val());
			//validation
			var userId = $("#seq").val();
			
			if(false==confirm($("#title").val()+'을(를) 삭제하시겠습니까?')) return;
			
			//ajax
			$.ajax({
					type:"POST",
					url:"/WEB_EX01/board/board.do",
					dataType:"html",
					data:{
						"work_div":"do_delete",
						"seq":$("#seq").val()
				},
				success: function(data){
					var jData = JSON.parse(data);
					console.log(jData.msgId+"|"+jData.msgContents);
					if(null!= jData && jData.msgId==1){
						alert(jData.msgContents);
						window.location.href="/WEB_EX01/board/board.do?work_div=do_retrieve";
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
			var title = $("#title").val();
			if(null==title || title.trim().length==0){
				$("#title").focus();
				alert('제목을 입력하세요.');
				return;
			}
			
			var contents = $("#contents").val();
			if(null==contents || contents.trim().length==0){
				$("#contents").focus();
				alert('내용을 입력하세요.');
				return;
			}
			
			//"work_div":"do_update"
			$("#work_div").val("do_update"); //work_div를 do_update로 set
			var param = $("#frmMng").serialize(); //form의 값을 모두 다 가져오는 것
			alert(param);
			
			if(false==confirm(title+'을(를) 수정하시겠습니까?')) return;
			
			//ajax
			   $.ajax({
					     type:"POST",
					     url:"/WEB_EX01/board/board.do",
					     dataType:"html",
					     data:param,
					success: function(data){
							var jData = JSON.parse(data);
					     	if(null != jData && jData.msgId=="1"){
					      	alert(jData.msgContents);
					      	window.location.href="/WEB_EX01/board/board.do?work_div=do_retrieve";
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
			var title = $("#title").val();
			if(null==title || title.trim().length==0){
				$("#title").focus();
				alert('제목을 입력하세요.');
				return;
			}
			
			var contents = $("#contents").val();
			if(null==contents || contents.trim().length==0){
				$("#contents").focus();
				alert('내용을 입력하세요.');
				return;
			}
			
			//"work_div":"do_update"
			$("#work_div").val("do_insert"); //work_div를 set
			var param = $("#frmMng").serialize(); //form의 값을 모두 다 가져오는 것
			//alert(param);
			
			if(false==confirm(title+'을(를) 등록하시겠습니까?')) return;
			
			//ajax
			   $.ajax({
					     type:"POST",
					     url:"/WEB_EX01/board/board.do",
					     dataType:"html",
					     data:param,
					success: function(data){
							var jData = JSON.parse(data);
					     	if(null != jData && jData.msgId=="1"){
					      	alert(jData.msgContents);
					      	window.location.href="/WEB_EX01/board/board.do?work_div=do_retrieve";
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
			if(false==confirm('초기화하시겠습니까?')) return;
			
			$("#title").val("");
			$("#reg_id").val("");
			$("#reg_dt").val("");
			$("#read_cnt").val("");
			$("#contents").val("");
		});
		
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>