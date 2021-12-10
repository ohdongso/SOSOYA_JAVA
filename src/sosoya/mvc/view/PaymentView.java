package sosoya.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sosoya.mvc.controller.PaymentController;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersVO;

public class PaymentView {
	private static Scanner sc = new Scanner(System.in);
	
	// 결제하기
	public static boolean printPayment(MemberVO memberVO, List<GoodsVO> goodsVoList, OrdersVO ordersVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 결제하기 ===");
		
		int index = -1;
		for(GoodsVO goodsVO : goodsVoList) {
			++index;
			System.out.println("--------------------------- " + (index+1) + "번째 장바구니 상품 ---------------------------");
			System.out.println("상품이름 : " + goodsVO.getGoodsName());
			System.out.println("상품가격 : " + goodsVO.getGoodsPrice());
			System.out.println("상품개수 : " + ordersVO.getOrdersDetailsList().get(index).getOrdersDetailsCount());
			System.out.println("상품총금액 : " + goodsVO.getGoodsPrice() * ordersVO.getOrdersDetailsList().get(index).getOrdersDetailsCount() + "원");
			System.out.println();
		}
		
		System.out.println("---------------------------결제 최종금액---------------------------");
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
				System.out.println("최종결제 금액 " + ordersVO.getOrdersTotalprice() + "원 을 다시한번 입력해주세요.");
				System.out.print("최종결제금액 입력 : ");
				pay = Integer.parseInt(sc.nextLine());
				
				if(!String.valueOf(pay).equals(String.valueOf(ordersVO.getOrdersTotalprice()))) {
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
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 결제내역 ===");
		
		PaymentController.selectAllPayment(memberVO);
	}
	
	// 결제내역 삭제
	public static void printPaymentDelete(MemberVO memberVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 결제내역 삭제 ===");
		
		System.out.print("삭제할 결제 내역 개수를 입력주세요: ");
		int count = Integer.parseInt(sc.nextLine());
		
		List<Integer> paymentCodeList = new ArrayList<>();
		for(int i = 1; i <= count; i++) {
			System.out.print("삭제할 결제코드를 입력해주세요: ");
			int paymentCode = Integer.parseInt(sc.nextLine());
			paymentCodeList.add(paymentCode);
		}
		
		// 후기를 등록하고 처리해야 한다.
	}
	
	
}