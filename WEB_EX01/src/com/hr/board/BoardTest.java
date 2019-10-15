package com.hr.board;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.member.SearchVO;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

public class BoardTest {

	private final Logger LOG = Logger.getLogger(BoardTest.class);
	private BoardVO vo01;
	private BoardVO vo02;
	private BoardVO vo03;
	private BoardDao dao;
	
	public BoardTest() {
		vo01 = new BoardVO("1","제목1","0","내용1","admin","");
		vo02 = new BoardVO("2","제목2","0","내용2","admin","");
		vo03 = new BoardVO("3","제목3","0","내용3","admin","");		
		
		dao = new BoardDao();
	}
	
	public void do_selectOne(){
		vo01.setSeq("12");
		BoardVO outVO = (BoardVO) dao.do_selectOne(vo01);
		LOG.debug("======================");
		LOG.debug("outVO:"+outVO);
		LOG.debug("======================");
	}

	public void do_delete(){
		vo01.setSeq("11");
		int flag = dao.do_delete(vo01);
	}
	
	public void do_insert(){
		int flag = dao.do_insert(vo01);
	}
	
	public void do_update(){
		vo01.setSeq("16");
		vo01.setTitle("HR_U");
		vo01.setContents("HR_Contents_U");
		vo01.setRegId("admin_U");
		int flag = dao.do_update(vo01);
	}
	
	public void do_upsert(){
		vo01.setSeq("13");
		vo01.setTitle("아");
		vo01.setContents("아아");
		vo01.setRegId("아아아");
		int flag = dao.do_upsert(vo01);
	}
	
	//마지막에 등록된 순번 출력
	public void getSeq(){
		dao.getSeq();
	}

	/**
	 * 테스트코드: addAndGet():IS(insert, select) //완료
	 * 테스트코드: addAndUpdate():ISUS(insert, select, update, select) //완료
	 * paging: selectList 
	 * 코드테이블 설계
	 */
	
	public void addAndUpdate(){
		//-----------------------------
		//등록->등록된 시퀀스 조회
		//-----------------------------
		dao.do_insert(vo01);
		String seq = dao.getSeq();
		
		//-----------------------------
		//단건조회
		//-----------------------------
		vo01.setSeq(seq);
		BoardVO outVO = (BoardVO) dao.do_selectOne(vo01);
		
		//-----------------------------
		//수정:1
		//-----------------------------
		outVO.setTitle("제목_U");
		outVO.setContents("내용_U");
		outVO.setRegId("admin_U");
		dao.do_update(outVO);
		
		//-----------------------------
		//단건조회:2
		//-----------------------------
		BoardVO updatedVO = (BoardVO) dao.do_selectOne(outVO);
		
		//-----------------------------
		//비교: 1번과 2번을 비교
		//-----------------------------
		if(outVO.equals(updatedVO)){
			LOG.debug("======================");
			LOG.debug("단건수정 성공:\n"+outVO);
			LOG.debug("======================");
		}else{
			LOG.debug("======================");
			LOG.debug("단건수정 실패");
			LOG.debug("======================");
		}
		
	}
	
	public void addAndGet(){
		//-----------------------------
		//삭제:sequence이므로 사용할 수 없음.
		//-----------------------------

		//-----------------------------
		//등록->시퀀스 조회
		//-----------------------------
		dao.do_insert(vo01);
		String seq = dao.getSeq();
		
		//-----------------------------
		//단건조회
		//-----------------------------
		vo01.setSeq(seq);
		BoardVO outVO = (BoardVO) dao.do_selectOne(vo01);
		
		//-----------------------------
		//비교:
		//-----------------------------
		if(outVO.equals(vo01)){
			LOG.debug("=============================");
			LOG.debug("단건등록 성공\n"+outVO);
			LOG.debug("=============================");
		}else{
			LOG.debug("=============================");
			LOG.debug("단건등록 실패");
			LOG.debug("=============================");
		}
		
	}
	
	public void do_retrieve(){
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		searchVO.setSearchDiv("10");
		searchVO.setSearchWord("제목");
		
		List<BoardVO> list = (List<BoardVO>) dao.do_retrieve(searchVO);
		
		LOG.debug("=====================");
		for(BoardVO vo : list){
			LOG.debug("vo"+vo);
		}
		LOG.debug("=====================");
		
	}
	
	public void callProcedure(){
		vo01.setSeq("204");
		dao.callProcedure(vo01);
	}
	
	public static void main(String[] args) {
		BoardTest test = new BoardTest();
		//test.do_selectOne();
		//test.do_delete();
		//test.do_insert();
		//test.do_update();
		//test.do_upsert();
		//test.getSeq();
		//test.addAndGet();
		//test.addAndUpdate();
		test.do_retrieve();
		//test.callProcedure();
	}
}
