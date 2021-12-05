package sosoya.mvc.view;

import java.util.Scanner;

import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersVO;

public class PaymentView {
	private static Scanner sc = new Scanner(System.in);
	
	public static boolean printPayment(MemberVO memberVO, GoodsVO GoodsVO, OrdersVO ordersVO) {
		System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다." + " / 회원등급[" + memberVO.getGrade() + "] -----");
		System.out.println("=== 결제하기 ===");
		System.out.println("상품이름 : " + GoodsVO.getGoodsName());
		System.out.println("상품가격 : " + GoodsVO.getGoodsPrice());
		// System.out.println("상품개수 : " + ordersVO.getOrdersGoodscount());
		System.out.println("상품총금액 : " + ordersVO.getOrdersTotalprice());
		
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
					System.out.println("총결제 금액을 다시 한번 확인해주세요.");
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
}