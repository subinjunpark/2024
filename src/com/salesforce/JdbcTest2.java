package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest2 {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = " SELECT author_id,author_name,author_desc FROM AUTHOR order by 1";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				System.out.print(rs.getInt("AUTHOR_ID") + ", ");
				System.out.print(rs.getString("author_name") + ", ");
				System.out.print(rs.getString("author_desc") + "\n");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e)

			
​
		}
​
	}

}