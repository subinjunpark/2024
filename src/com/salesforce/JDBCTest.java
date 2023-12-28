package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");
			// 3. SQL문 준비 / 바인딩 / 실행
			String sql = "SELECT e.EMPLOYEE_ID ,\n"
						+ "		e.FIRST_NAME ,\n"
						+ "		e.SALARY\n"
						+ "FROM EMPLOYEES e";
			//바인딩
			pstmt = conn.prepareStatement(sql);
			//실행
			rs = pstmt.executeQuery(); // select
			// 4. 결과처리
			while (rs.next()) {
				System.out.print(rs.getInt(1)+", ");
				System.out.print(rs.getString(2)+", ");
				System.out.println(rs.getInt(3));
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
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

}
