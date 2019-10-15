package com.hr.member;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class HrMemberMain {

	private final Logger LOG = Logger.getLogger(HrMemberMain.class);
	
	HrMemberVO vo01;
	HrMemberVO vo02;
	HrMemberVO vo03;
	HrMemberDao dao;
	
	public HrMemberMain(){
		//public HrMemberVO(String userId, String password, String name, String email, String birth, String gender,
		//String cellPhone, String lvl, String regId, String regDt, String modId, String modDt)
		vo01 = new HrMemberVO("jame007_144","1234","이상무0000152","james0000152@naver.com","20190613","F","010-0152-0152","1","happy_hr_c0000152","","admin","");
		vo02 = new HrMemberVO("jame008_144","1234","이상무0000152","james0000152@naver.com","20190613","F","010-0152-0152","1","happy_hr_c0000152","","admin","");
		vo03 = new HrMemberVO("jame009_144","1234","이상무0000152","james0000152@naver.com","20190613","F","010-0152-0152","1","happy_hr_c0000152","","admin","");
		dao=new HrMemberDao();
	}
	
	public void do_deleteTest(){
		vo01.setUserId("happy_hr_c0000152");
		int delFlag = dao.do_delete(vo01);
		
		LOG.debug("---------------");
		LOG.debug("delFlag:"+delFlag);
		LOG.debug("---------------");
	}
	
	public void do_insertTest(){
		int flag=dao.do_insert(vo01);
		
		LOG.debug("---------------");
		LOG.debug("Flag:"+flag);
		LOG.debug("---------------");
	}
	
	public void do_selectOneTest(){
		HrMemberVO outVO = dao.do_selectOne(vo01);
		
		LOG.debug("---------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("---------------");
	}
	
	public void addAndGet(){
		//--------------------------------
		//삭제
		//--------------------------------
		int delFlag = dao.do_delete(vo01);
		
		LOG.debug("---------------");
		LOG.debug("delFlag:"+delFlag);
		LOG.debug("---------------");
		
		//--------------------------------
		//등록
		//--------------------------------
		int flag = dao.do_insert(vo01);
		LOG.debug("---------------");
		LOG.debug("add flag:"+flag);
		LOG.debug("---------------");
		
		//--------------------------------
		//단건조회
		//--------------------------------
		HrMemberVO outVO = dao.do_selectOne(vo01);
		LOG.debug("---------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("---------------");
		
		//--------------------------------
		//단건비교(등록한 데이터==조회한 데이터인지)
		//--------------------------------
		if(vo01.equals(outVO)){
			LOG.debug("---------------");
			LOG.debug("두 객체는 동일: 등록성공"+outVO);
			LOG.debug("---------------");
		}else{
			LOG.debug("---------------");
			LOG.debug("두 객체는 다르다: 등록실패"+outVO);
			LOG.debug("---------------");
		}
	}
	
	public void do_retrieve(){
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		searchVO.setSearchDiv("10");
		searchVO.setSearchWord("이상무001");
		List<HrMemberVO> list = dao.do_retrieve(searchVO);
		LOG.debug("---------------");
		LOG.debug("list"+list);
		LOG.debug("---------------");
	}
	
	public static void main(String[] args) {
		HrMemberMain hMember= new HrMemberMain();
		hMember.do_retrieve();
		//hMember.addAndGet();
	}
}
