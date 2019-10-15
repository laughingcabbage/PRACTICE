package com.hr.board;

import com.google.gson.Gson;
import com.hr.cmn.ConHandler;
import com.hr.cmn.MessageVO;
import com.hr.cmn.StringUtil;
import com.hr.code.CodeDao;
import com.hr.code.CodeVO;
import com.hr.member.HrMemberVO;
import com.hr.member.SearchVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController
 */
@WebServlet(description = "게시판", urlPatterns = { "/board/board.do" })
public class BoardController extends HttpServlet implements ConHandler {
	private static final long serialVersionUID = 1L;
	private BoardService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
    	LOG.debug("0------------------------");
    	LOG.debug("-service-"+service);
    	LOG.debug("0------------------------");
    	service = new BoardService();
    }

	/**
     * @see ConHandler#doServiceHandler(HttpServletRequest, HttpServletResponse)
     */
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
    				
    				case "do_move_to_list":
    					do_move_to_list(request, response);
    				break;
    				
    			}
    	
    	LOG.debug("1------------------------");
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

	public void do_move_to_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("03.1 do_move_to_list()");

    	RequestDispatcher dispatcher = request.getRequestDispatcher("/board/board.do?work_div=do_retrieve");
    	dispatcher.forward(request, response);
	}
	
	@Override
	public void do_insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("03.1 do_insert()");    	
    	BoardVO inVO = new BoardVO();
    	
    	//param
    	String title      = StringUtil.nvl(request.getParameter("title"),"");
    	String contents   = StringUtil.nvl(request.getParameter("contents"),"");
    	String regId      = StringUtil.nvl(request.getParameter("reg_id"),"");
    	
    	inVO.setTitle(title);
    	inVO.setContents(contents);
    	inVO.setRegId(regId);
    	//--param
    	
    	LOG.debug("03.2 param:"+inVO);
    	 	
    	int flag = service.do_insert(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString ="";
    	if(flag>0){
    		msg = inVO.getTitle()+"을(를) 등록하였습니다.";
    	}else{
    		msg = "등록실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
	}

	@Override
	public void do_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("03.1 do_update()");    	
    	BoardVO inVO = new BoardVO();
    	
    	//param
    	String seq    = StringUtil.nvl(request.getParameter("seq"),"");
    	String title      = StringUtil.nvl(request.getParameter("title"),"");
    	String contents     = StringUtil.nvl(request.getParameter("contents"),"");
    	String regId     = StringUtil.nvl(request.getParameter("reg_id"),"");
    	
    	inVO.setSeq(seq);
    	inVO.setTitle(title);
    	inVO.setContents(contents);
    	inVO.setRegId(regId);
    	//--param
    	
    	LOG.debug("03.2 param:"+inVO);
    	 	
    	int flag = service.do_update(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString ="";
    	if(flag>0){
    		msg = inVO.getTitle()+"을(를) 수정하였습니다.";
    	}else{
    		msg = "수정실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
	}

	@Override
	public void do_delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("03.1 do_delete()");
    	//service call
    	BoardVO inVO = new BoardVO();
    	//user_id읽어 오기
    	String seq = StringUtil.nvl(request.getParameter("seq"), "");
    	inVO.setSeq(seq);
    	int flag = service.do_delete(inVO);
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
    		msg="삭제되었습니다.";
//    		out.println("location.href='/WEB_EX01/member/member.do?work_div=do_retrieve';");
    	}else{
    		msg="삭제실패.";
    	}
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag) , msg));
    	LOG.debug("03.3 gsonString:"+gsonString);
    	out.print(gsonString);
	}

	@Override
	public void do_selectone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("03.1 do_selectone()");
    	BoardVO inVO = new BoardVO();
    	String seq = StringUtil.nvl(request.getParameter("seq"), "");
    	inVO.setSeq(seq);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	
    	BoardVO outVO = (BoardVO) service.do_selectOne(inVO); 	    
    	LOG.debug("03.3 outVO:"+outVO);
    	request.setAttribute("vo", outVO);
    	
    	//code
    	//--code
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/board/board_mng.jsp");
    	dispatcher.forward(request, response);
	}

	@Override
	public void do_retrieve(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
    	
    	List<BoardVO> list = (List<BoardVO>) this.service.do_retrieve(inVO);
    	LOG.debug("--------------------------------------------");
    	for(BoardVO vo : list){
    		LOG.debug(vo);
    	}
    	LOG.debug("--------------------------------------------");
    	int totalCnt=0;
    	//총글수
    	if(list.size()>0 && list!=null){
    		BoardVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/board/board_list.jsp");
    	dispatcher.forward(request, response);
	}

	@Override
	public void do_save_move(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("03.1 do_save_move()");

    	RequestDispatcher dispatcher = request.getRequestDispatcher("/board/board_mng.jsp");
    	dispatcher.forward(request, response);
	}

}
