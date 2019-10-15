<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
      							work_div:"do_line_chart"
      						},
      						async:false //동기
      	}).responseText;
    	
    	console.log("1."+jsonArray);
    	
    	var jsonArrayObject = JSON.parse(jsonArray);
    	
    	console.log("2."+jsonArrayObject);
    	
		var data = new google.visualization.DataTable();
		
		data.addColumn('string', 'Year');
		data.addColumn('number', 'Sales');
		data.addColumn('number', 'Expenses');
		
		for(var i=0; i<jsonArrayObject.length; i++){
			data.addRow(jsonArrayObject[i]);
			console.log('jsonArrayObject[i]:'+jsonArrayObject[i]);
		}
 
        var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>