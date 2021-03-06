package sosoya.mvc.view.main;

import java.util.Scanner;

import sosoya.mvc.controller.ReviewController;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.view.BasketView;
import sosoya.mvc.view.ErView;
import sosoya.mvc.view.OrderView;
import sosoya.mvc.view.PaymentView;
import sosoya.mvc.view.ReviewView;

public class MyPageView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void printMyPage(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 장바구니						    ");
			System.out.println("  	 2. 주문내역(삭제하기, 뒤로가기)			");
			System.out.println("  	 3. 결제내역(리뷰작성)  					");
			System.out.println("  	 4. 작성한리뷰보기					    ");
			System.out.println("  	 5. 뒤로가기						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 장바구니		
				printBasket(memberVO);
				break;
			case 2:
				// 주문내역(삭제하기, 뒤로가기)
				OrderView.printSelectAllOrders(memberVO);
				break;
			case 3:
				// 결제내역전체 보기
				PaymentView.printPaymentList(memberVO);	
				
				// 1. 후기작성, 2. 결제내역삭제, 3. 뒤로가기
				printPaymentOption(memberVO);
				break;
			case 4:
				// 1,리뷰전체검색 2,상품이름으로검색 3,뒤로가기
				printReviewOption(memberVO);	
				break;
			case 5:
				// 뒤로가기
				return;
			default :
				// 1~5번 까지 숫자를 입력해주세요.
				System.out.println("1~5번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 1,상품코드로검색 2,리뷰수정 3,리뷰삭제
	public static void printReviewOption(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 리뷰전체검색				                ");
			System.out.println("  	 2. 상품이름으로리뷰검색				            ");
		    System.out.println("  	 3. 뒤로가기          						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 리뷰전체검색
				ReviewView.printReview(memberVO);
				break;
			case 2:
				// 상품이름으로리뷰검색
				ReviewView.printGoodsNameReview(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 장바구니
	public static void printBasket(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 장바구니상품(전체검색)					            ");
			System.out.println("  	 2. 장바구니상품(주문)					            ");
			System.out.println("  	 3. 장바구니상품(수정)							    ");
			System.out.println("  	 4. 장바구니상품(삭제)  					            ");
			System.out.println("  	 5. 뒤로가기   						            ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 장바구니상품(전체검색)
				BasketView.printSelectAllBasket(memberVO);
				break;
			case 2:
				// 장바구니상품(주문)
				// 1번 전체주문, 2번 선택주문
				printBasketOption(memberVO);
				break;
			case 3:
				// 장바구니상품(수정)
				BasketView.printUpdateBasket(memberVO);
				break;
			case 4:
				// 장바구니상품(삭제)
				BasketView.printDeleteBasket(memberVO);
				break;
			case 5:
				// 뒤로가기
				return;
			default :
				// 1~5번 까지 숫자를 입력해주세요.
				System.out.println("1~5번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 1번 전체주문, 2번 선택주문 
	public static void printBasketOption(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 전체주문					            ");
			System.out.println("  	 2. 선택주문					            ");
			System.out.println("  	 3. 뒤로가기   						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 전체주문
				OrderView.printBasketAllOrder(memberVO);
				break;
			case 2:
				// 선택주문
				OrderView.printBasketByOrder(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}
	
	// 1번 전체주문, 2번 선택주문 
	public static void printPaymentOption(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 리뷰작성					            ");
			System.out.println("  	 2. 결제내역삭제					            ");
			System.out.println("  	 3. 뒤로가기   						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 리뷰작성
				ReviewView.printReviewOrder(memberVO);
				break;
			case 2:
				// 결제내역삭제
				PaymentView.printPaymentDelete(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		}
	}	
}