package sosoya.mvc.controller;

import java.sql.SQLException;  

import sosoya.mvc.exception.DuplicatedException;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.service.MemberService;
import sosoya.mvc.model.service.MemberServiceImpl;
import sosoya.mvc.view.MemberView;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.MenuView;
import sosoya.mvc.view.main.SuccessView;

public class MemberController {
	private static MemberService memberService = new MemberServiceImpl();
	
	/**
	 * 회원등록
	 * */
	public static void inputMember(MemberVO memberVO) {
		try {
			memberService.inputMember(memberVO);
			SuccessView.printMessage(memberVO.getId() + "님 회원가입을 축하드립니다.\n");
		} catch (DuplicatedException e) {
			FailView.errorMessage(e.getMessage());
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 아이디에 해당하는 회원검색
	 * */
	public static MemberVO selectByMember(String id) {
		MemberVO memberVO = null;
		
		try {
			memberVO = memberService.selectByMember(id);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return memberVO;
	}
	
	/**
	 * 로그인
	 * */
	public static void login(String id, String password) {
		try {
			MemberVO memberVO = memberService.login(id, password);
			MenuView.printUserMenu(memberVO);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원정보 수정
	 * */
	public static void updateMember(MemberVO memberVO) {
		try {
			memberService.updateMember(memberVO);
			SuccessView.printMessage("회원 수정이 완료되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원탈퇴
	 * */
	public static void memberStateUpdate(String id) {
		try {
			memberService.memberStateUpdate(id);
			SuccessView.printMessage("회원 탈퇴가 완료되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}