package com.hr.servlet.s2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.board.BoardVO;

/**
 * Servlet implementation class ServletToJsp
 */
@WebServlet(description = "Servlet to JSP", urlPatterns = { "/s2/servlettojsp.do" })
public class ServletToJsp extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletToJsp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath()+"*ServletToJsp");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//"/s2/servlettojsp.do"를 호출 -> receive.jsp
		BoardVO boardVO = new BoardVO();
		boardVO.setSeq("1");
		boardVO.setContents("contents");		
		//jsp로 전달할 data
		//request.setAttribute("name", "HR_JSP");
		request.setAttribute("vo", boardVO);
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/s2/receive.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
