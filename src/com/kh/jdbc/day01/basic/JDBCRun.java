package com.kh.jdbc.day01.basic;

import java.sql.*;

public class JDBCRun {
	public static void main(String[] args) {
		/*
		 * JDBC 코딩 절차
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 객체 생성(쿼리문 실행 준비)
		 * 4, SQL 전송(쿼리문 실행)
		 * 5. 결과 받기(ResultSet으로 받음)
		 * 6. 자원 해제(close())
		 */

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";

		String query = "SELECT * FROM DEPARTMENT";

		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결 생성 (sqldeveloper 접속 버튼 누름)
			Connection conn = DriverManager.getConnection(url, username, password);
			// 3. Statement 객체 생성(쿼리문 실행 준비) = 워크시트가 열림
			Statement stat = conn.createStatement();
			// 4. SQL 전송(명령문 실행, 즉 초록색 재생 버튼 누름)
			// 5. 결과 받기(Resultset)
			ResultSet rSet = stat.executeQuery(query);
			// 배열에 있는 값을 꺼내 쓸 때 함께 쓰는 것은? 반복문 while
			// 후처리 필요!
			while (rSet.next()) {
				System.out.print("부서코드 : " + rSet.getString("DEPT_ID"));
				System.out.println(", 부서명 : " + rSet.getString("DEPT_TITLE"));
			}
			// 6. 자원 해제
			rSet.close();
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
