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
<%@page import="com.hr.board.BoardVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="com.hr.code.CodeVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hr.member.SearchVO"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	//화면목록
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");

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
    String url			= "/WEB_EX01/board/board.do";	//호출URL
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
	<h3>BOARD</h3>
	<hr/>
	<!-- 버튼 -->
	<div>
		<input type="button" value="조회" onclick="javascript:do_retrieve();" />
		<input type="button" value="등록" onclick="javascript:do_save_move();" />
	</div>
	<!--// 버튼 -->
	
	
	<form name="searchFrm" action="/WEB_EX01/board/board.do" method="post" >
		<input type="hidden"  name="work_div"   />
		<input type="hidden"  name="page_num"   />
		<!-- 검색영역 -->
		<table>
			<tr>
				<td>
					<div>구분
						<select name="search_div" id="search_div">
							<option value="">전체</option>
							<option value="10" <%if(searchDiv.equals("10"))out.print("selected"); %> >제목</option>
							<option value="20" <%if(searchDiv.equals("20"))out.print("selected"); %> >내용</option>
							<option value="30" <%if(searchDiv.equals("30"))out.print("selected"); %> >등록자ID</option>
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
					<th width="8%">NO</th>
					<th width="63%">제목</th>
					<th width="9%">등록자ID</th>
					<th width="11%">등록일</th>
					<th width="9%">조회수</th>
					<!-- visibility:hidden; 보이지는 않지만 공간을 차지함 -->
					<!-- display:none; 보이지 않고 공간 차지하지 않음 -->
					<th width="0%" style="display:none;">SEQ</th>					
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${list.size()>0}">
						<c:forEach var="vo" items="${list}">
							<tr>
								<td><c:out value="${vo.num}" /></td>
								<td><c:out value="${vo.title}" /></td>
								<td><c:out value="${vo.regId}" /></td>
								<td><c:out value="${vo.regDt}" /></td>
								<td><c:out value="${vo.readCnt}" /></td>	
								<td style="display:none;"><c:out value="${vo.seq}" /></td>								
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="99">No data found</td>
						</tr>
					</c:otherwise>
				</c:choose>
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
			frm.action = '/WEB_EX01/board/board.do';
			frm.submit();
		}
		
		//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.searchFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = '1';
			frm.action         = '/WEB_EX01/board/board.do';
			
			//alert('frm.work_div.value:'+frm.work_div.value);
			frm.submit();
		}	
		
		//수정화면으로 이동:servlet
		$("#listTable>tbody").on("dblclick", "tr", function(){
			var cTrs = $(this);
			var cTds = cTrs.children();
			console.log(cTds);
			var seq = cTds.eq(5).text();
			console.log("seq:"+seq);
			
			var frm = document.searchFrm;
			frm.work_div.value="do_selectone";
			frm.action='/WEB_EX01/board/board.do?seq='+seq;
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