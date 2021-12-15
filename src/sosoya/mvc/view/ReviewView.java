package sosoya.mvc.view;

import java.util.Scanner; 

import sosoya.mvc.controller.ReviewController;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.ReviewVO;

public class ReviewView {
	private static Scanner sc = new Scanner(System.in);
	
	// 결제한 상품 주문하는 기능
	public static void printReviewOrder(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 리뷰작성 ===");
		
		System.out.print("리뷰를 작성하시겠습니까??(Y 또는 N)");
		String flag = sc.nextLine();
		
		if(flag.toUpperCase().equals("Y")) {
			System.out.print("리뷰를 작성할 주문코드를 입력해주세요: ");
			int orderCode = Integer.parseInt(sc.nextLine());
			
			System.out.print("리뷰를 작성할 주문상세코드를 입력해주세요: ");
			int orderDetailCode = Integer.parseInt(sc.nextLine());
			
			System.out.print("리뷰를 작성할 상품코드를 입력해주세요: ");
			int goodsCode = Integer.parseInt(sc.nextLine());
			
			// (제목, 내용, 평점) 3가지는 유효성 검사후 setter를 통해입력받는다.
			System.out.println("주문코드: " + orderCode + ", 주문상세코드: " + orderDetailCode + ", 상품코드: " + goodsCode);
			System.out.println("----- 리뷰내용 -----");
			
			System.out.print("리뷰 제목을 입력해주세요: ");
			String title = sc.nextLine();
			
			System.out.print("리뷰 내용을 입력해주세요: ");
			String content = sc.nextLine();
			
			int grade = 0;
			while(true) {
				System.out.print("리뷰 평점을 입력해주세요(1~5): ");
				grade = Integer.parseInt(sc.nextLine());
				if(grade==1||grade==2||grade==3||grade==4||grade==5) {
					break;
				} else {
					continue;
				}
			}
			
			ReviewVO reviewVO = new ReviewVO(0, memberVO.getId(), goodsCode, title, content, grade, null);
			
			ReviewController.insertReview(reviewVO);
		} else if(flag.toUpperCase().equals("N")) {
			System.out.println("리뷰작성이 취소 되었습니다.");
			return;
		} else {
			System.out.println("Y 또는 N을 입력해주세요.");
		}	
	}
	
	// 작성한 리뷰 보기
	public static void printReview(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 내가작성한 리뷰 보기 ===");
		
		
	}
	
	
}