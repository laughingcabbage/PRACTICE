/**
 * @Class Name : Hello.java
 * @Description : Hello
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.07.02           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2018.07.10 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 */
package com.hr.cmn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author sist
 *
 */
public interface ConHandler {
	Logger LOG = Logger.getLogger(ConHandler.class);
	
	/**
	 * 
	 * @Method Name  : doServiceHandler
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @Method Name  : do_insert
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @Method Name  : do_update
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @Method Name  : do_delete
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @Method Name  : do_selectone
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void do_selectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @Method Name  : do_retrieve
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @Method Name  : do_save_move
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void do_save_move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
