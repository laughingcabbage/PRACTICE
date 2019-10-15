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
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
Logger LOG=Logger.getLogger(this.getClass());
//원본파일명:abc.txt
String orgFileNm = StringUtil.nvl(
		            String.valueOf(request.getParameter("org_filenm")),"");
LOG.debug("orgFileNm:"+orgFileNm);
//저장파일명: save_filenm=c:\TEMP\abc01.txt&org_filenm=abc.txt
//http://localhost:8080/WEB_EX01/comm/download.jsp?save_filenm=c:\TEMP\abc01.txt&org_filenm=abc.txt		
String saveFileNm = StringUtil.nvl(
		            String.valueOf(request.getParameter("save_filenm")),"");	
//Input output Stream사용 파일 다운로드
LOG.debug("saveFileNm:"+saveFileNm);

InputStream   in  = null;
OutputStream  outputStream = null;
File          file  = null;
String        client="";
boolean       skip  = false;

try{
	//In/Out+브라우저
	try{
		file = new File(saveFileNm);//full path+저장파일명
		in   = new FileInputStream(file);
	}catch(FileNotFoundException fn){
		LOG.debug("FileNotFoundException:"+fn.toString());
		skip = true;
	}
	//브라우저
	client = request.getHeader("User-Agent");
	//Chrome: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36
	//ie    : Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko
	LOG.debug("client:"+client);
	
	//buffer clear
	response.reset();
	LOG.debug("skip:"+skip);
	if(!skip){//파일이 존재
		// octet-stream: 8bit 바이너리 배열을 의미  http,mail에서 application 형식이
		// 지정이 않된 경우나, 모를 경우사용.
		// octet-stream MIME 타입으로 지정되면 : 바이너리 데이터로  다운로드 가능하도록 처리 
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Description", "JSP Generated Data");
		
		out.clear();
		out=pageContext.pushBody();
		
		//IE
		if(client.indexOf("Trident") !=-1){
			//저장파일명->원본파일명으로 교체: %20(SPACE)
			//response.setHeader("Content-Disposition","attachment; filename="+URLEncoder.encode(orgFileNm,"UTF-8").replaceAll("\\+", "%20"));
			
		//IE이외 브라우저	
		}else{
			//한글파일명
			orgFileNm = new String(orgFileNm.getBytes("utf-8"),"iso-8859-1");
			response.setHeader("Content-Disposition","attachment; filename=\""+orgFileNm+"\"");
			response.setHeader("Content-Type","application/octet-stream; charset=utf-8");
		}
		
		response.setHeader("Content-Length",String.valueOf(file.length()));
		outputStream = response.getOutputStream();
		
		byte b[]=new byte[(int)file.length()];
		int leng = 0;
		while( (leng = in.read() )>0){
			outputStream.write(leng);
		}
		outputStream.flush();
		
		
	}else{//파일이 존재하지 않으면
		response.setContentType("text/html;charset=UTF-8");
	    out.println("<script>alert('파일명을 찾을수 없습니다.');history.back();</script>");
	}
	
	
}catch(Exception e){
	LOG.debug("Exception:"+e.toString());
}finally{
	if(null !=in){
		in.close();
	}
	
	if(null !=outputStream){
		outputStream.close();
	}	
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