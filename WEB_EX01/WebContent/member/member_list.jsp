<%--
  /**
  * @Class Name : useOutObject.jsp
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
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="com.hr.code.CodeVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hr.member.SearchVO"%>
<%@page import="com.hr.member.HrMemberVO"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	//화면목록
	List<HrMemberVO> list = (List<HrMemberVO>)request.getAttribute("list");

	//Param
    String pageNum    = "1";
    String searchDiv  = "";
    String searchWord = "";
    String pageSize   = "10";
    
    //paging
    int maxNum			= 0; 							//총 글 수(server에서 받아와야 하는 값)
    int currPageNo		= 1;							//현재페이지(pageNum과 같음)
    int rowPerPage		= 10;							//row수(pageSize와 같음)
    int bottomCount		= 10;							//bottomCount(고정)
    String url			= "/WEB_EX01/member/member.do";	//호출URL
    String scriptName	= "doSearchPage";				//Javascript함수명
    
	SearchVO paramVO = (SearchVO)request.getAttribute("paramVO");
	//out.print(paramVO);
	if(null != paramVO){
	     pageNum    = paramVO.getPageNum()+"";
	     searchDiv  = paramVO.getSearchDiv();
	     searchWord = paramVO.getSearchWord();
	     pageSize   = paramVO.getPageSize()+"";		
	     LOG.debug("===========================");
	     LOG.debug("paramVO="+paramVO);
	     LOG.debug("===========================");
		 rowPerPage=Integer.parseInt(pageSize);
		 currPageNo=Integer.parseInt(pageNum);
		 maxNum = (Integer) request.getAttribute("totalCnt");
	}
	
	
%>    
<!DOCTYPE html>
<html>
<head>
  <style type="text/css">
   body {
    font-family: Arial, Verdana, sans-serif;
    color: #111111;}
   table {
    width: 90%;}
   th, td {
    padding: 7px 10px 10px 10px;}
   th {
    text-transform: uppercase;
    letter-spacing: 0.1em;
    font-size: 90%;
    border-bottom: 2px solid #111111;
    border-top: 1px solid #999;
    text-align: left;}
   tr.even {
    background-color: #efefef;}
   tr:hover {
    background-color: #c3e6e5;}
   .money {
    text-align: right;}
  </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/WEB_EX01/js/jquery-ui.css" >
<title>Insert title here</title>
<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/js/jquery-ui.js"></script>
</head>
<body>
	<h3>Member관리</h3>
	<hr/>
	<!-- 버튼 -->
	<div>
		<input type="button" value="조회" onclick="javascript:do_retrieve();" />
		<input type="button" value="등록" onclick="javascript:do_save_move();" />
	</div>
	<!--// 버튼 -->
	
	
	<form name="searchFrm" action="/WEB_EX01/member/member.do" method="post" >
		<input type="hidden"  name="work_div"   />
		<input type="hidden"  name="page_num"   />
		<!-- 검색영역 -->
		<table>
			<tr>
				<td>
					<div>구분
						<select name="search_div" id="search_div">
							<option value="">전체</option>
							<option value="10" <%if(searchDiv.equals("10"))out.print("selected"); %> >이름</option>
							<option value="20" <%if(searchDiv.equals("20"))out.print("selected"); %> >이메일</option>
							<option value="30" <%if(searchDiv.equals("30"))out.print("selected"); %> >ID</option>
						</select>
						<input type="text"  name="search_word" id="search_word" />
						<select name="page_size" id="page_size">
							<option value="10"  <%if(pageSize.equals("10"))out.print("selected"); %> >10</option>
							<option value="50"  <%if(pageSize.equals("50"))out.print("selected"); %>>50</option>
							<option value="100" <%if(pageSize.equals("100"))out.print("selected"); %>>100</option>
							<option value="200" <%if(pageSize.equals("200"))out.print("selected"); %>>200</option>
						</select>
					</div>
				</td>
			</tr>
		</table>
		<!--// 검색영역 -->
		
		<!-- data list -->
		<table id="listTable">
			<thead>
				<tr>
					<th>NO</th>
					<th>아이디</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>이메일</th>
					<th>전화</th>
					<th>등급</th>
					<th>등록일</th>					
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && list.size()>0){
						for(HrMemberVO vo :list){
				%>
						<tr>
							<td><%=vo.getNum() %></td>
							<td><%=vo.getUserId() %></td>
							<td><%=vo.getName() %></td>
							<td><%=vo.getBirth() %></td>
							<td><%=vo.getEmail() %></td>
							<td><%=vo.getCellPhone() %></td>
							<td><%=vo.getLvl() %></td>
							<td><%=vo.getModDt() %></td>							
						</tr>
				<%	
					    }//--for 
					}else{  
				%>
						<tr>
							<td colspan="99">No data found</td>
						</tr>
				
				<%	}//--else %>
			</tbody>
		</table>
		
		<!--// data list -->
		
	</form>
	<!-- paging -->
	<div>
		<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
	</div>
	<!-- //paging -->
	<script>
	
		var user_id;
		$("#listTable>tbody").on("click", "tr", function(){
			if(window.event.ctrlKey){
				var cTrs = $(this);
				var cTds = cTrs.children();
				//console.log(cTds);
				var user_id = cTds.eq(1).text();
				close_window(user_id);
			}
		});
		
		function close_window(){
			//alert('close');	
			window.opener.setChildValue(user_id);
			window.self.close();
		}
		
		//page이동
		function doSearchPage(url,page_num){
			//alert(url+","+page_num);
			var frm = document.searchFrm;
			frm.work_div.value = "do_retrieve";
			frm.page_num.value = page_num;
			frm.action = url;
			frm.submit();
		}
		
		//등록화면으로 이동
		function do_save_move(){
			//alert('do_save_move');
			if(confirm("등록하시겠습니까?")==false) return;
			var frm = document.searchFrm;
			frm.work_div.value = 'do_save_move';
			frm.action = '/WEB_EX01/member/member.do';
			frm.submit();
		}
		
		//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.searchFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = '1';
			frm.action         = '/WEB_EX01/member/member.do';
			
			//alert('frm.work_div.value:'+frm.work_div.value);
			frm.submit();
		}	
		
		//수정화면으로 이동:servlet
		$("#listTable>tbody").on("dblclick", "tr", function(){
			var cTrs = $(this);
			var cTds = cTrs.children();
			console.log(cTds);
			var userId = cTds.eq(1).text();
			console.log(userId);
			
			var frm = document.searchFrm;
			frm.work_div.value="do_selectone";
			frm.action='/WEB_EX01/member/member.do?user_id='+userId;
			frm.submit();
		});
		
		$("#search_word").keypress(function(e){
			console.log("search_word:"+e);
			if(e.which == 13){//enter
				do_retrieve();
			}
		});
		
	</script>
</body>
</html>