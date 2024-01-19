package com.kh.jdbc.day03.pstmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "STUDENT";
	String password = "STUDENT";
	
	String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

	//	JDBC를 이용하여
	//	Oracle DB에 접속하는 클래스
	//	JDBC 코딩 절차
	
	/*
	* 1. 드라이버 등록
	* 2. DB 연결 생성
	* 3. 쿼리문 싱행 준비
	* 4. 쿼리문 실행 및 5. 결과 받기
	* 6. 자원해제(close())
	*/
	
	public void updateMember(Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '" + member.getMemberPw() 
				+ "', EMAIL = '" + member.getEmail() + "' "
				+ "', HOBBY = '" + member.getHobby() + "' "
				+ "WHERE MEMBER_ID = '" + member.getMemberId() + "'";
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			int result = stat.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";
		Member member = null;
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			int result = stat.executeUpdate(query);
			if (result > 0) {
				// delete 성공
			} else {
				// delete 실패, 롤백
			}
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
	
	public Member printById(Member mOne) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?" 
						+ " AND MEMBER_PWD = ?"; // 바뀌는 부분 첫 번째
		Member member = null;
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;				// 바뀌는 부분 두 번째
		ResultSet rSet = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(url, username, password);
			
			// 바뀌는 부분 세 번째
			pstmt = conn.prepareStatement(query);		// 쿼리문을 미리 컴파일 함
			pstmt.setString(1, mOne.getMemberId());		// ?(위치홀더)에 값을 넣는 코드
			pstmt.setString(2, mOne.getMemberPw());		// 시작은 1로 하고 마지막 수는 물음표의 갯수와 같다. (물음표 = 위치홀더)
			
			// 바뀌는 부분 네 번째
			rSet = pstmt.executeQuery();				// 쿼리문을 미리 컴파일하고 위치홀더 값을 셋팅하고 쿼리문 실행 및 결과 받기
			
			
			if (rSet.next()) {
				member = new Member();
				member.setMemberId(rSet.getString("MEMBER_ID"));
				member.setMemberPw(rSet.getString("MEMBER_PWD"));
				member.setMemberName(rSet.getString("MEMBER_NAME"));
				member.setEmail(rSet.getString("EMAIL"));
				member.setGender(rSet.getString("GENDER").charAt(0));
				member.setAge(rSet.getInt("AGE"));
				member.setPhone(rSet.getString("PHONE"));
				member.setAddress(rSet.getString("ADDRESS"));
				member.setHobby(rSet.getString("HOBBY"));
				member.setEnrollDate(rSet.getDate("ENROLL_DATE"));
			}
			rSet.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	public Member selectById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";
		Member member = null;

		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(query);
			member = new Member();
			if (rSet.next()) {
				member.setMemberId(rSet.getString("MEMBER_ID"));
				member.setMemberPw(rSet.getString("MEMBER_PWD"));
				member.setMemberName(rSet.getString("MEMBER_NAME"));
				member.setEmail(rSet.getString("EMAIL"));
				member.setGender(rSet.getString("GENDER").charAt(0));
				member.setAge(rSet.getInt("AGE"));
				member.setPhone(rSet.getString("PHONE"));
				member.setAddress(rSet.getString("ADDRESS"));
				member.setHobby(rSet.getString("HOBBY"));
				member.setEnrollDate(rSet.getDate("ENROLL_DATE"));
			}
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
		return member;
	}

	public void insertMember(Member member) {
		String query = "INSERT INTO MEMBER_TBL VALUES('" 
				+ member.getMemberId() + "', '"
				+ member.getMemberPw() + "', '"
				+ member.getMemberName() + "', '"
				+ member.getGender() + "', "
				+ member.getAge() + ", '"
				+ member.getEmail() + "', '"
				+ member.getPhone() + "', '"
				+ member.getAddress() + "', '"
				+ member.getHobby() + "', sysdate)";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
//			ResultSet rSet = stat.executeQuery(query);
			int result = stat.executeUpdate(query); // DML의 경우 호출하는 메소드
			if (result > 0) { 						// auto-commit이 되기 때문에 따로 커밋을 안해줘도 됨
				// insert 성공
			} else {
				// insert 실패
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 클라스 바로 밑에 코드 쓸 수 없음
	// 메소드로 감싸줘야함
	// 메소드 안에 코드를 쓰고 필요할 때 호출해서 씀
	public List<Member> selectAll() {

		String query = "SELECT * FROM MEMBER_TBL";
		List<Member> mList = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(query); // 초록색 재생버튼 누름
			// rSet에는 전부 다 담겨있기 때문에 한 행씩 꺼내서 출력해줘야 함

			mList = new ArrayList<Member>(); // 누락 주의! 꼭 써야함
			while (rSet.next()) {
				Member member = new Member();
				String memberId = rSet.getString("MEMBER_ID");
				String memberPw = rSet.getString("MEMBER_PWD");
				String memberName = rSet.getString("MEMBER_NAME");
				String email = rSet.getString("EMAIL");
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				member.setMemberName(memberName);
				member.setEmail(email);

				member.setGender(rSet.getString("GENDER").charAt(0));
				member.setAge(rSet.getInt("AGE"));
				member.setPhone(rSet.getString("PHONE"));
				member.setAddress(rSet.getString("ADDRESS"));
				member.setHobby(rSet.getString("HOBBY"));
				member.setEnrollDate(rSet.getDate("ENROLL_DATE")); // getDate 사용
				// 누락 주의! 하나의 행 데이터를 List에 반복적으로 저장
				mList.add(member);
				// 후처리 : SELECT한 결과값 자바 영역이 List에다가 옮기는 작업
			}
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
		return mList;
	}
}
