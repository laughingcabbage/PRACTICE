package com.hr.naver.mail;

import com.google.gson.Gson;
import com.hr.cmn.ConHandler;
import com.hr.cmn.MessageVO;
import com.hr.cmn.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailController
 */
@WebServlet(description = "네이버메일", urlPatterns = { "/mail/mail.do" })
public class MailController extends HttpServlet implements ConHandler {
	private static final long serialVersionUID = 1L;
	private MailService mailService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailController() {
        mailService = new MailService();
    }

    public void do_send_mail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	LOG.debug("3 do_send_mail");
    	MailVO inVO = new MailVO();
    	    	
    	//param to VO
    	String title = StringUtil.nvl(request.getParameter("title"), "");
    	String email = StringUtil.nvl(request.getParameter("email"), "");
    	String message = StringUtil.nvl(request.getParameter("message"), "");
    	
    	inVO.setTitle(title);
    	inVO.setMailTo(email);
    	inVO.setContents(message);
    	
    	LOG.debug("03.1 param:"+inVO);
    	
    	int flag = mailService.do_send_mail(inVO);
    	
    	LOG.debug("03.2 flag:"+flag);
    	
    	//message json
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String msg = "";
    	String gsonString = "";
    	if(flag>0){
    		msg = inVO.getMailTo()+"에게 메일이 전송되었습니다.";
    	}else{
    		msg = "전송실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	
    	LOG.debug("03.3 gsonString:"+gsonString);
    	out.print(gsonString);
    }
    
	/**
     * @see ConHandler#doServiceHandler(HttpServletRequest, HttpServletResponse)
     */
    public void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	//기능 : do_retrieve,do_insert,do_update,do_selectone,do_retrieve
    	//work_div
    	LOG.debug("02 doServiceHandler()");
    	request.setCharacterEncoding("UTF-8");
    	//work_div:read
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"),"");
    	LOG.debug("02.1 workDiv:"+workDiv);
    	
    	/* do_retrieve:목록
    	 * do_insert:등록
    	 * do_update:수정
    	 * do_selectone:단건조회
    	 * do_delete:
    	 */
    	switch(workDiv){
    	    //mail send
    		case "do_send_mail":
    			do_send_mail(request,response);
    		break;
    	    //등록화면으로 이동
			case "do_save_move":
				do_save_move(request,response);
			break;
		
    		case "do_insert":
    			do_insert(request,response);
    		break;
    		
    		case "do_update":
    			do_update(request,response);
    		break;  
    		
    		case "do_delete":
    			do_delete(request,response);
    		break;     
    		
    		case "do_selectone":
    			do_selectone(request,response);
    		break;  
    		
    		case "do_retrieve":
    			do_retrieve(request,response);
    		break;      		
    	}
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
		LOG.debug("1------------------------");
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("1------------------------");
		doServiceHandler(request, response);
	}

}
