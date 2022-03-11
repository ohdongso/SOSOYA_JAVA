package sosoya.mvc.view.main;

import java.util.Scanner;         
import sosoya.mvc.controller.MemberController;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.view.FaqView;
import sosoya.mvc.view.GoodsView;
import sosoya.mvc.view.MemberView;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);

	public static void menu() {
		while(true) {
			printMenu();
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 로그인
				loginMenu();
				break;
			case 2:
				// 회원가입
				printInsertMember();
				break;
			case 3:
				// 종료
				System.out.println("\n=== SoSoYa Shopping Mall에 다음에도 방문해주세요.^^ ===");
				System.exit(0);
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	} // menu() 끝.
	
	// 메인 메뉴
	public static void printMenu() {
		System.out.println("\n=== SoSoYa Shopping Mall ===");
		System.out.println("┌──────────────┐");
		System.out.println("  	 1. 로그인								");
		System.out.println("  	 2. 회원가입								");
		System.out.println("  	 3. 종료								    ");
		System.out.println("└──────────────┘");
		System.out.print("선택>>");
	}
	
	// 로그인 후 메뉴
	public static void printUserMenu(MemberVO memberVO) {
		while(true) {
			memberVO = MemberController.selectByMember(memberVO.getId());
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 회원메뉴						    ");
			System.out.println("  	 2. 상품검색							");
			System.out.println("  	 3. 마이페이지  					    ");
			System.out.println("  	 4. (교환,환불)  					    ");
			System.out.println("  	 5. 자주묻는질문(FAQ)					");
			System.out.println("  	 6. 로그아웃						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 회원메뉴
				MemberView.printMember(memberVO);
				break;
			case 2:
				// 상품검색
				GoodsView.printGoods(memberVO);
				break;
			case 3:
				// 마이페이지
				MyPageView.printMyPage(memberVO);
				break;
			case 4:
				// (교환,환불)
				break;
			case 5:
				// 자주묻는 질문 FAQ
				FaqView.printFaq(memberVO);
				break;
			case 6:
				// 로그아웃
				System.out.println("\n=== SoSoYa Shopping Mall을 이용해 주셔서 감사합니다.^^ ===");
				System.out.println("=== 로그아웃 완료. ===");
				return;
			default :
				// 1~6번 까지 숫자를 입력해주세요.
				System.out.println("1~6번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 로그인
	public static void loginMenu() {
		System.out.println("\n=== 로그인 ===");
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		
		System.out.print("패스워드 : ");
		String password = sc.nextLine();
		
		MemberController.login(id, password);
	}
	
	// 회원가입
	public static void printInsertMember() {
		System.out.println("\n=== 회원가입 ===");
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		
		String password = printPasswordCheck();
		while(password.equals("~!@#$%^&*()_+q")) {
			password = printPasswordCheck();
		}
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		System.out.print("핸드폰 : ");
		String phone = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
	
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		MemberVO memberVO = new MemberVO(id, password, name, 0, "C", 1, email, phone, address, null);
		MemberController.inputMember(memberVO);
	}
	
	// 비밀번호 확인
	public static String printPasswordCheck() {
		System.out.print("비밀번호 : ");
		String password = sc.nextLine();
		
		System.out.print("비밀번호 확인 : ");
		String passwordCheck = sc.nextLine();
		
		if(password.equals(passwordCheck)) {
			return password;
		} else {
			System.out.println("비밀번호를 다시 한번 확인해주세요.");
			return "~!@#$%^&*()_+q";
		}
	}
}