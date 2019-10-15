package com.hr.board;

import java.sql.CallableStatement;
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
import com.hr.cmn.WorkDiv;
import com.hr.member.SearchVO;

public class BoardDao implements WorkDiv {

	private final Logger LOG = Logger.getLogger(BoardDao.class);
	private ConnectionMaker connectionMaker;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName : boardDao
	 * 2. ClassName  : boardDao
	 * 3. Comment   : 
	 * 4. 작성자    : sist
	 * 5. 작성일    : 2019. 6. 27. 오후 2:17:26
	 * </PRE>
	 */
	public BoardDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	public int do_updateReadCnt(DTO dto) {
		BoardVO vo = (BoardVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE board                                                       \n");
		sb.append(" SET read_cnt = (SELECT NVL(read_cnt,0)+1 FROM board WHERE seq = ? )\n");
		sb.append(" WHERE seq = ?                                                      \n");
		
		try{
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.=============================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1.=============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getSeq());
			pstmt.setString(2, vo.getSeq());
			
			LOG.debug("2.=============================");
			LOG.debug("param:"+vo.toString());
			LOG.debug("2.=============================");
			
			flag = pstmt.executeUpdate();
			
		}catch(SQLException e){
			LOG.debug("=============================");
			LOG.debug("SQLException:"+e.toString());
			LOG.debug("=============================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag;
	}

	@Override
	public int do_insert(DTO dto) {
		BoardVO vo = (BoardVO) dto;
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO board (     \n");
		sb.append("     seq,                \n");
		sb.append("     title,              \n");
		sb.append("     contents,           \n");
		sb.append("     reg_id,             \n");
		sb.append("     reg_dt              \n");
		sb.append(" ) VALUES (              \n");
		sb.append("     SEQ_BOARD.nextval,  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     SYSDATE             \n");
		sb.append(" )                       \n");
		
		LOG.debug("1=================================");
		LOG.debug("query:\n"+sb.toString());
		LOG.debug("1=================================");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			//param 설정
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getRegId());
			
			//디버그
			LOG.debug("2=================================");
			LOG.debug("param:"+vo.toString());
			LOG.debug("2=================================");
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("3=================================");
			LOG.debug("flag:"+flag);
			LOG.debug("3=================================");
				
		}catch(SQLException e){
			LOG.debug("===============================");
			LOG.debug("SQLException"+e.toString());
			LOG.debug("===============================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		BoardVO vo = (BoardVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE board          \n");
		sb.append(" SET title = ?,        \n");
		sb.append("     contents = ?,     \n");
		sb.append("     reg_id = ?,       \n");
		sb.append("     reg_dt = sysdate  \n");
		sb.append(" WHERE seq = ?         \n");
		
		try{
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.=============================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1.=============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getRegId());
			pstmt.setString(4, vo.getSeq());
			
			LOG.debug("2.=============================");
			LOG.debug("param:"+vo.toString());
			LOG.debug("2.=============================");
			
			flag = pstmt.executeUpdate();
			
		}catch(SQLException e){
			LOG.debug("=============================");
			LOG.debug("SQLException:"+e.toString());
			LOG.debug("=============================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto){
		BoardVO vo = (BoardVO) dto;
		int flag=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM board  \n");
		sb.append(" WHERE seq = ?      \n");
		
		try{
			//커넥션 생성
			conn = connectionMaker.getConnection();
			//autoCommit되지 않게 설정하는 것. transaction을 개발자가 관리한다. 
			conn.setAutoCommit(false);
			
			//쿼리 디버그
			LOG.debug("1======================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1======================");
			
			//preparedStatement생성
			pstmt = conn.prepareStatement(sb.toString());
			
			//query param설정
			pstmt.setString(1, vo.getSeq());
			LOG.debug("2======================");
			LOG.debug("param:\n"+sb.toString());
			LOG.debug("2======================");
			
			flag = pstmt.executeUpdate();
			
			//flag가 0보다 크면(즉, 성공했으면) commit, 아니면 rollback
			if(flag>0){
				//로그 남기기
				LOG.debug("3======================");
				LOG.debug("transaction: "+conn);
				LOG.debug("3======================");
				//conn.rollback();
				//conn.commit();
			}else{
				//conn.rollback();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
	
		LOG.debug("4==================");
		LOG.debug("flag:"+flag);
		LOG.debug("4==================");
		
		return flag;
	}

	public String getSeq(){
		String seq =""; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT MAX(seq) seq \n");
		sb.append(" FROM board          \n");
		sb.append(" WHERE seq>0         \n");
		
		try{
			//커넥션 생성
			conn = connectionMaker.getConnection();
			
			//쿼리 디버그
			LOG.debug("1======================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1======================");
			
			//preparedStatement 생성
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				seq = rs.getString("seq");
				LOG.debug("2======================");
				LOG.debug("seq:"+seq);
				LOG.debug("2======================");
			}
			
		}catch(SQLException e){
			LOG.debug("==================");
			LOG.debug("SQLException:"+e.getMessage());
			LOG.debug("==================");
		}finally{
			//자원반납
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return seq;
	}
	
	//테스트 성공
	@Override
	public DTO do_selectOne(DTO dto) {
		BoardVO vo = (BoardVO) dto;
		BoardVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT seq,                                          \n");
		sb.append("     title,                                           \n");
		sb.append("     read_cnt,                                        \n");
		sb.append("     contents,                                        \n");
		sb.append("     reg_id,                                          \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD HH24:mi:ss') reg_dt   \n");
		sb.append(" FROM board                                           \n");
		sb.append(" WHERE seq = ?                                        \n");
		
		try{
			//커넥션 생성
			conn = connectionMaker.getConnection();
			
			//쿼리 디버그
			LOG.debug("1======================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1======================");
			
			//preparedStatement 생성
			pstmt = conn.prepareStatement(sb.toString());
			
			//query param설정
			pstmt.setString(1, vo.getSeq());
			
			LOG.debug("2======================");
			LOG.debug("param seq:"+vo.getSeq());
			LOG.debug("2======================");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				outVO = new BoardVO();
				outVO.setSeq(rs.getString("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setReadCnt(rs.getString("read_cnt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
			}
			
		}catch(SQLException e){
			LOG.debug("==================");
			LOG.debug("SQLException:"+e.getMessage());
			LOG.debug("==================");
		}finally{
			//자원반납
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return outVO;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		SearchVO vo = (SearchVO) dto;
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//검색조건 설정 
		//제목=10, 내용=20, id=30
		StringBuilder sbWhere = new StringBuilder();
		if(null!=vo.getSearchDiv()){
			if("10".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE title like ?||'%' \n");
			}else if("20".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE contents like ?||'%' \n");
			}else if("30".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE reg_id like ?||'%' \n");
			}
		}
		
		//메인쿼리
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT T1.*, T2.*                                                                              \n");
		sb.append(" FROM                                                                                           \n");
		sb.append(" (                                                                                              \n");
		sb.append("     SELECT B.rnum as num                                                                       \n");
		sb.append("           ,B.seq                                                                               \n");
		sb.append("           ,B.title                                                                             \n");
		sb.append("           ,B.read_cnt                                                                          \n");
		sb.append("           ,B.contents                                                                          \n");
		sb.append("           ,B.reg_id                                                                            \n");
		sb.append("           ,DECODE(TO_CHAR(B.reg_dt, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'),             \n");
		sb.append(" 				  TO_CHAR(B.reg_dt, 'HH24:MI:SS'), TO_CHAR(B.reg_dt, 'YYYY-MM-DD')) AS reg_dt  \n");
		sb.append("     FROM(                                                                                      \n");
		sb.append("         SELECT ROWNUM AS rnum, A.*                                                             \n");
		sb.append("         FROM(                                                                                  \n");
		sb.append("             SELECT * FROM board                                                                \n");
		sb.append("             --SEARCH CONDITION                                                                 \n");
		//-------------------------------------------------------------------------------------------------------------
		//검색조건이 있으면 검색조건을 쿼리에 덧붙이기
		if(null!=vo.getSearchDiv()){
			if(null!=vo.getSearchWord() && vo.getSearchWord().length()>0){
				sb.append(sbWhere.toString());
			}
		}
		//-------------------------------------------------------------------------------------------------------------
		sb.append("             ORDER BY reg_dt DESC                                                               \n");
		sb.append("         )A                                                                                     \n");
		sb.append("         WHERE ROWNUM <= ( ? *( ? -1)+ ?)                                  \n");
		sb.append("     )B                                                                                         \n");
		sb.append("     WHERE B.rnum >= ( ? *( ? -1)+1)                                               \n");
		sb.append(" )T1                                                                                            \n");
		sb.append(" CROSS JOIN                                                                                     \n");
		sb.append(" (                                                                                              \n");
		sb.append("     SELECT COUNT(*) total_cnt                                                                  \n");
		sb.append("     FROM board a                                                                               \n");
		sb.append("     --SEARCH CONDITION                                                                         \n");
		//-------------------------------------------------------------------------------------------------------------
		//검색조건이 있으면 쿼리에 검색조건 덧붙이기
		if(null!=vo.getSearchDiv()){
			if(null!=vo.getSearchWord() && vo.getSearchWord().length()>0){
				sb.append(sbWhere.toString());
			}
		}
		//-------------------------------------------------------------------------------------------------------------
		sb.append(" )T2																							   \n");
		
		LOG.debug("sql:\n"+sb.toString());
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			//param설정
			if(null!=vo.getSearchDiv() && !"".equals(vo.getSearchDiv())){//검색어가 있으면
				pstmt.setString(1, vo.getSearchWord());
				pstmt.setInt(2, vo.getPageSize());
				pstmt.setInt(3, vo.getPageNum());
				pstmt.setInt(4, vo.getPageSize());
				pstmt.setInt(5, vo.getPageSize());
				pstmt.setInt(6, vo.getPageNum());
				pstmt.setString(7, vo.getSearchWord());
			}else{//검색어가 없으면
				pstmt.setInt(1, vo.getPageSize());
				pstmt.setInt(2, vo.getPageNum());
				pstmt.setInt(3, vo.getPageSize());
				pstmt.setInt(4, vo.getPageSize());
				pstmt.setInt(5, vo.getPageNum());
			}
			
			LOG.debug("param:\n"+vo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardVO outVO = new BoardVO();
				outVO.setSeq(rs.getString("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setReadCnt(rs.getString("read_cnt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total_cnt"));
				
				list.add(outVO);
			}
			
		}catch(SQLException e){
			LOG.debug("========================");
			LOG.debug("SQLException="+e.getMessage());
			LOG.debug("========================");
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return list;
	}

	public int do_upsert(DTO dto){
		int flag = 0;
		BoardVO vo = (BoardVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" MERGE INTO board ta                                                  \n");
		sb.append(" USING                                                                \n");
		sb.append(" (                                                                    \n");
		sb.append("     SELECT                                                           \n");
		sb.append("         ? 			AS seq,                                          \n");
		sb.append("         ?			AS title,                                        \n");
		sb.append("         ? 			AS contents,                                     \n");
		sb.append("         ? 			AS reg_id                                        \n");
		sb.append("     FROM dual                                                        \n");
		sb.append(" )tb                                                                  \n");
		sb.append(" ON(ta.seq = tb.seq)                                                  \n");
		sb.append(" WHEN MATCHED THEN                                                    \n");
		sb.append(" UPDATE                                                               \n");
		sb.append(" SET title    = tb.title,                                             \n");
		sb.append("     contents = tb.contents,                                          \n");
		sb.append("     reg_id   = tb.reg_id,                                            \n");
		sb.append("     reg_dt   = sysdate                                               \n");
		sb.append(" WHEN NOT MATCHED THEN                                                \n");
		sb.append(" INSERT (ta.seq, ta.title, ta.contents, ta.reg_id, ta.reg_dt)         \n");
		sb.append(" VALUES (seq_board.nextval, tb.title, tb.contents, tb.reg_id, sysdate)\n");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			LOG.debug("1.==============================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1.==============================");
			
			pstmt.setString(1, vo.getSeq());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getRegId());
			
			LOG.debug("2.==============================");
			LOG.debug("param:\n"+vo);
			LOG.debug("2.==============================");
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("3.==============================");
			LOG.debug("flag:"+flag);
			LOG.debug("3.==============================");
			
		}catch(SQLException e){
			LOG.debug("4.==============================");
			LOG.debug("SQLException:"+e.toString());
			LOG.debug("4.==============================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}

		return flag;
	}
	
	/**
	 * 
	 * @Method Name  : callProcedure
	 * @작성일   : 2019. 7. 2.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : call procedure
	 * @param dto
	 */
	public void callProcedure(DTO dto){
		BoardVO vo = (BoardVO) dto;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try{
			conn = connectionMaker.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("{call up_sal(?)}");
			
			LOG.debug("1===========================");
			LOG.debug("query:"+sb.toString());
			LOG.debug("1===========================");
			
			cstmt = conn.prepareCall(sb.toString());
			cstmt.setInt(1, Integer.parseInt(vo.getSeq()));
			
			LOG.debug("2===========================");
			LOG.debug("param:"+vo.getSeq());
			LOG.debug("2===========================");
		
			int flag = cstmt.executeUpdate();
			
			LOG.debug("3===========================");
			LOG.debug("flag:"+flag);
			LOG.debug("3===========================");

		}catch(SQLException e){
			LOG.debug("===========================");
			LOG.debug("SQLException:"+e.getMessage());
			LOG.debug("===========================");
		}finally{
			try{
				if(null!=cstmt){
					cstmt.close();
				}
			}catch(SQLException e){
				
			}
			JDBCReturnReso.close(conn);
		}
		
		
	}
	
	
	
	
	
}
