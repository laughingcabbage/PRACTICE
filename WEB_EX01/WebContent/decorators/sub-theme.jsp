<%@page import="com.hr.member.HrMemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%
    HrMemberVO user = (HrMemberVO)session.getAttribute("user");
    //out.print(user);
%>	


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style type="text/css">
 body {
        text-align: center;
        color: #FFF;
        width: 750px;
        min-width: 992px
    } 
    div#wapper {
        text-align: center;
        color: #FFF;
        width: 750px;
        min-width: 992px
    }
    header, footer, nav, aside, section {
        border: 1px solid #999;
        margin: 5px;
        padding: 10px;
    }
    nav {
        height: 50px;
        background-color: red;
    }
    section {
        float: left;
        min-height: 650px;
    }

    section {
        /* background-color: goldenrod;    */
        width: 868px;
    }
    footer {
        height: 50px;
        /* background-color: goldenrod; */
        position: relatiev;
        clear: both;
    }
    article {
        width: 90%;
        margin: 20px;
        /* background-color: #999; */
    }
</style>
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
<script >
	function doLogout(){
		if(false == confirm("로그아웃 하시겠습니까?"))return;
		var frm =document.topForm;
		frm.work_div.value='do_logout';
		frm.action="<c:url value='/member/member.json' />";
		frm.submit();
	}
</script>
</head>
<body>
	
    <div id="wapper">
    <form action="" name="topForm" id="topForm" method="post" >
    	<input type="hidden" name="work_div" />
    </form>
	    <!-- top -->
	    <nav class="navbar navbar-default">
	    	<div class="container-fluid">
	    		<div class="navbar-header">
	    			<a class="navbar-brand" href="#">HR_JSP</a>
	    		</div>
	    		<!-- menu link -->
	    		<ul class="nav navbar-nav">
	    			<li class="nav-item" >
	    			 <a href="/WEB_EX01/board/board.do?work_div=do_retrieve" class="nav-link">게시판</a>
	    			</li>
	    		</ul>
	    		<ul class="nav navbar-nav">
	    			<li class="nav-item" >
	    			 <a href="/WEB_EX01/member/member.do?work_div=do_retrieve" class="nav-link">회원관리</a>
	    			</li>
	    		</ul>    		
	    		<!--// menu link -->
	    		<ul class="nav navbar-nav navbar-right">
	    			<li><a href="#"><span class="glyphicon glyphicon-user"></span><c:out value="${name}" /></a></li>
	    			<li><a href="javascript:doLogout();"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	    		</ul>
	    	</div>
	    </nav><!--// top -->
	    
	    <!--콘텐츠부분-->
        <section>
        		<decorator:body />
        </section><!--//콘텐츠부분-->
    </div> 
    
    <!--풋터-->
    <footer>footer</footer>
</body>
</html>