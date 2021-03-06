package sosoya.mvc.view;

import java.util.List;
import java.util.Scanner;

import sosoya.mvc.controller.PaymentController;
import sosoya.mvc.controller.ReviewController;
import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.model.dto.ReviewVO;
import sosoya.mvc.view.main.FailView;

public class ReviewView {
	private static Scanner sc = new Scanner(System.in);
	private static PaymentDAO paymentDao = new PaymentDAOImpl();
	
	// 결제한 상품 주문하는 기능
	public static void printReviewOrder(MemberVO memberVO) {
		System.out.println("\n=== 리뷰작성 ===");
		
		System.out.print("리뷰를 작성하시겠습니까??(Y 또는 N) : ");
		String flag = sc.nextLine();
		
		if(flag.toUpperCase().equals("Y")) {
			// 전체 결제내역을 보여줘야 한다.
			System.out.println("\n=== 후기작성 가능한 결제내역 ===");
			
			List<PaymentVO> list = null;
			try {
				list = paymentDao.selectAllReviewPayment(memberVO);
			} catch (Exception e) {
				FailView.errorMessage(e.getMessage()); 
			}
			
			int size = list.size();
			if(size == 0) {
				System.out.println("리뷰작성 가능한 결제내역이 존재하지 않습니다.");
				return;
			}
			
			PaymentController.selectAllReviewPayment(memberVO);
			System.out.println();
			
			// (제목, 내용, 평점) 3가지는 유효성 검사후 setter를 통해입력받는다.
			System.out.println("----- 리뷰정보입력 -----");
			
			System.out.print("리뷰를 작성할 상품코드를 입력해주세요: ");
			int goodsCode = Integer.parseInt(sc.nextLine());
			
			System.out.print("리뷰를 작성할 주문상세코드를 입력해주세요: ");
			int orderDetailCode = Integer.parseInt(sc.nextLine());
			
			System.out.print("리뷰 제목을 입력해주세요: ");
			String title = sc.nextLine();
			
			System.out.print("리뷰 내용을 입력해주세요: ");
			String content = sc.nextLine();
			
			int grade = 0;
			while(true) {
				System.out.print("리뷰 평점을 입력해주세요(1~5) : ");
				grade = Integer.parseInt(sc.nextLine());
				if(grade==1||grade==2||grade==3||grade==4||grade==5) {
					break;
				} else {
					System.out.println("\n1~5사이 숫자를 입력해주세요.");
					continue;
				}
			}
			
			// 최종확인 하는 View필요, 어떤 주문상세에 대한 리뷰를 작성하는지 학인 할수 있어야 한다.
			System.out.println("\n----- 리뷰내용 최종확인 -----");
			System.out.println("리뷰 제목 : " + title);
			System.out.println("리뷰 내용 : " + content);
			System.out.println("리뷰 평점 : " + grade + "점");
			System.out.println("상품코드 : " + goodsCode);
			System.out.println("주문상세코드 : " + orderDetailCode);
			
			// 최종승인 여부
			while(true) {
				System.out.print("\n최종입력한 리뷰내용이 맞으면 Y를 틀리면 N을 입력해주세요. : ");
				String input = sc.nextLine();
				if(input.toUpperCase().equals("Y")) {
					ReviewVO reviewVO = new ReviewVO(0, memberVO.getId(), goodsCode, orderDetailCode, title, content, grade, null);
					ReviewController.insertReview(reviewVO);
					break;
				} else if(input.toUpperCase().equals("N")) {
					System.out.println("리뷰작성이 취소 되었습니다.");
					break;
				} else {
					System.out.println("Y 또는 N을 입력해주세요.");
					continue;
				}
			}
		} else if(flag.toUpperCase().equals("N")) {
			System.out.println("리뷰작성이 취소 되었습니다.");
		} else {
			System.out.println("Y 또는 N을 입력해주세요.");
		}	
	}
	
	// 작성한 리뷰 보기
	public static void printReview(MemberVO memberVO) {
		System.out.println("\n=== 내가작성한 리뷰 전체보기 ===");
		ReviewController.selectAllReview(memberVO);
	}
	
	// 상품코드로리뷰검색
	public static void printGoodsNameReview(MemberVO memberVO) {
		System.out.println("\n=== 상품이름으로리뷰검색 ===");
		
		System.out.print("상품 이름을 입력해주세요: ");
		String goodsName = sc.nextLine();
		
		ReviewController.selectGoodsNameReview(memberVO, goodsName);
	}
	
	// 리뷰수정
	public static void printUpdateReview(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 리뷰수정 ===");
		
		System.out.print("수정할 리뷰코드를 입력해주세요: ");
		int reviewCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 리뷰제목를 입력해주세요: ");
		String reviewTitle = sc.nextLine();
		
		System.out.print("수정할 리뷰내용을 입력해주세요: ");
		String reviewContent = sc.nextLine();
		
		int reviewGrade = 0;
		while(true) {
			System.out.print("수정할 상품평점을 입력해주세요: ");
			reviewGrade = Integer.parseInt(sc.nextLine());
			
			if(reviewGrade==1||reviewGrade==2||reviewGrade==3||reviewGrade==4||reviewGrade==5) {
				break;
			} else {
				System.out.println("평점은 1~5사이 숫자를 입력해 주세요.");
			}
		}
		
		ReviewVO reviewVO = new ReviewVO(reviewCode, reviewTitle, reviewContent, reviewGrade,memberVO);
		
		ReviewController.updateReview(reviewVO);
	}
	
	// 리뷰삭제
	public static void printDeleteReview(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 리뷰삭제 ===");
		
		System.out.print("삭제할 리뷰코드를 입력해주세요: ");
		int reviewCode = Integer.parseInt(sc.nextLine());
		
		// ReviewController.updateReview(reviewVO);
	}
}