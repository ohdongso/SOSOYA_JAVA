package sosoya.mvc.controller;

import java.sql.SQLException; 

import sosoya.mvc.exception.DuplicatedException;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.service.MemberService;
import sosoya.mvc.model.service.MemberServiceImpl;
import sosoya.mvc.view.FailView;
import sosoya.mvc.view.MemberView;
import sosoya.mvc.view.SuccessView;

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
	 * 로그인
	 * */
	public static void login(String id, String password) {
		try {
			MemberVO memberVO = memberService.login(id, password);
			MemberView.printUserMenu(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	
	
}