package com.hr.google.chart;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.hr.cmn.ConHandler;
import com.hr.cmn.StringUtil;
import com.hr.member.HrMemberService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoogleChartPieController
 */
@WebServlet(description = "파이차트", urlPatterns = { "/google/chart.json" })
public class GoogleChartPieController extends HttpServlet implements ConHandler {
	private static final long serialVersionUID = 1L;
	private HrMemberService hrMemberService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleChartPieController() {
        super();
        hrMemberService = new HrMemberService();
    }
    
    //http://localhost:8080/WEB_EX01/google/chart.json?work_div=do_yearFemaleRatio
    public void do_yearFemaleRatio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	LOG.debug("03 do_yearFemaleRatio");
    	
    	YearMemberRatioVO inVO = new YearMemberRatioVO();
    	List<YearMemberRatioVO> list = hrMemberService.do_yearFemaleRatio(inVO);
    	LOG.debug("01 list:"+list);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String gsonString = "";
    	
    	JsonArray jArray = new JsonArray();
    	for(int i=0; i<list.size(); i++){
    		JsonArray sArray = new JsonArray();
    		sArray.add(list.get(i).getYear());
    		sArray.add(list.get(i).getFemale());
    		
    		jArray.add(sArray);
    	}
    	gsonString = jArray.toString();
    	LOG.debug("gsonString:"+gsonString);
    	out.print(gsonString);
    	
    }
    
    //http://localhost:8080/WEB_EX01/google/chart.json?work_div=do_line_chart
    public void do_line_chart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	LOG.debug("03 do_line_chart");
    	
    	List<LineVO> list = new ArrayList<>();
    	LineVO outVO01 = new LineVO("2016",1000,400);
    	LineVO outVO02 = new LineVO("2017",1170,460);
    	LineVO outVO03 = new LineVO("2018",660,1120);
    	LineVO outVO04 = new LineVO("2019",1030,540);
    	
    	list.add(outVO01);
    	list.add(outVO02);
    	list.add(outVO03);
    	list.add(outVO04);
    	
    	//JSON: JsonArray
    	Gson gson = new Gson();
    	JsonArray jArray = new JsonArray();
    	
    	for(int i=0; i<list.size(); i++){
    		JsonArray sArray = new JsonArray();
    		sArray.add(list.get(i).getYear());
    		sArray.add(list.get(i).getSales());
    		sArray.add(list.get(i).getExpenses());
    		
    		jArray.add(sArray);
    	}
    	
    	LOG.debug("-----------------------------");
    	LOG.debug("-jArray.toString()-"+jArray.toString());
    	LOG.debug("-----------------------------");
    	
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	out.write(jArray.toString());
    }

    //http://localhost:8080/WEB_EX01/google/chart.json?work_div=do_pie_chart
    public void do_pie_chart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	LOG.debug("03 do_pie_chart");
    	
    	List<PieVO> list = new ArrayList<>();
    	PieVO outVO01=new PieVO("브링 더 소울 - 더 무비",28);
    	PieVO outVO02=new PieVO("엑시트",27);
    	PieVO outVO03=new PieVO("사자",9);
    	PieVO outVO04=new PieVO("마이펫의 이중생활2",8);
    	PieVO outVO05=new PieVO("알라딘",8);
    	PieVO outVO06=new PieVO("분노의 질주-홉스&쇼",5);
    	PieVO outVO07=new PieVO("라이온킹",5);
    	
    	list.add(outVO01);
    	list.add(outVO02);
    	list.add(outVO03);
    	list.add(outVO04);
    	list.add(outVO05);
    	list.add(outVO06);
    	list.add(outVO07);
    	
    	//JSON: JsonArray
    	Gson gson = new Gson();
    	JsonArray jArray = new JsonArray();
    	
    	for(int i=0; i<list.size(); i++){
    		JsonArray sArray = new JsonArray();
    		sArray.add(list.get(i).getLabel());
    		sArray.add(list.get(i).getData());
    		
    		jArray.add(sArray);
    	}
    	
    	LOG.debug("-----------------------------");
    	LOG.debug("-jArray.toString()-"+jArray.toString());
    	LOG.debug("-----------------------------");
    	
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	out.write(jArray.toString());
    	
    }
    
    public void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	LOG.debug("1------------------------");
    	
    	//work_div읽기
    			String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    			LOG.debug("02.1 workDiv:"+workDiv);
    			
    			/* 기능 
    			 * do_retrieve:목록
    			 * do_insert:등록
    			 * do_delete:수정
    			 * do_selectOne:단건조회
    			 * do_update:삭제
    			 */ 
    			
    			switch(workDiv){
    			
    				case "do_yearFemaleRatio":
    					do_yearFemaleRatio(request, response);
    				break;
    				
    				case "do_line_chart":
    					do_line_chart(request, response);
					break;
				
    				case "do_pie_chart":
    					do_pie_chart(request, response);
    				break;
    				//등록화면으로 이동
    				case "do_save_move":
    					do_save_move(request, response);
    				break;
    				
    				case "do_insert":
    					do_insert(request, response);
    				break;
    				
    				case "do_update":
    					do_update(request, response);
    				break;
    				
    				case "do_delete":
    					do_delete(request, response);
    				break;
    				
    				case "do_selectone":
    					do_selectone(request, response);
    				break;
    				
    				case "do_retrieve":
    					do_retrieve(request, response);
    				break;
    		 				
    			}
    	
    	LOG.debug("1------------------------");
    }

	/**
     * @see ConHandler#do_insert(HttpServletRequest, HttpServletResponse)
     */
    public void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ConHandler#do_save_move(HttpServletRequest, HttpServletResponse)
     */
    public void do_save_move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ConHandler#do_delete(HttpServletRequest, HttpServletResponse)
     */
    public void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ConHandler#do_retrieve(HttpServletRequest, HttpServletResponse)
     */
    public void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ConHandler#do_update(HttpServletRequest, HttpServletResponse)
     */
    public void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ConHandler#do_selectone(HttpServletRequest, HttpServletResponse)
     */
    public void do_selectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doServiceHandler(request, response);
	}

}
