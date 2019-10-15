package com.hr.cmn;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.code.CodeVO;

public class StringUtil {

	private static final Logger LOG = Logger.getLogger(StringUtil.class);
	
	/**
	 * 
	 * @Method Name  : renderPaging
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param maxNum	 : 총 글수
	 * @param currPageNo : 현재 페이지
	 * @param rowPerPage : 한 페이지에 보여 줄 글의 개수
	 * @param bottomCount: 바닥에 보여 줄 페이지 수
	 * @param url		 : 호출url
	 * @param scriptName : 호출 javascript
	 * @return
	 */
	public static String renderPaging(int maxNum, int currPageNo, int rowPerPage, int bottomCount, String url, String scriptName){
		/*총글수				21
		 *현재페이지 			 1
		 *총글수				 0
		 *바닥에 보여 줄 페이지 수	10
		 *한 페이지에 보여 줄 글 수	10
		 *호출 url
		 *호출 javascript
		 *<< < 1 2 3 4 5 6 7 8 9 10 > >>
		*/
		
		int maxPageNo   = ((maxNum-1)/rowPerPage)+1; //총페이지
		int startPageNo = ((currPageNo-1)/bottomCount)*bottomCount+1; 
		int endPageNo	= ((currPageNo-1)/bottomCount+1)*bottomCount;
		int nowBlockNo	= ((currPageNo-1)/bottomCount)+1;
		int maxBlockNo	= ((maxNum-1)/bottomCount)+1;		
		int inx = 0;//반복변수
		
		StringBuilder html = new StringBuilder();
		if(currPageNo>maxPageNo){
			return "";
		}
		//<table><tr><td></td></tr></table>
		
		html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> \n");
		html.append("<tr> \n");
		html.append("<td align=\"center\"> \n");
		//paging----------------------------------------
		
		//<< &laquo;
		if(nowBlockNo>1 && nowBlockNo<=maxBlockNo){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',1);\" >");
			html.append("&laquo; ");
			html.append("</a> \n");
		}	
		//<
		if(startPageNo > bottomCount){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+(startPageNo-1)+");\" >");
			html.append("< ");
			html.append("</a> \n");
		}
		//1 2 ... 10
		for(inx=startPageNo; inx<=maxPageNo && inx<=endPageNo; inx++){
			if(inx==currPageNo){
				html.append("<b>"+inx+"</b> &nbsp;&nbsp;");
			}else{
				html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+inx+");\" >");
				html.append(inx);
				html.append("</a>&nbsp;&nbsp; \n");
			}
		}
		//>
		if(maxPageNo>=inx){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+((nowBlockNo*bottomCount)+1)+");\" >");
			html.append("> ");
			html.append("</a> \n");
		}
		//>> &raquo;
		if(maxPageNo>=inx){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+maxPageNo+");\" >");
			html.append("&raquo; ");
			html.append("</a> \n");
		}
		//paging----------------------------------------
		
		html.append("</td> \n");
		html.append("</tr> \n");
		html.append("</table>");
		
		LOG.debug("=======================");
		LOG.debug(html.toString());
		LOG.debug("=======================");
		
		return html.toString();
	}
	
	public static String nvl(Object str, String defVal){
		String retStr = "";

		if(null == str || str.equals("")){
			retStr = defVal;
		}else{
			retStr = str.toString().trim();
		}
		
		return retStr;
	}
	
	/**
	 * 
	 * @Method Name  : makeSelectBox
	 * @작성일   : 2019. 7. 22.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param list : select에 필요한 코드 정보
	 * @param selectBoxNm : <select name="lvl" id="lvl">
	 * @param selectedNm  : <option selected>
	 * @param allYN       : 전체표시
	 * @return			  : <select name="lvl" id="lvl">
	 * 							<option value="">전체</option>
								<option value="1" selected>일반사용자</option>
								<option value="9">관리자</option>
							</select>
	 */
	public static String makeSelectBox(List<CodeVO> list, String selectBoxNm, String selectedNm, boolean allYN){
		
		StringBuilder sb = new StringBuilder();
		//<select name="lvl" id="lvl">
		sb.append("\n<select name='"+selectBoxNm+"' id='"+selectBoxNm+"'>\n");
		
		//전체
		if(allYN==true){
			sb.append("<option value=''>전체</option>");
		}
		
		//<option value="1" selected>일반사용자</option>
		for(CodeVO dto :list){
			CodeVO vo = (CodeVO) dto;
			sb.append("\t<option value='"+vo.getCodeId()+"' ");
			if(selectedNm.equals(vo.getCodeId())){
				sb.append("selected='selected' ");
			}
			sb.append(">");
			sb.append(vo.getCodeNm());
			sb.append("</option>\n");
		}
		sb.append("</select>");
		LOG.debug("--------------------------------");
		LOG.debug(sb.toString());
		LOG.debug("--------------------------------");
		
		return sb.toString();
	}
}
