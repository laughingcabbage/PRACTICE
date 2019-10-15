package com.hr.code;

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

public class CodeDao implements WorkDiv {

	private final Logger LOG = Logger.getLogger(CodeDao.class);
	private ConnectionMaker connectionMaker;
	
	public CodeDao(){
		connectionMaker = new ConnectionMaker();
	}
	
	@Override
	public int do_insert(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodeVO> do_retrieve(DTO dto) {
		CodeVO vo = (CodeVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CodeVO> list =new ArrayList<>();
		
		try{
			StringBuilder sb=new StringBuilder();
			sb.append(" SELECT b.code_id,                   \n");
			sb.append("        b.code_nm                    \n");
			sb.append(" FROM code_type A JOIN code B        \n");
			sb.append(" ON a.code_type_id = b.code_type_id  \n");
			sb.append(" WHERE b.code_type_id = ?            \n");
			sb.append(" AND b.use_yn =1                     \n");
			sb.append(" ORDER BY b.num                      \n");
			
			LOG.debug("1.==========================");
			LOG.debug("1.query\n"+sb.toString());
			LOG.debug("1.==========================");
			
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			//param
			pstmt.setString(1, vo.getCodeTypeId());	
			LOG.debug("2.==========================");
			LOG.debug("2.param="+vo.getCodeTypeId());
			LOG.debug("2.==========================");			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CodeVO outVO =new CodeVO();
				
				outVO.setCodeId(rs.getString("code_id"));
				outVO.setCodeNm(rs.getString("code_nm"));
				list.add(outVO);
			}
			
			
		}catch(SQLException e){
			LOG.debug("==========================");
			LOG.debug("SQLException"+e.toString());
			LOG.debug("==========================");	
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return list;
	}

}
