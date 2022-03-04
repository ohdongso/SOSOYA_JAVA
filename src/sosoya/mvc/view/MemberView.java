package sosoya.mvc.view;

import java.util.Scanner; 

import sosoya.mvc.controller.MemberController;
import sosoya.mvc.model.dao.MemberDAOImpl;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.MenuView;

public class MemberView {
	private static Scanner sc = new Scanner(System.in);

	public static void printMember(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 회원정보보기						");
			System.out.println("  	 2. 회원정보수정						");
			System.out.println("  	 3. 회원탈퇴						    ");
			System.out.println("  	 4. 뒤로가기						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				// 회원정보보기
				printMemberInfo(memberVO);
				break;
			case 2:
				// 회원정보수정
				printMemberUpdate(memberVO);
				memberVO = MemberController.selectByMember(memberVO.getId());
				break;
			case 3:
				// 회원탈퇴
				printStateUpdate(memberVO);
				MenuView.menu();
				break;
			case 4:
				// 뒤로가기
				try {
					memberVO = new MemberDAOImpl().selectByMember(memberVO.getId());
				} catch (Exception e) {
					FailView.errorMessage(e.getMessage());
				}
				return;
			default:
				System.out.println("1에서 4사이의 숫자를 입력해주세요.");
			}
		}
	}
	
	// 회원정보탈퇴
	public static void printStateUpdate(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 회원탈퇴 비밀번호 확인 ===");
		System.out.print("비밀번호: ");
		String passwordCheck = sc.nextLine();
		
		String password = memberVO.getPassword();
		if(!password.equals(passwordCheck)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		System.out.println("\n=== 회원탈퇴 ===");
		System.out.print("정말로 회원탈퇴 하시겠습니까??(Y/N) : ");
		String answer = sc.nextLine();
		if(answer.toUpperCase().equals("Y")) {
			MemberController.memberStateUpdate(memberVO.getId());
		} else if(answer.toUpperCase().equals("N")) {
			System.out.println("회원 탈퇴를 취소하셨습니다.");
		} else {
			System.out.println("Y 또는 N을 입력해주세요.");
			return;
		}
	}
	
	// 회원정보보기
	public static void printMemberInfo(MemberVO memberVO) {
		System.out.println("\n=== 회원정보 ===");
		System.out.println("아이디 : " + memberVO.getId());
		System.out.println("구매횟수 : " + memberVO.getPurchasescount() + "회");
		System.out.println("회원등급 : " + memberVO.getGrade());
		System.out.println("이름 : " + memberVO.getName());
		System.out.println("이메일 : " + memberVO.getEmail());
		System.out.println("핸드폰 : " + memberVO.getPhone());
		System.out.println("주소 : " + memberVO.getAddress());
		System.out.println("가입날짜 : " + memberVO.getRegdate());
	}
	
	// 회원정보수정
	public static void printMemberUpdate(MemberVO memberVO) {
		System.out.println("\n=== 비밀번호확인 ===");
		System.out.print("비밀번호: ");
		String passwordCheck = sc.nextLine();
		
		String password = memberVO.getPassword();
		if(!password.equals(passwordCheck)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		// 회원정보 출력
		System.out.println("\n=== 회원정보 ===");
		System.out.println("아이디 : " + memberVO.getId());
		System.out.println("구매횟수 : " + memberVO.getPurchasescount() + "회");
		System.out.println("회원등급 : " + memberVO.getGrade());
		System.out.println("이름 : " + memberVO.getName());
		System.out.println("이메일 : " + memberVO.getEmail());
		System.out.println("핸드폰 : " + memberVO.getPhone());
		System.out.println("주소 : " + memberVO.getAddress());
		System.out.println("가입날짜 : " + memberVO.getRegdate());
		
		System.out.println("\n=== 회원정보수정 ===");
		
		while(true) {
			System.out.print("비밀번호를 변경 하시겠습니까?(Y/N) : ");
			String input = sc.nextLine().toUpperCase();
			
			if(input.equals("Y")) {
				password = printPasswordCheck();
				while(password.equals("~!@#$%^&*()_+q")) {
					password = printPasswordCheck();
				}
				break; 
			} else if(!input.equals("N")) {
				System.out.println("Y 또는 N을 입력해주세요.");
				continue;
			}
			
			// N이 입력 됐을 경우.
			password = memberVO.getPassword();
			break;
		}
		
		String phone = "";
		while(true) {
			System.out.print("핸드폰 번호를 변경 하시겠습니까?(Y/N) : ");
			String input = sc.nextLine().toUpperCase();
			
			if(input.equals("Y")) {
				System.out.print("핸드폰 : ");
				phone = sc.nextLine();
				break;
			} else if(!input.equals("N")) {
				System.out.println("Y 또는 N을 입력해주세요.");
				continue;
			}
			
			// N이 입력 됐을 경우.
			phone = memberVO.getPhone();
			break;
		}
		
		String email = "";
		while(true) {
			System.out.print("이메일을 변경 하시겠습니까?(Y/N) : ");
			String input = sc.nextLine().toUpperCase();
			
			if(input.equals("Y")) {
				System.out.print("이메일 : ");
				email = sc.nextLine();
				break;
			} else if(!input.equals("N")) {
				System.out.println("Y 또는 N을 입력해주세요.");
				continue;
			}
			
			// N이 입력 됐을 경우.
			email = memberVO.getEmail();
			break;
		}
		
		String address = "";
		while(true) {
			System.out.print("주소를 변경 하시겠습니까?(Y/N) : ");
			String input = sc.nextLine().toUpperCase();
			
			if(input.equals("Y")) {
				System.out.print("주소 : ");
				address = sc.nextLine();
				break;
			} else if(!input.equals("N")) {
				System.out.println("Y 또는 N을 입력해주세요.");
				continue;
			}
			
			address = memberVO.getAddress();
			break;
		}		
		
		MemberVO memberUpdateVO = new MemberVO(memberVO.getId(), password, email, phone, address);
		MemberController.updateMember(memberUpdateVO);
	}
	
	// 비밀번호 확인
	public static String printPasswordCheck() {
		System.out.print("새 비밀번호 : ");
		String password = sc.nextLine();
		
		System.out.print("새 비밀번호 확인 : ");
		String passwordCheck = sc.nextLine();
		
		if(password.equals(passwordCheck)) {
			return password;
		} else {
			System.out.println("비밀번호를 다시 한번 확인해주세요.");
			return "~!@#$%^&*()_+q";
		}
	} 
}