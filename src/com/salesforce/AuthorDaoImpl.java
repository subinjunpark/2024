package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

	//반복되는 "JDBC 드라이버 (Oracle) 로딩"을 한번에 해결
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public int insert(AuthorVO vo) {

		System.out.println("AuthorDaoImpl.insert(vo) --> " + vo);

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			
			//반복되는 "JDBC 드라이버 (Oracle) 로딩"을 한번에 해결
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = " INSERT INTO author(author_id, author_name, author_desc) \n"
					+ " values(seq_author_id.nextval, ?, ?) ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAuthor_name());
			pstmt.setString(2, vo.getAuthor_desc());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + " 건이 저장 되었습니다.");

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

		return count;
	}

	@Override
	public List<AuthorVO> getList() {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		List<AuthorVO> list = new ArrayList<AuthorVO>();

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = " SELECT * FROM AUTHOR ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				count++;
				// System.out.print(rs.getInt(1)+ "\t" + rs.getString(2)+ "\t" +
				// rs.getString(3)+ "\n");
				// AuthorVO(int author_id, String author_name, String author_desc)
				AuthorVO vo = new AuthorVO(rs.getInt(1), rs.getString(2), rs.getString(3));
				list.add(vo);
			}
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
		return list;
	}

	@Override
	public int delete(Long authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = " DELETE FROM AUTHOR a \n" + " WHERE a.AUTHOR_ID = ? ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, authorId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + " 건이 삭제 되었습니다.");

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

		return count;
	}

	@Override
	public int update(AuthorVO vo) {

		System.out.println("AuthorDaoImpl.insert(vo) --> " + vo);

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = " UPDATE AUTHOR a\n" + " SET a.AUTHOR_DESC = ? \n" + " WHERE a.AUTHOR_ID = ? ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAuthor_desc());
			pstmt.setInt(2, vo.getAuthor_id());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + " 건이 수정 되었습니다.");

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

		return count;
	}

}