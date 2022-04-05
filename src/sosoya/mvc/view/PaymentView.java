package sosoya.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sosoya.mvc.controller.PaymentController;
import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.view.main.FailView;

public class PaymentView {
	private static Scanner sc = new Scanner(System.in);
	private static PaymentDAO paymentDao = new PaymentDAOImpl();
	
	// 상품검색 후 바로주문해서 보이는 View
	public static boolean printPaymentDirectOrder(MemberVO memberVO, List<GoodsVO> goodsVoList, OrdersVO ordersVO) {
		System.out.println("\n=== 결제하기 ===");
		int index = -1;
		for(GoodsVO goodsVO : goodsVoList) {
			++index;
			System.out.println("--------------------------- " + (index+1) + "번째 상품 ---------------------------");
			System.out.println("상품이름 : " + goodsVO.getGoodsName());
			System.out.println("상품가격 : " + goodsVO.getGoodsPrice() + "원");
			System.out.println("상품개수 : " + ordersVO.getOrdersDetailsList().get(index).getOrdersDetailsCount() + "개");
			System.out.println("상품총금액 : " + goodsVO.getGoodsPrice() * ordersVO.getOrdersDetailsList().get(index).getOrdersDetailsCount() + "원");
			System.out.println();
		}
		
		System.out.println("---------------------------결제 최종금액---------------------------");
		// 결제시 회원 멤버십 적용되기 전, 후 가격을 보여줘야 한다.
		int originalPrice = 0;	
		int reducedPrice = 0;
		int reducedTotalPrice = 0; 
		
		reducedTotalPrice = ordersVO.getOrdersTotalprice();
		originalPrice = ordersVO.getOriginalPrice();
		reducedPrice = originalPrice - reducedTotalPrice;
		
		System.out.println("정가 : " + originalPrice + "원, 할인가격 : " + reducedPrice + "원");
		System.out.println("최종결제 금액 : " + ordersVO.getOrdersTotalprice() + "원");
		
		boolean flag = false;
		while(true) {
			String result = "";
			System.out.print("상품을 결제하시겠습니까??(Y/N) : ");
			result = sc.nextLine();
			
			if(result.toUpperCase().equals("N")) {
				break;
			} else if(result.toUpperCase().equals("Y")){
				int pay = 0;
				
				System.out.println("\n최종결제 금액 " + reducedTotalPrice + "원 을 다시한번 입력해주세요.");
				System.out.print("최종결제금액 입력 : ");
				pay = Integer.parseInt(sc.nextLine());
				
				if(!(pay == ordersVO.getOrdersTotalprice())) {
					System.out.println("총결제 금액을 다시 한번 확인해주세요. 결제 처음으로 돌아갑니다.");
					continue;
				}
				
				flag = true;
				break;
			} else {
				System.out.println("Y 또는 N을 입력해주세요.");
				continue;
			}		
		} // while문 끝.
		return flag;
	}
	
