package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.ReviewVO;

public class ReviewView {
	private static Scanner sc = new Scanner(System.in);
	
	// 결제한 상품 주문하는 기능
	public static void printOrder(MemberVO memberVO) {
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
			ReviewVO reviewVO = new ReviewVO(0, memberVO.getId(), goodsCode, null, null, 0, null);
			
			
		} else if(flag.toUpperCase().equals("N")) {
			
		} else {
			System.out.println("Y 또는 N을 입력해주세요.");
		}	
	}
}