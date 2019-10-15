package com.hr.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(description = "file upload", urlPatterns = { "/upload.do" })
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final Logger LOG = Logger.getLogger(UploadServlet.class);
       
    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.변수선언
		
		//doPost내의 전역변수 
		List<FileVO> fileList = new ArrayList<>();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//File관련 변수
		String savePath 		= "E:\\Java_20190415\\02_ORACLE\\workspace_web\\WEB_EX01\\WebContent\\upload";
		int uploadFileSizeLimit = 1024*1024*50; //50mb
		String encType 			= "UTF-8";
		
		LOG.debug("0. savePath:"+savePath);
		
		//1.upload/yyyy/mm/폴더를 만든다.
		
		File dirUpload = new File(savePath);
		
		//upload폴더가 존재한다==거짓이면 이 dir를 make해라.
		if(dirUpload.exists()==false) dirUpload.mkdir();
		
		//년도:2019 폴더 만들기
		Date date = new Date();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		String year = sdfYear.format(date);
		String yearPath = savePath + File.separator + year;
		LOG.debug("1.yearPath:"+yearPath);
		
		File dirYear = new File(yearPath);
		
		//yearDir가 존재하지 않으면 이 디렉토리를 생성해라.
		if(dirYear.exists()==false) dirYear.mkdir();
		
		//월:07 폴더 만들기
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		String month = sdfMonth.format(date);
		String monthPath = yearPath + File.separator + month;
		LOG.debug("1.1.monthPath:"+monthPath);
		
		File dirMonth = new File(monthPath);
		
		//monthDir가 존재하지 않으면 이 디렉토리를 생성해라.
		if(dirMonth.exists()==false) dirMonth.mkdir();
		
		//2.form에서 전달된 data를 읽는다.
		
		savePath = monthPath;
		LOG.debug("2. savePath:"+savePath);
		
		try{
			MultipartRequest multi = new MultipartRequest(request, savePath, uploadFileSizeLimit, encType);
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()){
				FileVO fileVO       = new FileVO();
				String tmpFile      = files.nextElement();
				
				if(null==tmpFile || tmpFile.length()==0) continue;
				
				String orgFileName  = multi.getOriginalFileName(tmpFile); //원본파일명
				String saveFileName = multi.getFilesystemName(tmpFile);   //저장된 파일명
				saveFileName = savePath + File.separator + saveFileName;
				
				LOG.debug("-----------------------------------------");
				LOG.debug("2.1. tmpFile:"+tmpFile);
				LOG.debug("2.2. orgFileName:"+orgFileName);
				LOG.debug("2.3. saveFileName:"+saveFileName);
				LOG.debug("-----------------------------------------");
				
				File fileSize = new File(saveFileName); //파일사이즈
				fileVO.setOrgFileNm(orgFileName);
				fileVO.setSaveFineNm(saveFileName);
				fileVO.setfSize(fileSize.length());
				
				fileList.add(fileVO);
			}
			
			//화면으로 전달
			request.setAttribute("list", fileList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/upload/upload_result.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception e){
			LOG.debug("2. Exception:---------------------");
			LOG.debug(e.toString());
			LOG.debug("2. Exception:---------------------");
		}
		
		//3.파일을 upload처리
		//4.List<FileVO> vo에 set
		//5.화면전달
	}

}
