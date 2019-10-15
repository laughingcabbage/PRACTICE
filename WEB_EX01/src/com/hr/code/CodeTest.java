package com.hr.code;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.StringUtil;

public class CodeTest {
	private final Logger LOG=Logger.getLogger(CodeTest.class);
	private CodeVO codeVO;
	private CodeDao dao;
	
	public CodeTest(){
		codeVO = new CodeVO();
		dao    = new CodeDao();
	}
	
	public void do_retrieve(){
		codeVO.setCodeTypeId("LVL");
		List<CodeVO> list =(List<CodeVO>) dao.do_retrieve(codeVO);
		for(CodeVO vo:  list){
			LOG.debug(vo);
		}
		
		String selected = StringUtil.makeSelectBox(list, "lvl", "1", false);
	}
	
	public static void main(String[] args) {
		CodeTest codeTest=new CodeTest();
		codeTest.do_retrieve();
	}

}