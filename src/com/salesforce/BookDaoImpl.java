//package com.salesforce;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookDaoImpl implements BookDao {
//
//	private Connection getConnection() {
//		Connection conn = null;
//		try {
//			// 1. JDBC 드라이버 (Oracle) 로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return conn;
//	}
//
//	public int insert(BookVO bo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int count = 0;
//		try {
//			conn = getConnection();
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String sql = "INSERT INTO Book b(Book_ID, Title, Pubs, Pub_date, Author_id)\n"
//					+ "VALUES (seq_book_id.nextval, ?, ?, to_date(?,'YYYYMMDD'), ?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bo.getBook_title());
//			pstmt.setString(2, bo.getPubs());
//			pstmt.setString(3, bo.getPub_date());
//			pstmt.setInt(4, bo.getAuthor_id());
//			count = pstmt.executeUpdate();
//			System.out.println(count + " 건이 저장 되었습니다.");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//		}
//
//		return count;
//	}
//
//	public int update(BookVO bo) {
//        // 구현 내용 작성
//        return 0; // 임시로 0을 반환했지만 실제로는 업데이트된 행의 개수를 반환하게 될 것이다.
//    }
//
//    public int delete(BookVO bo) {
//        // 구현 내용 작성
//        return 0; // 임시로 0을 반환했지만 실제로는 삭제된 행의 개수를 반환하게 될 것이다.
//    }
//
//    public List<BookVO> getList() {
//
//		// 0. import java.sql.*;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int count = 0;
//		List<BookVO> list = new ArrayList<BookVO>();
//
//		try {
//			conn = getConnection();
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//			// sql 문
//			String sql = " SELECT * FROM Book b";
//			// 바인딩
//			pstmt = conn.prepareStatement(sql);
//
//			// 실행
//			rs = pstmt.executeQuery();
//
//			// 4.결과처리
//			while (rs.next()) {
//				count++;
//				// System.out.print(rs.getInt(1)+ "\t" + rs.getString(2)+ "\t" +
//				// rs.getString(3)+ "\n");
//				// AuthorVO(int author_id, String author_name, String author_desc)
//				BookVO vo = new BookVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
//				list.add(vo);
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//		}
//		return list;
//	}
//}
//
//

package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 로드 실패!");
		}
		return conn;
	}

	@Override
	public List<BookVo> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<BookVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = " SELECT b.BOOK_ID , \n" + "        b.TITLE , \n" + "        b.PUBS , \n"
					+ "        TO_CHAR(b.PUB_DATE, 'YYYY/MM/DD' ) pubdate, \n" + "        b.AUTHOR_ID \n"
					+ " FROM BOOK b";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BookVo vo = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		}

		return list;
	}

	public List<BookVo> getList(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<BookVo> list = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = " SELECT b.BOOK_ID, b.TITLE, b.PUBS, to_char(b.PUB_DATE,'YYYYMMDD') PUB_DATE, a.AUTHOR_ID, a.AUTHOR_NAME\n"
					+ " FROM BOOK b, AUTHOR a\n" + " WHERE b.author_id = a.author_id" + " AND a.author_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 바인딩

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVo vo = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), id);
				vo.setAuthor_name(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return list;
	}

	@Override
	public boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			String sql = " INSERT INTO BOOK " + " VALUES(SEQ_BOOK_ID.NEXTVAL, " + " ?, ?, to_date(?,'YYYYMMDD'), ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setInt(4, vo.getAuthor_id());

			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return insertedCount == 1;
	}

	public BookVo get(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		BookVo vo = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = " SELECT book_id, title, pubs, to_char(pub_date,'yyyymmdd') pub_date " + " FROM book "
					+ " WHERE book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id)); // 바인딩

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return vo;
	}

	@Override
	public boolean update(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;

		try {
			conn = getConnection();
			String sql = " UPDATE BOOK SET \n" + " title = ?, \n" + " pubs = ?, \n"
					+ " pub_date = to_date(?,'YYYYMMDD'), \n" + " AUTHOR_ID = ? \n" + " WHERE book_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setInt(4, vo.getAuthor_id());
			pstmt.setInt(5, vo.getBook_id());

			updatedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return 1 == updatedCount;
	}

	public List<BookVo> findKeyword(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = " SELECT b.BOOK_ID , \n" + "        b.TITLE , \n" + "        b.PUBS , \n"
					+ "        TO_CHAR(b.PUB_DATE, 'YYYY/MM/DD' ) pubdate, \n" + "        b.AUTHOR_ID \n"
					+ " FROM BOOK b \n" + " WHERE b.TITLE || b.PUBS LIKE ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%"); // 바인딩

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVo vo = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return list;
	}

	@Override
	public boolean delete(Long bookId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = " DELETE FROM BOOK " + " WHERE book_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bookId);

			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return 1 == deletedCount;

	}

}