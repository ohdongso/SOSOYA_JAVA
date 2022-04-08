package sosoya.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sosoya.mvc.controller.PaymentController;
import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.view.main.FailView;

public class ErView {
	private static Scanner sc = new Scanner(System.in);
	private static PaymentDAO paymentDao = new PaymentDAOImpl();
	
	public static void printErMenu(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 교환				                        ");
			System.out.println("  	 2. 환불				                        ");
		    System.out.println("  	 3. 뒤로가기          						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 교환가능한 결제목록
				printErAllPage(memberVO);
				
				// 교환
				printExchangePage(memberVO);
				break;
			case 2:
				// 환불가능한 결제목록
				printErAllPage(memberVO);
				
				
				// 환불
				printRefundPage(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		} // while문 끝.
	}
	
	// 교환
	public static void printExchangePage(MemberVO memberVO) {
		System.out.println("\n=== 상품교환 ===");

		List<PaymentVO> list = null;
		try {
			list = paymentDao.selectAllErPayment(memberVO);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			return;
		}
		
		int size = list.size();
		if(size == 0) {
			System.out.println("주문 내역이 존재하지 않습니다.");
			return;
		}

		while(true) {
			int count = 0;
			System.out.print("(교환,환불)할 결제내역 개수를 입력주세요 : ");
			count = Integer.parseInt(sc.nextLine());
			
			if(count > size) {
				System.out.println("(교환,환불)가능한 결제내역 총 개수가 " + size + "개 입니다. 1개~" + size +"개 사이 숫자를 입력해주세요.");
				return;
			} else if(count < 1) {
				System.out.println("1개 이상을 입력해주세요.");
				return;
			}
			// CATEGORY, STATE 동일한 기능을 하는 컬럼이다 수정필요하다.
			List<Integer> paymentCodeList = new ArrayList<>();
			for(int i = 1; i <= count; i++) {
				System.out.println("\n------------ " + i + "번째 교환할 상품정보를 입력해주세요. ------------");
				System.out.print("교환할 주문코드를 입력해주세요 : ");
				int orderCode = Integer.parseInt(sc.nextLine()); 
				
				System.out.print("교환할 상품코드를 입력해주세요 : ");
				int goodsCode = Integer.parseInt(sc.nextLine());
				
				System.out.print("교환할 주문상세코드를 입력해주세요 : ");
				int orderDetailCode = Integer.parseInt(sc.nextLine());
				
				int orderDetailState = 2;
				
				System.out.println("교환 내용 제목을 입력해주세요 : ");
				String title = sc.nextLine();
				
				System.out.print("교환 사유를 입력해주세요 : ");
				String content = sc.nextLine();
				
				// (기존 배송지랑 동일 선택가능)
				System.out.print("교환 배송지를 입력해주세요 : ");
				String di = sc.nextLine();
				
				

				
				
			} // for문 끝.
		} // while문 끝.
	}
	
	// 환불
	public static void printRefundPage(MemberVO memberVO) {
		
	}
	
	public static void printErAllPage(MemberVO memberVO) {
		System.out.println("\n=== (교환,환불)가능한 결제목록 ===");
		PaymentController.selectAllErPayment(memberVO);
	}
}