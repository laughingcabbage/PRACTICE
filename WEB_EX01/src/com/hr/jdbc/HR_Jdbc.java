package com.hr.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HR_Jdbc {
/*
 * 1.jdbc 드라이버를 로드한다
 * 2.데이터베이스 connection을 생성한다
 * 3.PreparedStatement 생성
 * 4.쿼리 실행
 * 5.쿼리 실행 결과
 * 6.PreparedStatement종료
 * 7.connection 종료
 */
	public static void main(String[] args) {
		String dbUrl = "jdbc:oracle:thin:@211.238.142.144:1521:orcl";
		String dbUser = "scott";
		String dbPass = "1024";
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		try {
			//1.jdbc드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("1.jdbc 드라이버 로딩");
		
		//2.데이터베이스 connection 생성
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO dept2( \n");
		sb.append("	dcode,            \n");
		sb.append("	dname,            \n");
		sb.append("	pdept,            \n");
		sb.append("	area              \n");
		sb.append(") VALUES (         \n");
		sb.append("	144,              \n");
		sb.append("	'YH',             \n");
		sb.append("	'1006',           \n");
		sb.append("	'홍대'			  \n");
		sb.append(")                  \n");
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			pstmt = conn.prepareStatement(sb.toString());
			System.out.println("3--------------pstmt--"+pstmt);
			System.out.println(sb.toString());
			System.out.println("----------------");
			
			//4.쿼리 실행
			int result = pstmt.executeUpdate();
			System.out.println("----------------");
			System.out.println(result);
			System.out.println("----------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//나중에 생성한 객체부터 차례대로 close한다. 
			if(null!=pstmt){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null!=conn){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("2.데이터베이스 커넥션 생성:"+conn);
		
		//3.PreparedStatement 객체 생성
		
	}
}