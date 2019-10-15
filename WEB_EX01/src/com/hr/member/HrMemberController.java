package com.hr.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hr.cmn.MessageVO;
import com.hr.cmn.StringUtil;
import com.hr.code.CodeDao;
import com.hr.code.CodeVO;

/**
 * Servlet implementation class HrMemberController
 */
@WebServlet(description = "회원관리", urlPatterns = { "/member/member.do", "/member/member.json" })
public class HrMemberController extends HttpServlet {
	//View->Controller->Service
	
	private static final long serialVersionUID = 1L;
	private HrMemberService hrMemberService;
	private final Logger LOG = Logger.getLogger(HrMemberController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HrMemberController() {
    	hrMemberService = new HrMemberService();
    }
    
    protected void do_logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_logout()");
    	//1.session삭제
    	HttpSession httpSession = request.getSession();
    	if(null!=httpSession){
    		//삭제 전 
    		LOG.debug("httpSession:"+httpSession);
    		
    		httpSession.removeAttribute("user");
    		httpSession.removeAttribute("name");
    		httpSession.removeAttribute("id");
    		httpSession.invalidate();
    		
    		//삭제 후
    		LOG.debug("httpSession:"+httpSession);
    	}
    	
    	//2.login.jsp로 이동
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
    	dispatcher.forward(request, response);
    	
    }
    
    protected void do_login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_login()");
    	HrMemberVO inVO = new HrMemberVO();
    	String userId = StringUtil.nvl(request.getParameter("user_id"), "");
    	String password = StringUtil.nvl(request.getParameter("password"), "");
    	
    	inVO.setUserId(userId);
    	inVO.setPassword(password);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	MessageVO checkMsg = hrMemberService.loginCheck(inVO);	    
    	HrMemberVO outVO = new HrMemberVO();
    	
    	if(checkMsg.getMsgId().equals("1")){
    		outVO = hrMemberService.do_selectOne(inVO);
    		//session:
    		HttpSession session = request.getSession();
    		session.setAttribute("user", outVO);
    		session.setAttribute("name", outVO.getName());
    		session.setAttribute("id", outVO.getUserId());
    		LOG.debug("03.3 outVO:"+outVO);
    	}    		
    	
