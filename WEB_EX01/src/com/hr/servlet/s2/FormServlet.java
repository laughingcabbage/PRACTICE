package com.hr.servlet.s2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet(description = "폼data수신", urlPatterns = { "/s2/formservlet.do" })
public class FormServlet extends HttpServlet {
	
	private final Logger LOG = Logger.getLogger(FormServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		LOG.debug("doPost()");
		doServiceHandler(request, response);
	}
	
	protected void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-----------------------------------------------------------------------
		// 1.request read:validation
		//-----------------------------------------------------------------------
		
		//-----------------------------------------------------------------------
		// 2.request to VO
		//-----------------------------------------------------------------------
		
		//-----------------------------------------------------------------------
		// 3.Service/DAO 객체생성 param전달
		//-----------------------------------------------------------------------
		
		//-----------------------------------------------------------------------
		// 4.화면/Controller전달
		//-----------------------------------------------------------------------
	}
	
	protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//-----------------------------------------------------------------------
		//	command분기
		//		등록=do_insert	
		//		수정=do_update	
		//		업서트=do_upsert	
		//		삭제=do_delete	
		//		단건조회=do_selectone
		//		목록조회=do_retrieve
		//-----------------------------------------------------------------------
		
		String command = request.getParameter("command");
		
		if(command.equals("do_insert")){
			do_insert(request, response);
		}else{//command not defined
			
		}
		
		//수신
		String username = request.getParameter("username");
		String job 		= request.getParameter("job");
		String[] favorites = request.getParameterValues("favorite");
		String favResult = "";
		
		LOG.debug("doGet()");
//		LOG.debug("username:"+username);
//		LOG.debug("job:"+job);
		for(String str : favorites){
			favResult+=str+" ";
		}
		
		//브라우저에 출력
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out.print("username: "+username);
		//out.print("job: "+job);
		
		StringBuilder sb = new StringBuilder();
		sb.append(" <html>                                           \n");
		sb.append(" 	<head><title>Servlet Response</title></head> \n");
		sb.append(" 	<body>                                       \n");
		sb.append(" 		<p>name:"+username+"</p>                 \n");
		sb.append(" 		<p>job:"+job+"</p>                       \n");
		sb.append(" 		<p>관심분야:"+favResult+"</p>               \n");
		sb.append(" 	</body>                                      \n");
		sb.append(" </html>                                          \n");
		out.print(sb.toString());
	}

}
