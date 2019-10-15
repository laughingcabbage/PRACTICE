<%@page import="com.hr.board.BoardDao"%>
<%@page import="com.hr.board.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.hr.member.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SearchVO searchVO = new SearchVO();
	searchVO.setPageSize(10);
	searchVO.setPageNum(1);
	searchVO.setSearchDiv("10");
	searchVO.setSearchWord("제목");
	BoardDao dao = new BoardDao();
	List<BoardVO> list = (List<BoardVO>) dao.do_retrieve(searchVO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table, th, td{
		
		padding:5px;
		border: 1px solid black; /*테두리*/
		border-collapse: collapse; /*중복되는 테두리를 한 줄로 표시*/
	}
	
	thead, tfoot{
		background: #750A0A; /*배경색*/
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<h2>Table:thead, tbody, tfoot</h2>
		<hr/>
		<table border="1">
			<caption>게시판</caption>
			<thead>
				<tr>
					<th>NO</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			<% for(BoardVO vo : list){%>
			<tr>
				<td><%=vo.getNum()%></td>
				<td><%=vo.getTitle()%></td>
				<td><%=vo.getRegId()%></td>
				<td><%=vo.getRegDt()%></td>
			</tr>
			<% }%>
			</tbody>
		</table>
</body>
</html>