    	//JSON
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String gsonString = "";
    	gsonString = gson.toJson(checkMsg);
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
//    	
//    	RequestDispatcher dispatcher = request.getRequestDispatcher("/main/main.jsp");
//    	dispatcher.forward(request, response);
    }
    
    protected void do_save_move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_save_move()");
    	CodeVO codeVO = new CodeVO();
    	CodeDao dao   = new CodeDao();
    	codeVO.setCodeTypeId("LVL");
    	List<CodeVO> list =(List<CodeVO>) dao.do_retrieve(codeVO);
    	request.setAttribute("lvlList", list);
    	//user_id 화면제어:readonly
    	request.setAttribute("mode", "insert");
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_mng.jsp");
    	dispatcher.forward(request, response);
    }
    
    //http://localhost:8080/WEB_EX01/member/member.do?work_div=do_insert
    protected void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_insert()");
    	LOG.debug("03.1 do_update()");    	
    	HrMemberVO inVO = new HrMemberVO();
    	
    	//param
    	String userId    = StringUtil.nvl(request.getParameter("user_id"),"");
    	String name      = StringUtil.nvl(request.getParameter("name"),"");
    	String password  = StringUtil.nvl(request.getParameter("passwd"),"");
    	String email     = StringUtil.nvl(request.getParameter("email"),"");
    	String birth     = StringUtil.nvl(request.getParameter("birth"),"");
    	String cellPhone = StringUtil.nvl(request.getParameter("cell_phone"),"");
    	String gender    = StringUtil.nvl(request.getParameter("sex"),"F");
    	String lvl       = StringUtil.nvl(request.getParameter("lvl"),"1");
    	String regId     = StringUtil.nvl(request.getParameter("reg_id"),"");
    	
    	inVO.setUserId(userId);
    	inVO.setName(name);
    	inVO.setPassword(password);
    	inVO.setEmail(email);
    	inVO.setBirth(birth);
    	inVO.setCellPhone(cellPhone);
    	inVO.setGender(gender);
    	inVO.setLvl(lvl);
    	inVO.setRegId(regId);
    	//--param
    	
    	LOG.debug("03.2 param:"+inVO);
    	 	
    	int flag = this.hrMemberService.do_insert(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString ="";
    	if(flag>0){
    		msg = inVO.getUserId()+"을(를) 등록하였습니다.";
    	}else{
    		msg = "등록실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
    }
    
    protected void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_update()");    	
    	HrMemberVO inVO = new HrMemberVO();
    	
    	//param
    	String userId    = StringUtil.nvl(request.getParameter("user_id"),"");
    	String name      = StringUtil.nvl(request.getParameter("name"),"");
    	String password  = StringUtil.nvl(request.getParameter("passwd"),"");
    	String email     = StringUtil.nvl(request.getParameter("email"),"");
    	String birth     = StringUtil.nvl(request.getParameter("birth"),"");
    	String cellPhone = StringUtil.nvl(request.getParameter("cell_phone"),"");
    	String gender    = StringUtil.nvl(request.getParameter("sex"),"F");
    	String lvl       = StringUtil.nvl(request.getParameter("lvl"),"1");
    	String modId     = StringUtil.nvl(request.getParameter("mod_id"),"");
    	
    	inVO.setUserId(userId);
    	inVO.setName(name);
    	inVO.setPassword(password);
    	inVO.setEmail(email);
    	inVO.setBirth(birth);
    	inVO.setCellPhone(cellPhone);
    	inVO.setGender(gender);
    	inVO.setLvl(lvl);
    	inVO.setModId(modId);
    	//--param
    	
    	LOG.debug("03.2 param:"+inVO);
    	 	
    	int flag = this.hrMemberService.do_update(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString ="";
    	if(flag>0){
    		msg = inVO.getUserId()+"을(를) 수정하였습니다.";
    	}else{
    		msg = "수정실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
    }
    
    protected void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_delete()");
    	//service call
    	HrMemberVO inVO = new HrMemberVO();
    	//user_id읽어 오기
    	String userId = StringUtil.nvl(request.getParameter("user_id"), "");
    	inVO.setUserId(userId);
    	int flag = hrMemberService.do_delete(inVO);
    	LOG.debug("03.2 flag:"+flag);
    	//JSON
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String msg = "";
    	String gsonString = "";
    	//msg=flag
    	//msgContents="삭제되었습니다.";
    	if(flag>0){
    		msg="삭제 되었습니다.";
//    		out.println("location.href='/WEB_EX01/member/member.do?work_div=do_retrieve';");
    	}else{
    		msg="삭제 실패.";
    	}
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag) , msg));
    	LOG.debug("03.3 gsonString:"+gsonString);
    	out.print(gsonString);
    }
    
    protected void do_selectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_selectone()");
    	HrMemberVO inVO = new HrMemberVO();
    	String userId = StringUtil.nvl(request.getParameter("user_id"), "");
    	inVO.setUserId(userId);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	
    	HrMemberVO outVO = hrMemberService.do_selectOne(inVO); 	    
    	LOG.debug("03.3 outVO:"+outVO);
    	request.setAttribute("vo", outVO);
    	request.setAttribute("mode", "update");
    	
    	//code
    	CodeVO codeVO = new CodeVO();
    	CodeDao dao   = new CodeDao();
    	codeVO.setCodeTypeId("LVL");
    	List<CodeVO> list =(List<CodeVO>) dao.do_retrieve(codeVO);
    	request.setAttribute("lvlList", list);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_mng.jsp");
    	dispatcher.forward(request, response);
    }
    
    protected void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_retrieve()");
    	SearchVO inVO = new SearchVO();
    	
    	String pageNum 	  = StringUtil.nvl(request.getParameter("page_num"), "1");//null이면 default로 1
    	String searchDiv  = StringUtil.nvl(request.getParameter("search_div"), "");
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");
    	String pageSize   = StringUtil.nvl(request.getParameter("page_size"), "10");
    	
    	inVO.setPageNum(Integer.parseInt(pageNum));
    	inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);
    	inVO.setPageSize(Integer.parseInt(pageSize));
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	
    	List<HrMemberVO> list = hrMemberService.do_retrieve(inVO);
    	LOG.debug("--------------------------------------------");
    	for(HrMemberVO vo : list){
    		LOG.debug(vo);
    	}
    	LOG.debug("--------------------------------------------");
    	int totalCnt=0;
    	//총글수
    	if(list.size()>0 && list!=null){
    		HrMemberVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_list.jsp");
    	dispatcher.forward(request, response);
    }
    
	protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.debug("02 doServiceHandler()");
		request.setCharacterEncoding("UTF-8");
		
		//work_div읽기
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		LOG.debug("02.1 workDiv:"+workDiv);
		
		/* 기능 
		 * do_retrieve:목록
		 * do_insert:등록
		 * do_delete:수정
		 * do_selectOne:단건조회
		 * do_update:삭제
		 * do_login:로그인
		 * do_logout:로그아웃
		 */ 
		
		switch(workDiv){
			case "do_logout":
				do_logout(request, response);
			break;
			case "do_login":
				do_login(request, response);
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
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 hrMemberService: "+hrMemberService);
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 hrMemberService: "+hrMemberService);
		doServiceHandler(request, response);
	}
}
