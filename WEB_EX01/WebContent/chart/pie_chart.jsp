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
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
  	<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
		//json data call
    	var jsonArray = $.ajax({
    						url:"<c:url value='/google/chart.json'/>",
    						dataType:"html",
    						data:{
    							work_div:"do_pie_chart"
    						},
    						async:false //동기
    	}).responseText;
    	
		var jsonArrayObject = JSON.parse(jsonArray);
		console.log('jsonArrayObject:'+jsonArrayObject);
		
		var data = new google.visualization.DataTable();
		
		data.addColumn('string', 'label');
		data.addColumn('number', 'data');
		
		for(var i=0; i<jsonArrayObject.length; i++){
			data.addRow(jsonArrayObject[i]);
			console.log('jsonArrayObject[i]:'+jsonArrayObject[i]);
		}

        var options = {
          title: '무비차트'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>