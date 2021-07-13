package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	// 필드

	// 생성자

	// 메소드게세

	// 메소드일반

	// 작가 삭제하기
	public int authorDelete(int authorId) {
		int count = -1;

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
			query += "delete from author ";
			query += "where author_id = ?";

			pstmt = conn.prepareStatement(query); // 쿼리문 만들기(데이터입력전에 해야함)

			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제 되었습니다.");

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
		return count;
	}

	// 작가 수정하기
	public int authorUpdate(AuthorVo uAuthorVo) {
		int count = -1;

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

			pstmt.setString(1, uAuthorVo.getAuthorName()); // 데이터 입력
			pstmt.setString(2, uAuthorVo.getAuthorDesc());
			pstmt.setInt(3, uAuthorVo.getAuthorId()); // 데이터 자료형 주의

			count = pstmt.executeUpdate();

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
		return count;
	}

	// 작가 등록하기
	public int authorInsert(AuthorVo iAuthorVo) {
		int count = -1;

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
			String query = "";
			query += "insert into author ";
			query += "values(seq_author_id.nextval, ?, ?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, iAuthorVo.getAuthorName());
			pstmt.setString(2, iAuthorVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

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

		return count; // 성공갯수 리턴
	}

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {
		// DB값을 가져와서 ArrayList로 전달
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

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
			query += "select author_id, ";
			query += "       author_name, ";
			query += "       author_desc ";
			query += "from author";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(authorVo);
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
		return authorList;
	}
}