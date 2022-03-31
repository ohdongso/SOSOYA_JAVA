package sosoya.mvc.view.main;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList; 
import java.util.List;

import sosoya.mvc.controller.ReviewController;
import sosoya.mvc.model.dao.ReviewDAO;
import sosoya.mvc.model.dao.ReviewDAOImpl;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.FaqVO;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.model.dto.ReviewVO;

public class SuccessView {
	private static ReviewDAO reviewDao = new ReviewDAOImpl();
	
	/**
	 * 성공했을 때 메시지 출력
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 리뷰개수 TOP3에 해당하는 상품출력
	 * */
	public static void printReviewTop3CountGoods(List<GoodsVO> goodsList) {
		for(GoodsVO goodsVo : goodsList) {
			System.out.println(goodsVo);
		}
	}
	
	/**
	 * 전체 리뷰내용 출력
	 * */
	public static void printAllReview(List<ReviewVO> reviewVoList) {
		int size = reviewVoList.size();
		int index = 0;
		for(ReviewVO reviewVO : reviewVoList){
			System.out.println(++index + "번째 리뷰" + " | 리뷰코드:" + reviewVO.getReviewCode() + " | 회원아이디:" + reviewVO.getId() + " | 상품이름:" + reviewVO.getGoodsVO().getGoodsName());
			System.out.println("  리뷰제목 ▶ " + reviewVO.getReviewTitle());
			System.out.println("  리뷰내용 ▶ " + reviewVO.getReviewContent());
			System.out.println("  등록날짜 ▶ " + reviewVO.getReviewRegdate());
			System.out.println("  상품평점 ▶ " + reviewVO.getReviewGrade() + "점");
			
			if(index < size)System.out.println();
		}
	}
	
	/**
	 * 결제내역 출력
	 * */
	public static void printPayment(List<PaymentVO> paymentVoList) {
		int size = paymentVoList.size();
		int index = 0;
		for(PaymentVO paymentVo : paymentVoList) {
			System.out.println("▶▶▶▶▶▶ 회원아이디:" + paymentVo.getMemberVO().getId() + " | 결제코드:" + paymentVo.getPaymentCode() 
			+ " | 주문코드:" + paymentVo.getOrdersCode() + " | 결제날짜:" + paymentVo.getPaymentDate() + " ◀◀◀◀◀◀");
			
			List<OrdersVO> orderVoList = new ArrayList<>();
			orderVoList.add(paymentVo.getOrdersVO());
			printPaymentDetailView(orderVoList);
			
			index++;
			if(index < size)System.out.println();
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
				   
				   System.out.println("  ▶ " + ordersDetailsVO);
			   }
			   index++;
			   if(index < size)System.out.println();
		}
	}
	
	/**
	 * 결제내역 상세출력
	 * */
	public static void printPaymentDetailView(List<OrdersVO> orderVoList) {
		int size = orderVoList.size();
		int index = 0;
		for(OrdersVO order : orderVoList) {
			// 주문객체 뷰 출력
			System.out.println("▶ 주문코드:" + order.getOrdersCode() +" [총금액:" + order.getOrdersTotalprice() 
			+ "원, 주소:" + order.getOrdersDi() + ", 주문날짜:" + order.getOrdersDate() + "]");
			   
			   // 주문상세목록을 출력하는데 코드는 전부 날리고 상품이름이 출력되게 해줘야 될거 같다.
			   for(OrdersDetailsVO ordersDetailsVO : order.getOrdersDetailsList()) {
				   String memberId = order.getId();
				   int goodsCode = ordersDetailsVO.getGoodsCode();
				   int orderDetailCode = ordersDetailsVO.getOrdersDetailsCode();
				   System.out.println("  ▶ " + ordersDetailsVO);
				   System.out.println("    ▶ 상품코드:\"" + goodsCode + "번\", "
				   		+ "주문상세코드:\"" + orderDetailCode + "번\"");
				   
				   // 작성된 리뷰일 경우
				   // 아이디와 주문상세코드에 해당하는 리뷰가 존재하면 표시해줘야한다.
				   ReviewVO reviewVo = reviewDao.selectByIdOrderDetailCode(memberId, orderDetailCode);
				   if(reviewVo != null) {
					   System.out.println("      ▶ 리뷰등록이 완료된 결제상품입니다.");
				   }
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