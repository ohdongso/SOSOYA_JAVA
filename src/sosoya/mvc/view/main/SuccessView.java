package sosoya.mvc.view.main;

import java.util.ArrayList;
import java.util.List; 

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.FaqVO;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.model.dto.ReviewVO;

public class SuccessView {
	/**
	 * 성공했을 때 메시지 출력
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 전체 리뷰내용 출력
	 * */
	public static void printAllReview(List<ReviewVO> reviewVoList) {
		int index = 0;
		
		for(ReviewVO reviewVO : reviewVoList){
			System.out.println(++index + "번째 리뷰" + " | 리뷰코드:" + reviewVO.getReviewCode() + " | 회원아이디:" + reviewVO.getId() + " | 상품이름:" + reviewVO.getGoodsVO().getGoodsName());
			System.out.println("  리뷰제목 ▶ " + reviewVO.getReviewTitle());
			System.out.println("  리뷰내용 ▶ " + reviewVO.getReviewContent());
			System.out.println("  등록날짜 ▶ " + reviewVO.getReviewRegdate());
			System.out.println("  상품평점 ▶ " + reviewVO.getReviewGrade() + "\n");
		}
	}
	
	/**
	 * 결제내역 출력
	 * */
	public static void printPayment(List<PaymentVO> paymentVoList) {
		for(PaymentVO paymentVo : paymentVoList) {
			System.out.println("회원아이디:" + paymentVo.getMemberVO().getId() + " | 결제코드:" + paymentVo.getPaymentCode() 
			+ " | 주문코드:" + paymentVo.getOrdersCode() + " | 결제날짜:" + paymentVo.getPaymentDate());
			
			List<OrdersVO> orderVoList = new ArrayList<>();
			orderVoList.add(paymentVo.getOrdersVO());
			printOrderByMemberId(orderVoList);
		}		
	}
		
	/**
	 * 주문 내역 출력
	 * */
	public static void printOrderByMemberId(List<OrdersVO> orderVoList) {
		int size = orderVoList.size();
		int index = 0;
		for(OrdersVO order : orderVoList) {
			// 주문객체 뷰 출력
			System.out.println("▶ 주문코드:" + order.getOrdersCode() +" [총금액:" + order.getOrdersTotalprice() 
			+ "원, 주소:" + order.getOrdersDi() + ", 주문날짜:" + order.getOrdersDate() + "]");
			   
			   // 주문상세목록을 출력하는데 코드는 전부 날리고 상품이름이 출력되게 해줘야 될거 같다.
			   for(OrdersDetailsVO ordersDetailsVO : order.getOrdersDetailsList()) {
				   
				   System.out.println("    ▶ " + ordersDetailsVO);
			   }
			   index++;
			   if(index < size)System.out.println();
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