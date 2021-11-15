package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.model.dto.MemberVO;

public class MemberView {
	private static Scanner sc = new Scanner(System.in);
	
	// 로그인 후 메뉴
	public static void printUserMenu(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println(memberVO);
	}
}