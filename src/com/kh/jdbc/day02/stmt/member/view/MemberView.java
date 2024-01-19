package com.kh.jdbc.day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.stmt.member.controller.MemberController;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {
	MemberController mController;

	public MemberView() {
		mController = new MemberController();
	}

	public void startProgram() {
		int choice = 0;
		Member member = null;
		String memberId = "";
		
		do {
			choice = this.printMainMenu();
			switch (choice) {
			case 1 :
				List<Member> mList = mController.printAll();
				this.PrintAllMembers(mList);
				break;
			case 2 :
				memberId = this.inputMemberId();
				member = mController.printOneById(memberId);
				this.printOneById(member);
				break;
			case 3 :
				member = this.inputMember();
				mController.registerMember(member);
				break;
			case 4 :
				memberId = this.inputMemberId();
				member = mController.printOneById(memberId);
				if (member != null) {
					this.modifyMember();
					member.setMemberId(memberId); // 누락 주의! WHERE 조건절에 사용되는 memberId 반드시 필요
					mController.modifyMember(member);
				} else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			case 5 :
				memberId = this.inputMemberId();
				mController.removeMember(memberId);
				break;
			}
		} while (choice != -1);
	}
	
	public int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 관리 프로그램 === ===");
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 회원 아이디로 조회");
		System.out.println("3. 회원 정보 등록");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("-1. 프로그램 종료");
		System.out.print("\n메뉴 입력 >> ");
		int choice = sc.nextInt();
		return choice;
	}
	
	public Member modifyMember() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n=== === 회원 정보 수정 === ===");
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		System.out.print("이메일 입력 : ");
		String email = sc.next();
		System.out.print("전화번호 입력 : ");
		String phone = sc.next();
		sc.nextLine(); // 개행 제거
		System.out.print("주소 입력 : ");
		String address = sc.nextLine();
		System.out.print("취미 입력 : ");
		String hobby = sc.next();

		Member mOne = new Member(memberPw, email, phone, address, hobby);
		return mOne;
	}

	public String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}

	public Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n=== === 회원 정보 입력 === ===");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M, F) : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호(- 없이) : ");
		String phone = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member(memberId, memberPw, memberName, gender, age, email, phone, address, hobby);
		return member;
	}

	public void printOneById(Member member) {
		System.out.println("\n=== === 회원 정보 출력 === ===");
		System.out.printf("이름 : %s\t 나이 : %d\t 아이디 : %s\t " + "성별 : %s\t 이메일 : %s\t" + "전화번호 : %s\t 주소 : %s\t "
				+ "취미 : %s\t 가입날짜 : %s\n"
				, member.getMemberName()
				, member.getAge()
				, member.getMemberId()
				, member.getGender()
				, member.getEmail()
				, member.getPhone()
				, member.getAddress()
				, member.getHobby()
				, member.getEnrollDate());
	}
	
	public void PrintAllMembers(List<Member> mList) {
		System.out.println("=== === 회원 정보 전체 출력 === ===");
		for (Member member : mList) {
			System.out.printf("이름 : %s\t 나이 : %d\t 아이디 : %s\t "
					+ "성별 : %s\t 이메일 : %s\t"
					+ "전화번호 : %s\t 주소 : %s\t "
					+ "취미 : %s\t 가입날짜 : %s\n"
					, member.getMemberName()
					, member.getAge()
					, member.getMemberId()
					, member.getGender()
					, member.getEmail()
					, member.getPhone()
					, member.getAddress()
					, member.getHobby()
					, member.getEnrollDate());
		}
	}
}
