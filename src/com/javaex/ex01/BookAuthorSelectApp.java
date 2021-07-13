package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSelectApp {

	public static void main(String[] args) {
		// 0.import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3.SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select bo.book_id, ";
			query += "       bo.title, ";
			query += "       bo.pubs, ";
			query += "       to_char(bo.pub_date, 'yyyy-mm-dd day') pub_date, ";
			query += "       au.author_id, ";
			query += "       au.author_name, ";
			query += "       au.author_desc ";
			query += "from author au, book bo ";
			query += "where au.author_id = bo.author_id";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bBookId = rs.getInt("book_id");
				String bTitle = rs.getString("title");
				String bPubs = rs.getString("pubs");
				String bPubDate = rs.getString("pub_date");
				int aAuthorId = rs.getInt("author_id");
				String aAuthorName = rs.getString("author_name");
				String aAuthorDesc = rs.getString("author_desc");

				System.out.println(bBookId + ", " + bTitle + ", " + bPubs + ", " + bPubDate + ", " + aAuthorId + ", "
						+ aAuthorName + ", " + aAuthorDesc);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5.자원정리
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
