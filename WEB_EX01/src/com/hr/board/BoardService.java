package com.hr.board;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.DTO;

public class BoardService {
	
	private final Logger LOG = Logger.getLogger(BoardService.class);
	private BoardDao dao;
	
	public BoardService(){
		dao = new BoardDao();
    	LOG.debug("0------------------------");
    	LOG.debug("-dao-"+dao);
    	LOG.debug("0------------------------");		
	}
	
	public int do_insert(DTO dto) {
		return dao.do_insert(dto);
	}
	
	public int do_update(DTO dto) {
		return dao.do_update(dto);
	}
	
	public int do_delete(DTO dto){
		return dao.do_delete(dto);
	}
	
	public DTO do_selectOne(DTO dto){
		//단건조회
		BoardVO outVO = (BoardVO) dao.do_selectOne(dto);
		//조회수 증가
		int flag = dao.do_updateReadCnt(dto);
		outVO.setwFlag(flag);
		
		LOG.debug("1-do_selectOne------------------------");
		LOG.debug("outVO: "+outVO);
		LOG.debug("1-do_selectOne------------------------");
		
		return outVO;
	}
	
	/**
	 * 
	 * @Method Name  : do_retrieve
	 * @작성일   : 2019. 7. 24.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 조회
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_retrieve(DTO dto){
		return dao.do_retrieve(dto);
	}
		
}
