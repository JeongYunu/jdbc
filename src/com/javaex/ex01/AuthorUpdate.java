package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {
		// 0.import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1.JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3.SQL문 준비 / 바인딩 / 실행
			String query = ""; // 자바 문자열(쿼리문) 준비, 입력될 데이터는 ?로 표현
			query += "update author ";
			query += "set author_name = ?, ";
			query += "    author_desc = ? ";
			query += "where author_id = ?";

			pstmt = conn.prepareStatement(query); // 쿼리문 만들기(데이터입력전에 해야함)

			pstmt.setString(1, "서채영"); // 데이터 입력
			pstmt.setString(2, "서울특별시");
			pstmt.setInt(3, 7); // 데이터 자료형 주의

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정 되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5.자원정리
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