	// 결제하기(장바구니)
	public static boolean printPayment(MemberVO memberVO, List<GoodsVO> goodsVoList, OrdersVO ordersVO) {
		System.out.println("\n=== 결제하기 ===");
		
		int index = -1;
		for(GoodsVO goodsVO : goodsVoList) {
			++index;
			System.out.println("--------------------------- " + (index+1) + "번째 장바구니 상품 ---------------------------");
			System.out.println("상품이름 : " + goodsVO.getGoodsName());
			System.out.println("상품가격 : " + goodsVO.getGoodsPrice() + "원");
			System.out.println("상품개수 : " + ordersVO.getOrdersDetailsList().get(index).getOrdersDetailsCount() + "개");
			System.out.println("상품총금액 : " + goodsVO.getGoodsPrice() * ordersVO.getOrdersDetailsList().get(index).getOrdersDetailsCount() + "원");
			System.out.println();
		}
		
		System.out.println("---------------------------결제 최종금액---------------------------");
		// 결제시 회원 멤버십 적용되기 전, 후 가격을 보여줘야 한다.
		int originalPrice = 0;	
		int reducedPrice = 0;
		int reducedTotalPrice = 0; 
		
		reducedTotalPrice = ordersVO.getOrdersTotalprice();
		originalPrice = ordersVO.getOriginalPrice();
		reducedPrice = originalPrice - reducedTotalPrice;
		
		System.out.println("정가 : " + originalPrice + "원, 할인가격 : " + reducedPrice + "원");
		System.out.println("최종결제 금액 : " + ordersVO.getOrdersTotalprice() + "원");
		
		boolean flag = false;
		while(true) {
			String result = "";
			System.out.print("상품을 결제하시겠습니까??(Y/N) : ");
			result = sc.nextLine();
			
			if(result.toUpperCase().equals("N")) {
				return flag;
			} else if(result.toUpperCase().equals("Y")){
				int pay = 0;
		
				System.out.println("\n최종결제 금액 " + reducedTotalPrice + "원 을 다시한번 입력해주세요.");
				System.out.print("최종결제금액 입력 : ");
				pay = Integer.parseInt(sc.nextLine());
				
				if(pay != ordersVO.getOrdersTotalprice()) {
					System.out.println("총결제 금액을 다시 한번 확인해주세요. 결제 처음으로 돌아갑니다.");
					continue;
				}
				
				flag = true;
			} else {
				System.out.println("Y 또는 N을 입력해주세요.");
				continue;
			}
			return flag;
		}	
	}
	
	// 결제내역전체 보기
	public static void printPaymentList(MemberVO memberVO) {
		System.out.println("\n=== 결제내역 ===");
		PaymentController.selectAllPayment(memberVO);
	}
	
	// 결제내역 삭제
	public static void printPaymentDelete(MemberVO memberVO) {
		System.out.println("\n=== 결제내역 삭제 ===");
		PaymentController.selectAllPayment(memberVO);
		System.out.println();
		
		// 결제내역 개수를 초과하거나 0개 이하를 입력하면 다시 입력받는다.
		List<PaymentVO> list = null;
		try {
			list = paymentDao.selectAllPayment(memberVO);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			return;
		}
		
		int size = list.size();
		if(size == 0) {
			System.out.println("결제 내역이 존재하지 않습니다.");
			return;
		}
		
		int count = 0;

		System.out.print("삭제할 결제 내역 개수를 입력주세요 : ");
		count = Integer.parseInt(sc.nextLine());
		
		if(count > size) {
			System.out.println("결제 내역 총 개수가 " + size + "개 입니다. 1개~" + size +"개 사이 숫자를 입력해주세요.");
			return;
		} else if(count < 1) {
			System.out.println("1개 이상을 입력해주세요.");
			return;
		}

		List<Integer> paymentCodeList = new ArrayList<>();
		for(int i = 1; i <= count; i++) {
			System.out.print("삭제할 결제코드를 입력해주세요: ");
			int paymentCode = Integer.parseInt(sc.nextLine());
			paymentCodeList.add(paymentCode);
		}
		
		// 결제내역을 삭제하면 리뷰를 작성하지 못한다는것을 명시해야 한다.
		while(true) {
			System.out.print("\"결제내역을 삭제하면 리뷰작성을 하지못합니다. 정말 삭제하시겠습니까??(y 또는 n을 입력해주세요)\" : ");
			String input = sc.nextLine();
			
			if(input.toUpperCase().equals("Y")) {
				// Controller에 전달
				PaymentController.printPaymentDelete(paymentCodeList);
				return;
			} else if(input.toUpperCase().equals("N")) {
				System.out.println("결제내역 삭제가 취소되었습니다.");
				return;
			} else {
				System.out.println("y 또는 n을 입력해주세요.\n");
				continue;
			}
		}
	}
}