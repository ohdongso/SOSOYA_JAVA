package sosoya.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sosoya.mvc.controller.ErController;
import sosoya.mvc.controller.PaymentController;
import sosoya.mvc.model.dao.OrdersDAO;
import sosoya.mvc.model.dao.OrdersDAOImpl;
import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.view.main.FailView;

public class ErView {
	private static Scanner sc = new Scanner(System.in);
	private static PaymentDAO paymentDao = new PaymentDAOImpl();
	private static OrdersDAO orderDao = new OrdersDAOImpl();
	
	public static void printErMenu(MemberVO memberVO) {
		while(true) {
			System.out.println("\n----- " + memberVO.getId() + "님 방문을 환영합니다."   + " / 회원등급[" + memberVO.getGrade() + "] -----");
			System.out.println("┌──────────────┐");
			System.out.println("  	 1. 교환				                        ");
			System.out.println("  	 2. 환불				                        ");
		    System.out.println("  	 3. 뒤로가기          						    ");
			System.out.println("└──────────────┘");
			System.out.print("선택>>");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				// 교환가능한 결제목록
				printErAllPage(memberVO);
				
				// 교환
				printExchangePage(memberVO);
				break;
			case 2:
				// 환불가능한 결제목록
				printErAllPage(memberVO);
				
				
				// 환불
				printRefundPage(memberVO);
				break;
			case 3:
				// 뒤로가기
				return;
			default :
				// 1~3번 까지 숫자를 입력해주세요.
				System.out.println("1~3번 까지 숫자를 입력해주세요.");
				break;
			}
		} // while문 끝.
	}
	
	// 교환
	public static void printExchangePage(MemberVO memberVO) {
		System.out.println("\n=== 상품교환 ===");

		List<PaymentVO> list = null;
		try {
			list = paymentDao.selectAllErPayment(memberVO);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			return;
		}
		
		int size = list.size();
		if(size == 0) {
			System.out.println("교환가능한 결제내역이 존재하지 않습니다.");
			return;
		}

		while(true) {
			int count = 0;
			System.out.print("(교환,환불)할 결제내역 개수를 입력주세요 : ");
			count = Integer.parseInt(sc.nextLine());
			
			if(count > size) {
				System.out.println("(교환,환불)가능한 결제내역 총 개수가 " + size + "개 입니다. 1개~" + size +"개 사이 숫자를 입력해주세요.");
				return;
			} else if(count < 1) {
				System.out.println("1개 이상을 입력해주세요.");
				return;
			}
			
			List<Integer> paymentCodeList = new ArrayList<>();
			for(int i = 1; i <= count; i++) {
				System.out.println("\n------------ " + i + "번째 교환할 상품정보를 입력해주세요. ------------");
				System.out.print("교환할 주문코드를 입력해주세요 : ");
				int orderCode = Integer.parseInt(sc.nextLine()); 
								
				System.out.print("교환할 상품코드를 입력해주세요 : ");
				int goodsCode = Integer.parseInt(sc.nextLine());
				
				System.out.print("교환할 주문상세코드를 입력해주세요 : ");
				int orderDetailCode = Integer.parseInt(sc.nextLine());
				
				// 주문코드, 상품코드, 주문상세코드 유효성검사
				boolean orderCodeFlag = false;
				boolean goodsCodeFlag = false;
				boolean orderDetailCodeFlag = false;
				for(PaymentVO paymentVO : list) {
					orderCodeFlag = false;
					goodsCodeFlag = false;
					orderDetailCodeFlag = false;
					
					// 주문코드
					if(paymentVO.getOrdersCode() == orderCode) {
						orderCodeFlag = true;
					}
					
					// 상품코드, 주문상세코드
					List<OrdersDetailsVO> orderDetailList = paymentVO.getOrdersVO().getOrdersDetailsList();
					for(OrdersDetailsVO orderDetaillVo : orderDetailList) {
						if(orderDetaillVo.getGoodsCode() == goodsCode) {
							goodsCodeFlag = true;		
						}
						
						if(orderDetaillVo.getOrdersDetailsCode() == orderDetailCode) {
							orderDetailCodeFlag = true;
						}					
					}
					
					// 입력값 전체가 true인것이 존재한다면.(for문 종료)
					if(orderCodeFlag == true || goodsCodeFlag == true || orderDetailCodeFlag == true) {
						break;
					}
				} // for문 끝.
				
				if(orderCodeFlag == false || goodsCodeFlag == false || orderDetailCodeFlag == false) {
					System.out.println();
					if(orderCodeFlag == false) System.out.println("주문코드를 다시 한번 확인해주세요.");
					if(goodsCodeFlag == false) System.out.println("상품코드를 다시 한번 확인해주세요.");
					if(orderDetailCodeFlag == false)System.out.println("주문상세코드를 다시 한번 확인해주세요.");
					return;
				}
				
				// 카테고리 ==> 1.교환 2.환불 
				int erCategory = 1;
				// 상태 ==> 1,(교환,환불 진행중,취소가능) 2,(교환,환불 진행중,취소불가능) 3,(교환,환불 완료) 4,삭제상태
				int erState = 1;
				
				System.out.print("교환 내용 제목을 입력해주세요 : ");
				String title = sc.nextLine();
				
				System.out.print("교환 사유를 입력해주세요 : ");
				String content = sc.nextLine();
				
				String erDi = "";
				while(true) {
					// (기존 배송지랑 동일 선택가능)
					int diFlag = 0;
						
					System.out.print("기존배송지를 이용하시면 1을 새로운 배송지는 2를 입력해주세요 : ");
					diFlag = Integer.parseInt(sc.nextLine());
					if(diFlag == 1) {
						for(PaymentVO paymentVO : list) {
							if(paymentVO.getOrdersCode() == orderCode) {
								erDi = paymentVO.getOrdersVO().getOrdersDi();
								System.out.println("기존배송지 : " + erDi);
							}
						}								
						break;
					} else if(diFlag == 2) {
						System.out.print("교환 배송지를 입력해주세요 : ");
						erDi = sc.nextLine();
						break;
					} else {
						System.out.println("1또는 2를 입력해주세요.");
						continue;
					}
				} // while문 끝.
				
				ErVO erVo = new ErVO(0, memberVO.getId(), orderCode, orderDetailCode, 
						goodsCode, erCategory, title, content, erDi, null, erState);
				
				while(true) {
					System.out.println();
					System.out.print("신청한 교환내역은 수정이 불가능 합니다. 정말 교환하시겠습니까??(y또는n을 입력해주세요) : ");
					String input = sc.nextLine();
					if(input.toUpperCase().equals("Y")) {
						ErController.insertErVo(erVo);
						return;
					} else if(input.toUpperCase().equals("N")) {
						System.out.println("교환이 취소되었습니다.");
						return;
					} else {
						System.out.println("y 또는 n을 입력해주세요.");
						continue;
					}
				} // while문 끝.
			} // for문 끝.
		} // while문 끝.
	}
	
	// 환불
	public static void printRefundPage(MemberVO memberVO) {
		
	}
	
	public static void printErAllPage(MemberVO memberVO) {
		System.out.println("\n=== (교환,환불)가능한 결제목록 ===");
		PaymentController.selectAllErPayment(memberVO);
	}
}