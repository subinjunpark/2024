package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindBookInfo {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
						// sql 문
						String sql = "SELECT b.TITLE, b.PUBS, b.PUB_DATE \n"
								+ "FROM BOOK b , AUTHOR a \n"
								+ "WHERE b.AUTHOR_ID = a.AUTHOR_ID AND a.AUTHOR_NAME = ?";
						// 바인딩
						Scanner scanner = new Scanner(System.in);
						System.out.print("이름을 입력하세요: ");
						String name = scanner.nextLine();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, "%"+name+"%");
						// 실행
						ResultSet rs = pstmt.executeQuery();
						
						// 4.결과처리
						while (rs.next()) {
						    String title = rs.getString("TITLE");
						    String pubs = rs.getString("PUBS");
						    String pubdate = rs.getString("PUB_DATE");
						    System.out.println("책 제목: " + title);
						    System.out.println("출판사: " + pubs);
						    System.out.println("출판날짜: " + pubdate);
						
						}
						
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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