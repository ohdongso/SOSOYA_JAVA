package sosoya.mvc.view.main;

import java.util.ArrayList;  
import java.util.List;
import sosoya.mvc.model.dao.ReviewDAO;
import sosoya.mvc.model.dao.ReviewDAOImpl;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.ErVO;
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
	 * (교환,환불)취소가능한 내역전체조회
	 */
	public static void printSelectAllCancelEr(List<ErVO> list) {
		int size = list.size();
		int index = 0;
		
		for(ErVO erVo : list) {
			if(erVo.getErCategory() == 1) {
				System.out.println("---------------------- " + ++index + "번째 상품 (교환)내역 ----------------------");
				System.out.println("  카테고리 ▶ 교환");
				System.out.println("  교환제목 ▶ " + erVo.getErTitle());
				System.out.println("  교환내용 ▶ " + erVo.getErContent());
				System.out.println("  교환주소 ▶ " + erVo.getErDi());
				System.out.println("  교환코드 ▶ " + erVo.getErCode());
				System.out.println("  신청날짜 ▶ " + erVo.getErRegdate());
				System.out.println("  주문상세내역 ▶ " + erVo.getOrdersDetailsVo());
			} else if(erVo.getErCategory() == 2) {
				System.out.println("---------------------- " + ++index + "번째 상품 (환불)내역 ----------------------");
				System.out.println("  카테고리 ▶ 환불");
				System.out.println("  환불제목 ▶ " + erVo.getErTitle());
				System.out.println("  환불내용 ▶ " + erVo.getErContent());
				System.out.println("  환불코드 ▶ " + erVo.getErCode());
				System.out.println("  신청날짜 ▶ " + erVo.getErRegdate());
				System.out.println("  주문상세내역 ▶ " + erVo.getOrdersDetailsVo());
			} else {
				System.out.println("(교환,환불)코드 \"" + erVo.getErCode() + "\"에 해당하는 (교환,환불)카테고리가 잘못 입력 되었습니다.");
				System.out.println("잘못된 카테고리 값이 입력되었습니다. 관리자에게 문의해주세요.(010-2689-5806)");
				return;
			}
			
			if(index < size)System.out.println();
		} // for문 끝.
	}
	
	/**
	 * (교환,환불) 내역전체조회
	 * */
	public static void printSelectAllEr(List<ErVO> list) {
		int size = list.size();
		int index = 0;
		
		for(ErVO erVo : list){
			String erStateMessage = null;
			if(erVo.getErCategory() == 1) {
				System.out.println("---------------------- " + ++index + "번째 상품 (교환)내역 ----------------------");
				System.out.println("  카테고리 ▶ 교환");
				System.out.println("  교환제목 ▶ " + erVo.getErTitle());
				System.out.println("  교환내용 ▶ " + erVo.getErContent());
				System.out.println("  교환주소 ▶ " + erVo.getErDi());
				System.out.println("  교환코드 ▶ " + erVo.getErCode());
				System.out.println("  신청날짜 ▶ " + erVo.getErRegdate());
				System.out.println("  주문상세내역 ▶ " + erVo.getOrdersDetailsVo());
				
				
				// 1,(교환,환불 진행중,취소가능) 2,(교환,환불 진행중,취소불가능) 3,(교환,환불 완료) 4,삭제상태
				int state = erVo.getErState();
				switch (state) {
				case 1:
					erStateMessage = "교환 진행중 입니다. 관리자가 승인하지 않았기 때문에 취소가능합니다.";
					break;
				case 2:
					erStateMessage = "교환 진행중 입니다. 관리자가 승인했기 때문에 취소불가능합니다.";
					break;
				case 3:
					erStateMessage = "교환 완료 되었습니다. 사용자가 교환물품을 받고 관리자가 교환최종승인했습니다.";
					break;
				case 4:
					erStateMessage = "교환이 취소되었습니다.";
				}
				
				System.out.println("  교환진행상태 ▶ " + erStateMessage);
			} else if(erVo.getErCategory() == 2) {
				System.out.println("---------------------- " + ++index + "번째 상품 (환불)내역 ----------------------");
				System.out.println("  카테고리 ▶ 환불");
				System.out.println("  환불제목 ▶ " + erVo.getErTitle());
				System.out.println("  환불내용 ▶ " + erVo.getErContent());
				System.out.println("  환불코드 ▶ " + erVo.getErCode());
				System.out.println("  신청날짜 ▶ " + erVo.getErRegdate());
				System.out.println("  주문상세내역 ▶ " + erVo.getOrdersDetailsVo());
				
				// 1,(교환,환불 진행중,취소가능) 2,(교환,환불 진행중,취소불가능) 3,(교환,환불 완료) 4,삭제상태
				int state = erVo.getErState();
				switch (state) {
				case 1:
					erStateMessage = "환불 진행중 입니다. 관리자가 승인하지 않았기 때문에 취소가능합니다.";
					break;
				case 2:
					erStateMessage = "환불 진행중 입니다. 관리자가 승인했기 때문에 취소불가능합니다.";
					break;
				case 3:
					erStateMessage = "환불 완료 되었습니다. 관리자가 환불물품을 받고 관리자가 환불최종승인했습니다.";
					break;
				case 4:
					erStateMessage = "환불이 취소되었습니다.";
				}
				
				System.out.println("  환불진행상태 ▶ " + erStateMessage);		
			} else {
				System.out.println("(교환,환불)코드 \"" + erVo.getErCode() + "\"에 해당하는 (교환,환불)카테고리가 잘못 입력 되었습니다.");
				System.out.println("잘못된 카테고리 값이 입력되었습니다. 관리자에게 문의해주세요.(010-2689-5806)");
				return;
			}
			
			if(index < size)System.out.println();
		}
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
				   
				   int state = ordersDetailsVO.getOrdersDetailsState();				   		   
				   // 교환중
				   if(state == 2) {
					   System.out.println("    ▶ 교환중인 상품입니다.");   
				   // 환불중
				   } else if(state == 3) {
					   System.out.println("    ▶ 환불중인 상품입니다.");
				   // 관리자 최종승인
				   } else if(state == 4) {
					   System.out.println("    ▶ (교환,환불)이 완료된 상품입니다.");
				   }				  
			   }
			   index++;
			   if(index < size)System.out.println();
		} // for문 끝.
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
				   
				   // 작성된 리뷰일 경우 + 교환, 환불 중인 상품일 경우
				   // 아이디와 주문상세코드에 해당하는 리뷰가 존재하면 표시해줘야한다.
				   int state = ordersDetailsVO.getOrdersDetailsState();
				   ReviewVO reviewVo = reviewDao.selectByIdOrderDetailCode(memberId, orderDetailCode);
				   if(reviewVo != null) {
					   System.out.println("      ▶ 리뷰등록이 완료된 결제상품입니다.");
					   
					   // 교환중
					   if(state == 2) {
						   System.out.println("        ▶ 교환중인 상품입니다.");
					   
					   // 환불중
					   } else if(state == 3) {
						   System.out.println("        ▶ 환불중인 상품입니다.");
					   // 관리자최종승인
					   } else if(state == 4) {
						   System.out.println("        ▶ (교환,환불)이 완료된 상품입니다.");
					   }
				   // 작성되지 않을 리뷰일 경우 + 교환, 환불 중인 상품일 경우
				   } else {
					   // 교환중
					   if(state == 2) {
						   System.out.println("      ▶ 교환중인 상품입니다.");
					   // 환불중
					   } else if(state == 3) {
						   System.out.println("      ▶ 환불중인 상품입니다.");
					   } else if(state == 4) {
						   System.out.println("      ▶ (교환,환불)이 완료된 상품입니다.");
					   }
				   }		   
			   }
			   index++;
			   if(index < size)System.out.println();
		}
	}
	
	/**
	 * 결제내역 상세출력
	 * */
	public static void printPaymentReviewDetailView(List<OrdersVO> orderVoList) {
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