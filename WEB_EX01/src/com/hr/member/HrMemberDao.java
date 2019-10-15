package com.hr.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.JDBCReturnReso;
import com.hr.cmn.MessageVO;
import com.hr.cmn.StringUtil;
import com.hr.cmn.WorkDiv;
import com.hr.google.chart.YearMemberRatioVO;

public class HrMemberDao implements WorkDiv{
	
	private final Logger LOG = Logger.getLogger(HrMemberDao.class);
	ConnectionMaker connectionMaker = new ConnectionMaker();
	
	public HrMemberDao(){}
	
	public List<YearMemberRatioVO> do_yearFemaleRatio(DTO dto){
		YearMemberRatioVO vo = (YearMemberRatioVO) dto;
		List<YearMemberRatioVO> list = new ArrayList<>();
		Connection conn = null; //db 연결
		PreparedStatement pstmt = null; //query 수행
		ResultSet rs = null;//결과처리
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT TO_CHAR(reg_dt, 'yyyy') as YEAR,           \n");
			sb.append("        COUNT(*) as TOTAL,                         \n");
			sb.append("        SUM(decode(gender, 'F', 1, 0)) as FEMALE,  \n");
			sb.append("        SUM(decode(gender, 'M', 1, 0)) as MALE     \n");
			sb.append(" FROM hr_member                                    \n");
			sb.append(" WHERE to_char(reg_dt, 'yyyy')>=?                  \n");
			sb.append(" GROUP BY to_char(reg_dt, 'yyyy')                  \n");
			sb.append(" ORDER by 1 DESC                                   \n");
			
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.conn:"+conn);
			LOG.debug("2.sql:\n"+sb.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, StringUtil.nvl(vo.getYear(), "2015"));
			
			LOG.debug("3.param:"+vo.getYear());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				YearMemberRatioVO outVO = new YearMemberRatioVO();
				outVO.setYear(rs.getString("year"));
				outVO.setTotalCnt(rs.getInt("total"));
				outVO.setMale(rs.getInt("male"));
				outVO.setFemale(rs.getInt("female"));
				
				list.add(outVO);
			}
					
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return list;
	}
	
	public MessageVO passCheck(DTO dto){
		HrMemberVO vo = (HrMemberVO) dto;
		MessageVO outVO = new MessageVO();
		int result = 0;
		Connection conn = null; //db 연결
		PreparedStatement pstmt = null; //query 수행
		ResultSet rs = null;//결과처리
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(*) cnt       \n");
			sb.append("FROM hr_member            \n");
			sb.append("WHERE user_id  = ?        \n");
			sb.append("AND   password = ?        \n");
			
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.conn:"+conn);
			LOG.debug("2.sql:\n"+sb.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getPassword());
			
			LOG.debug("3.param:"+vo.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
				if(result==0) {//비번을 확인하세요
					outVO.setMsgId("20");
					outVO.setMsgContents("비번을 확인하세요.");
				}else{
					outVO.setMsgId("1");
					outVO.setMsgContents("비번 OK.");
				}
				
			}
					
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return outVO;
	}
	
	/**
	 * 
	 * @Method Name  : idCheck
	 * @작성일   : 2019. 7. 31.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param dto
	 * @return
	 */
	public MessageVO idCheck(DTO dto){
		HrMemberVO vo = (HrMemberVO) dto;
		MessageVO outVO = new MessageVO();
		int result = 0;
		Connection conn = null; //db 연결
		PreparedStatement pstmt = null; //query 수행
		ResultSet rs = null;//결과처리
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT count(*) cnt \n");
			sb.append(" FROM hr_member      \n");
			sb.append(" WHERE user_id= ?    \n");
			
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.conn:"+conn);
			LOG.debug("2.sql:\n"+sb.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUserId());
			
			LOG.debug("3.param:"+vo.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
				if(result==0) {//id를 확인하세요
					outVO.setMsgId("10");
					outVO.setMsgContents("ID를 확인하세요.");
				}else{
					outVO.setMsgId("1");
					outVO.setMsgContents("ID가 있습니다.");
				}
				
			}
					
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return outVO;
	}
	
	/**
	 * 등록
	 * @param vo
	 * @return
	 */
	public int do_insert(DTO dto){
		HrMemberVO vo = (HrMemberVO) dto;
		int flag=0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{	
			conn = connectionMaker.getConnection();
			LOG.debug("1.conn:"+conn);
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO hr_member ( \n");
			sb.append("    user_id,            \n");
			sb.append("    password,           \n");
			sb.append("    name,               \n");
			sb.append("    email,              \n");
			sb.append("    birth,              \n");
			sb.append("    gender,             \n");
			sb.append("    cell_phone_num,     \n");
			sb.append("    lvl,                \n");
			sb.append("    reg_id,             \n");
			sb.append("    reg_dt,             \n");
			sb.append("    mod_id,             \n");
			sb.append("    mod_dt              \n");
			sb.append(") VALUES (              \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    ?,                  \n");
			sb.append("    SYSDATE,            \n");
			sb.append("    ?,                  \n");
			sb.append("    SYSDATE             \n");
			sb.append(")                       \n");
			
			LOG.debug("2.sql:\n"+sb.toString());
			LOG.debug("3.param:\n"+vo.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getCellPhone());
			pstmt.setString(8, vo.getLvl());
			pstmt.setString(9, vo.getRegId());
			pstmt.setString(10, vo.getModId());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4.flag:"+flag);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}
	
	/**
	 * 수정
	 * @param vo
	 * @return
	 */
	public int do_update(DTO dto){
		HrMemberVO vo = (HrMemberVO) dto;
		int flag=0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{	
			conn = connectionMaker.getConnection();
			LOG.debug("1.conn:"+conn);
			
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE hr_member           \n");
			sb.append(" SET password   = ?         \n");
			sb.append("     ,name   = ?            \n");
			sb.append("     ,email  = ?            \n");
			sb.append("     ,birth  = ?            \n");
			sb.append("     ,gender = ?            \n");
			sb.append("     ,cell_phone_num = ?    \n");
			sb.append("     ,lvl    = ?            \n");
			sb.append("     ,mod_id = ?            \n");
			sb.append("     ,mod_dt = SYSDATE      \n");
			sb.append(" WHERE user_id  = ?         \n");
			
			LOG.debug("2.sql:\n"+sb.toString());
			LOG.debug("3.param:\n"+vo.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getCellPhone());
			pstmt.setString(7, vo.getLvl());
			pstmt.setString(8, vo.getModId());
			pstmt.setString(9, vo.getUserId());
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("4.flag:"+flag);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}
	
	/**
	 * 삭제
	 * @param vo
	 * @return
	 */
	public int do_delete(DTO dto){
		HrMemberVO vo = (HrMemberVO) dto;
		int flag=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM hr_member \n");
		sb.append("WHERE user_id = ?     \n");
		
		try {
			conn = connectionMaker.getConnection();
			LOG.debug("1==================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1==================");
			
			pstmt= conn.prepareStatement(sb.toString());
			
			//query param 설정
			pstmt.setString(1, vo.getUserId()); //1번째 ?에 vo의 userId 넣으라는 뜻
			LOG.debug("2==================");
			LOG.debug("param:\n"+vo);
			LOG.debug("2==================");
			
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
	
		LOG.debug("3==================");
		LOG.debug("flag:\n"+flag);
		LOG.debug("3==================");
		
		return flag;
	}
	
	/**
	 * 단건조회
	 * @param vo
	 * @return
	 */
	public HrMemberVO do_selectOne(DTO dto){
		HrMemberVO vo = (HrMemberVO) dto;
		HrMemberVO outVO = null;
		Connection conn = null; //db 연결
		PreparedStatement pstmt = null; //query 수행
		ResultSet rs = null;//결과처리
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT                                       \n");
			sb.append("     user_id,                                 \n");
			sb.append("     password,                                \n");
			sb.append("     name,                                    \n");
			sb.append("     email,                                   \n");
			sb.append("     birth,                                   \n");
			sb.append("     gender,                                  \n");
			sb.append("     cell_phone_num,                          \n");
			sb.append("     lvl,                                     \n");
			sb.append("     reg_id,                                  \n");
			sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') red_dt,     \n");
			sb.append("     mod_id,                                  \n");
			sb.append("     TO_CHAR(mod_dt,'YYYY-MM-DD') mod_dt      \n");
			sb.append(" FROM                                         \n");
			sb.append("     hr_member                                \n");
			sb.append(" where user_id = ?                            \n");
			
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.conn:"+conn);
			LOG.debug("2.sql:\n"+sb.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUserId());
			
			LOG.debug("3.param:"+vo.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				outVO = new HrMemberVO();
				outVO.setUserId(rs.getString("user_id"));
				outVO.setPassword(rs.getString("password"));
				outVO.setName(rs.getString("name"));
				outVO.setEmail(rs.getString("email"));
				outVO.setBirth(rs.getString("birth"));
				outVO.setGender(rs.getString("gender"));
				outVO.setCellPhone(rs.getString("cell_phone_num"));
				outVO.setLvl(rs.getString("lvl"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("red_dt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
			}
					
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return outVO;
	}
	
	/**
	 * 검색 및 조회
	 * @param vo
	 * @return
	 */
	public List<HrMemberVO> do_retrieve(DTO dto){
		SearchVO vo = (SearchVO) dto;
		List<HrMemberVO> list = new ArrayList<>();
		Connection conn = null; //db 연결
		PreparedStatement pstmt = null; //query 수행
		ResultSet rs = null;//결과처리
		
		//search query
		//이름=10, 메일=20, ID=30
		StringBuilder sbWhere = new StringBuilder();
		if(null!=vo.getSearchDiv()){
			if("10".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE name like ?||'%' \n");
			}else if("20".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE email like ?||'%' \n");
			}else if("30".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE user_id like ?||'%' \n");
			}
		}
				
		//main query
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT T1.*, T2.*										 \n");					
		sb.append(" FROM                                                     \n");
		sb.append(" (                                                        \n");
		sb.append(" SELECT B.rnum as num                                     \n");
		sb.append("       ,B.user_id                                         \n");
		sb.append(" 	  ,B.password                                        \n");
		sb.append(" 	  ,B.name                                            \n");
		sb.append(" 	  ,B.email                                           \n");
		sb.append(" 	  ,B.birth                                           \n");
	    sb.append("		  ,(                        						 \n");
	    sb.append("    		 SELECT code_nm        							 \n");
		sb.append(" 		 FROM code             							 \n");
		sb.append(" 		 WHERE code_id=B.gender 						 \n");
	    sb.append(" 		)gender                    						 \n");                              
	    sb.append("		  ,B.cell_phone_num         						 \n");
		sb.append(" 	  ,cd_name('LVL', B.lvl) lvl 						 \n"); 
		sb.append(" 	  ,B.mod_id                                          \n");
		sb.append(" 	  ,TO_CHAR(b.mod_dt,'YYYY-MM-DD') mod_dt             \n");
		sb.append(" FROM(                                                    \n");
		sb.append(" 	SELECT ROWNUM AS rnum, A.*                           \n");
		sb.append(" 	FROM(                                                \n");
		sb.append(" 		SELECT * FROM hr_member                          \n");
		sb.append(" 		--SEARCH CONDITION                               \n");
		//------------------------------------------------------------------------
		if(null!=vo.getSearchDiv()){//검색구분이 있는지 체크
			if(null!=vo.getSearchWord() && vo.getSearchWord().length()>0){//검색어가 있는지, 검색어의 길이가 0보다 큰지
				sb.append(sbWhere.toString());//검색구분, 검색어가 있다면 sbWhere를 sb에 붙이기
			}
		}
		//------------------------------------------------------------------------
		sb.append(" 		ORDER BY mod_dt DESC                             \n");
		sb.append(" 	)A                                                   \n");
//		sb.append("     WHERE ROWNUM <=(&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE) \n");  
//		sb.append("     )B                                                   \n");
//		sb.append(" WHERE B.rnum>= (&PAGE_SIZE*(&PAGE_NUM-1)+1)              \n");
		sb.append("     WHERE ROWNUM <=( ? *( ? -1)+?) \n");  
		sb.append("     )B                                                   \n");
		sb.append(" WHERE B.rnum>= ( ? *( ? -1)+1)              \n");
		sb.append(" )T1                                                      \n");
		sb.append(" CROSS JOIN                                               \n");
		sb.append(" (                                                        \n");
		sb.append(" SELECT COUNT(*) total_cnt                                \n");
		sb.append(" FROM hr_member                                           \n");
		sb.append(" --SEARCH CONDITION                                       \n");
		//------------------------------------------------------------------------
		if(null!=vo.getSearchDiv()){//검색구분이 있는지 체크
			if(null!=vo.getSearchWord() && vo.getSearchWord().length()>0){//검색어가 있는지, 검색어의 길이가 0보다 큰지
				sb.append(sbWhere.toString());//검색구분, 검색어가 있다면 sbWhere를 sb에 붙이기
			}
		}
		//------------------------------------------------------------------------
		sb.append(" )T2                                                      \n");
		
		LOG.debug("sql:\n"+sb.toString());
		
		try{
			conn=connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			//param
			if(null!=vo.getSearchDiv() && !"".equals(vo.getSearchDiv())){
				//검색어가 있는 경우
				//검색어
				//PAGE_SIZE
				//PAGE_NUM
				//PAGE_SIZE
				//PAGE_SIZE
				//PAGE_NUM
				//검색어
				pstmt.setString(1, vo.getSearchWord());
				pstmt.setInt(2, vo.getPageSize());
				pstmt.setInt(3, vo.getPageNum());
				pstmt.setInt(4, vo.getPageSize());
				pstmt.setInt(5, vo.getPageSize());
				pstmt.setInt(6, vo.getPageNum());
				pstmt.setString(7, vo.getSearchWord());
			}else{//검색어가 없는 경우 
				//PAGE_SIZE
				//PAGE_NUM
				//PAGE_SIZE
				//PAGE_SIZE
				//PAGE_NUM
				pstmt.setInt(1, vo.getPageSize());
				pstmt.setInt(2, vo.getPageNum());
				pstmt.setInt(3, vo.getPageSize());
				pstmt.setInt(4, vo.getPageSize());
				pstmt.setInt(5, vo.getPageNum());
			}
			
			LOG.debug("3.param: \n"+vo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HrMemberVO outVO = new HrMemberVO();
				outVO = new HrMemberVO();
				outVO.setNum(rs.getInt("num"));
				outVO.setUserId(rs.getString("user_id"));
				outVO.setPassword(rs.getString("password"));
				outVO.setName(rs.getString("name"));
				outVO.setEmail(rs.getString("email"));
				outVO.setBirth(rs.getString("birth"));
				outVO.setGender(rs.getString("gender"));
				outVO.setCellPhone(rs.getString("cell_phone_num"));
				outVO.setLvl(rs.getString("lvl"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setTotal(rs.getInt("total_cnt"));
				
				list.add(outVO);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return list;	
		
	}

}
