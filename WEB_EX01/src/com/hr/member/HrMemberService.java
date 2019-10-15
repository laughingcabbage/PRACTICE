package com.hr.member;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.DTO;
import com.hr.cmn.MessageVO;
import com.hr.google.chart.YearMemberRatioVO;

public class HrMemberService {
	//View->Controller->Service->Dao
	
	private final Logger LOG = Logger.getLogger(HrMemberService.class);
	private HrMemberDao hrMemberDao;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName : HrMemberService
	 * 2. ClassName  : HrMemberService
	 * 3. Comment   : 초기화
	 * 4. 작성자    : sist
	 * 5. 작성일    : 2019. 7. 19. 오전 9:12:54
	 * </PRE>
	 */
	public HrMemberService(){
		hrMemberDao = new HrMemberDao();
	}
	
	public List<YearMemberRatioVO> do_yearFemaleRatio(DTO dto){
		return hrMemberDao.do_yearFemaleRatio(dto);
	}
	
	public MessageVO loginCheck(DTO dto){
		MessageVO outVO = new MessageVO();
		//01 idCheck
		outVO = hrMemberDao.idCheck(dto);
		if(!outVO.getMsgId().equals("1")) return outVO;
		
		//02 passCheck
		outVO = hrMemberDao.passCheck(dto);
		if(!outVO.getMsgId().equals("1")) return outVO;
		
		return outVO;
	}
	
	/**
	 * 
	 * @Method Name  : do_insert
	 * @작성일   : 2019. 7. 19.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 등록
	 * @param dto
	 * @return
	 */
	public int do_insert(DTO dto){
		return hrMemberDao.do_insert(dto);
	}
	
	/**
	 * 
	 * @Method Name  : do_update
	 * @작성일   : 2019. 7. 19.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 수정
	 * @param dto
	 * @return
	 */
	public int do_update(DTO dto){
		return hrMemberDao.do_update(dto);
	}
	
	/**
	 * 
	 * @Method Name  : do_delete
	 * @작성일   : 2019. 7. 19.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 삭제
	 * @param dto
	 * @return
	 */
	public int do_delete(DTO dto){
		return hrMemberDao.do_delete(dto);
	}
	
	/**
	 * 
	 * @Method Name  : do_selectOne
	 * @작성일   : 2019. 7. 19.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 단건조회
	 * @param dto
	 * @return
	 */
	public HrMemberVO do_selectOne(DTO dto){
		return hrMemberDao.do_selectOne(dto);
	}
	
	/**
	 * 
	 * @Method Name  : do_retrieve
	 * @작성일   : 2019. 7. 19.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 목록조회
	 * @param dto
	 * @return
	 */
	public List<HrMemberVO> do_retrieve(DTO dto){
		return hrMemberDao.do_retrieve(dto);
	}
}
