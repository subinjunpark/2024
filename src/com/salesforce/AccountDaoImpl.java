package com.salesforce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDAO {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "webdb", "1234");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 실패!");
		}
		return conn;
	}

	@Override
	public List<AccountVO> getList(String tradingDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO accountVO = null;
		
		List<AccountVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			String sql = " select seq_id, deposit, withdraw, "
      					 + " to_char(tr_date, 'YYYYMMDD'), balance \n"
      					 + " from account \n"
      					 + " where to_char(tr_date, 'YYYYMMDD') = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, tradingDate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				accountVO = new AccountVO (
							rs.getLong(1),
							rs.getLong(2),
							rs.getLong(3),
							rs.getString(4),
							rs.getLong(5)
							);
				list.add(accountVO);
			}
		} catch(SQLException e) {
			System.err.println("ERROR : " + e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.err.println("ERROR : " + e.getMessage());
			}
		}
		return list;
	}
	@Override
	public List<AccountVO> getList(String startDate, String endDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO accountVO = null;
		
		List<AccountVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			String sql = " select seq_id, deposit, withdraw, "
      					 + " to_char(tr_date, 'YYYYMMDD'), balance \n"
      					 + " from account \n"
      					 + " where to_char(tr_date, 'YYYYMMDD') "
      					 + " between ? and ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				accountVO = new AccountVO (
							rs.getLong(1),
							rs.getLong(2),
							rs.getLong(3),
							rs.getString(4),
							rs.getLong(5)
							);
				list.add(accountVO);
			}
		} catch(SQLException e) {
			System.err.println("ERROR : " + e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.err.println("ERROR : " + e.getMessage());
			}
		}
		return list;
	}


	@Override
	public List<AccountVO> getBalance() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AccountVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			String sql = " select balance "
					       + " from account \n"
					       + " where seq_id = (select max(seq_id) from account)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO accountVO = new AccountVO (
							rs.getLong(1)
							);
				list.add(accountVO);
			}
		} catch(SQLException e) {
			System.err.println("ERROR : " + e.getMessage());
		}
		return list;
	}

	@Override
	public boolean insertTradeInfo(String depositWithdraw, Long money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			if("deposit".equals(depositWithdraw)) {
				conn = getConnection();
				String sql = " INSERT INTO account \n"
      						 + " (seq_id, deposit, withdraw, tr_date, balance) \n"
      						 + " select ACCOUNT_SEQ_ID.nextval, ?, 0, sysdate, balance + ? \n"
      						 + " from account \n"
      						 + " where seq_id = (select max(seq_id) from account)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, money);
				pstmt.setLong(2, money);
				
				insertedCount = pstmt.executeUpdate();
			} else if("withdraw".equals(depositWithdraw)) {
				List<AccountVO> list = getBalance();
				Long a = list.get(list.size()-1).getBalance();	// 잔액
				if(money > a) {
					System.out.println("잔액이 부족합니다.");
				} else {
					conn = getConnection();
					String sql = " INSERT INTO account \n"
      							 + " (seq_id, deposit, withdraw, tr_date, balance) \n"
      							 + " select ACCOUNT_SEQ_ID.nextval, 0, ?, sysdate, balance - ? \n"
      							 + " from account \n"
      							 + " where seq_id = (select max(seq_id) from account)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setLong(1, money);
					pstmt.setLong(2, money);
					
					insertedCount = pstmt.executeUpdate();
				}
				
			}
		} catch(SQLException e) {
			System.err.println("ERROR : " + e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.err.println("ERROR : " + e.getMessage());
			}
		}
		return insertedCount == 1;
	}
	
	
	
	
}
