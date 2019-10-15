package com.hr.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hr.api.naver.blog.MemberVO;
import com.hr.member.HrMemberVO;

/**
 * Servlet Filter implementation class LoginCheck
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST }
					, 
		description = "로그인체크", 
		urlPatterns = { 
				"/member/*", 
				"/board/board.do"
		})
public class LoginCheck implements Filter {
	
	final Logger LOG = Logger.getLogger(LoginCheck.class);

    /**
     * Default constructor. 
     */
    public LoginCheck() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.debug("==========================");
		LOG.debug("doFilter");
		LOG.debug("==========================");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		boolean isLogin = false;
		
		if(null!=session){
			if(null!=session.getAttribute("user")){
				HrMemberVO vo = (HrMemberVO)session.getAttribute("user");
				LOG.debug("=========================");
				LOG.debug("vo:"+vo);
				LOG.debug("=========================");
				isLogin = true;
			}
		}
		
		if(isLogin==true){//로그인이 된 경우
			chain.doFilter(request, response);
		}else{//로그인이 안 된 경우
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
			dispatcher.forward(httpRequest, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
