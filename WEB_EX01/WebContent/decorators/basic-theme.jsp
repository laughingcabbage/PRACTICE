<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    			<li><a href="#"><span class="glyphicon glyphicon-user"></span> 홍길동</a></li>
    			<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    		</ul>
    		
    	</div>
    </nav>
    <!--// top -->
    <hr />
    <decorator:body />
    <hr />
    <h1>Footer</b></h1>
</body>
</html>