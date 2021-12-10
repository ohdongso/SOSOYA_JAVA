package sosoya.mvc.view.main;

import java.util.ArrayList;
import java.util.List; 

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.FaqVO;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;

public class SuccessView {
	/**
	 * 성공했을 때 메시지 출력
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 결제내역 출력
	 * */
	public static void printPayment(PaymentVO paymentVO) {
		System.out.println("회원아이디:" + paymentVO.getMemberVO().getId() + " | 결제코드:" + paymentVO.getPaymentCode() 
		+ " | 주문코드:" + paymentVO.getOrdersCode() + " | 결제날짜:" + paymentVO.getPaymentDate());

		List<OrdersVO> orderVoList = new ArrayList<>();
		orderVoList.add(paymentVO.getOrdersVO());
		printOrderByMemberId(orderVoList);
	}
		
	/**
	 * 주문 내역 출력
	 * */
	public static void printOrderByMemberId(List<OrdersVO> orderVoList) {
		for(OrdersVO order : orderVoList) {
			System.out.println("주문코드:" + order.getOrdersCode() +" | 총금액:" + order.getOrdersTotalprice() 
			+ " | 주소:" + order.getOrdersDi() + " | 주문날짜:" + order.getOrdersDate() );
			   for(OrdersDetailsVO ordersDetailsVO : order.getOrdersDetailsList()) {
				   System.out.println("  ▶ " + ordersDetailsVO);
			   }
			   System.out.println();
		}
	}
	
	/**
	 * FAQ전체 목록 출력
	 * */
	public static void printSelectFaq(List<FaqVO> list) {
		for(FaqVO vo : list) {
			System.out.println(vo.getFaqCategory() + ", " + vo.getFaqTitle() + " : " + vo.getFaqContent());
		}
	}
	
	/**
	 * Basket전체 목록 출력
	 * */
	public static void printSelectBasket(List<BasketVO> list) {
		for(BasketVO vo : list) {
			System.out.println(vo);
		}
	}
	
	/**
	 * Goods전체 목록 출력
	 * */
	public static void printSelectGoods(List<GoodsVO>  list) {
		for(GoodsVO vo : list) {
			System.out.println(vo);
		}
	}
}