package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDAO;

	public MemberController() {
		mDAO = new MemberDAO();
	}

	public Member printOneById(String memberId) {
		Member member = mDAO.selectById(memberId);
		return member;
	}

	public List<Member> printAll() {
		List<Member> mList = mDAO.selectAll();
		return mList;
	}

	public void registerMember(Member member) {
		mDAO.insertMember(member);
	}

	public void removeMember(String memberId) {
		Member member = mDAO.selectById(memberId);
		mDAO.deleteMember(memberId);
	}

	public void modifyMember(Member member) {
		mDAO.updateMember(member);
	}
}
