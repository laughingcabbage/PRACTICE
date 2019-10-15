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
<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%

	Logger LOG = Logger.getLogger(this.getClass());
	String uploadPath = request.getRealPath("/upload");
	
	/*
	//uploadPath:E:\Java_20190415\02_ORACLE\workspace_web\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WEB_EX01\\upload
	*/
	
	int size = 1024*1024*50;; //50메가라는 뜻
	
	LOG.debug("================================");
	LOG.debug("uploadPath:"+uploadPath);
	LOG.debug("================================");
	
	/*
	request를 uploadPath의 경로에 
	50메가 사이즈까지 
	utf-8로 
	renamePolicy에 따라
	저장
	*/
	
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
	
	String name = multi.getParameter("name");
	String title = multi.getParameter("title");
	
	//올린사람, 제목
	LOG.debug("================================");
	LOG.debug("name:"+name);
	LOG.debug("title:"+title);
	LOG.debug("================================");
	
	//파일 이름들
	Enumeration files = multi.getFileNames();
	
	while(files.hasMoreElements()){
		String file = (String) files.nextElement();	
		String fileName = multi.getFilesystemName(file);
		String orgFile = multi.getOriginalFileName(file);
		
		LOG.debug("-------------------------");
		LOG.debug("file="+file);
		LOG.debug("fileName="+fileName);
		LOG.debug("orgFile="+orgFile);
		LOG.debug("-------------------------");
	}
%>
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
	<h3></h3>
	<hr/>
	<script>
		$(document).ready(function(){

		});
	</script>
	<script src="/WEB_EX01/js/bootstrap.min.js"></script>
</body>
</html>