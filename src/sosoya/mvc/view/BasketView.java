package sosoya.mvc.view;

import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;   

import sosoya.mvc.controller.BasketController;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.service.BasketService;
import sosoya.mvc.model.service.BasketServiceImpl;
import sosoya.mvc.view.main.FailView;

public class BasketView {
	private static Scanner sc = new Scanner(System.in);
	private static BasketService basketService = new BasketServiceImpl();
	
	// 장바구니 전체검색
	public static void printSelectAllBasket(MemberVO memberVO) {
		System.out.println("\n=== 장바구니상품(전체검색) ===");
		BasketController.selectAllBasket(memberVO);
	}
	
	// 장바구니 수정
	public static void printUpdateBasket(MemberVO memberVO) {
		System.out.println("\n=== 장바구니상품(수정) ===");
		BasketController.selectAllBasket(memberVO);
		System.out.println();
		
		System.out.print("수정할 (장바구니코드)를 입력해주세요: ");
		int basketCode = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 (상품개수)를 입력해주세요: ");
		int goodsCount = Integer.parseInt(sc.nextLine());
		
		BasketController.updateBasket(basketCode, goodsCount);
	}
	
	// 장바구니 삭제
	public static void printDeleteBasket(MemberVO memberVO) {
		while(true) {
			System.out.println("\n=== 장바구니상품(삭제) ===");
			
			int input = 0;
			System.out.print("( 1,전체삭제  2,선택삭제  3,뒤로가기 ) 1~3사이 숫자를 입력해주세요 : ");
			input = Integer.parseInt(sc.nextLine());
			System.out.println();
			
			List<BasketVO> list = new ArrayList<>();
			int basketCount = 0;
			
			// 전체삭제, 선택삭제 일 때 장바구니에 목록이 없을 경우 뒤로가기.
			if(input == 1 || input == 2) {	
				try {
					list = basketService.selectAllBasket(memberVO);
				} catch (Exception e) {
					FailView.errorMessage(e.getMessage());
					System.out.println("뒤로이동 합니다.");
					break;
				}		
			}
			
			// 장바구니에 담긴 상품이 있으면, 총 개수를 담아준다.
			basketCount = list.size();
			
			// 장바구니 전체삭제
			if(input == 1) {
				System.out.println("=== 장바구니상품(삭제전 확인) ===");
				BasketController.selectAllBasket(memberVO);
				System.out.println();
				
				System.out.print("정말 전체삭제하시겠습니까??(Y/N) : ");
				String answer = sc.nextLine();
				if(answer.toUpperCase().equals("Y")) {
					BasketController.deleteAllBasket(memberVO.getId());
				} else if(answer.toUpperCase().equals("N")) {
					System.out.println("장바구니 전체상품삭제가 취소 되었습니다.");
				} else {
					System.out.println("Y또는N을 입력해주세요.");
				}
				
			// 장바구니 선택삭제
			} else if(input == 2){
				System.out.println("=== 장바구니상품(삭제전 확인) ===");
				BasketController.selectAllBasket(memberVO);
				System.out.println();
				
				System.out.print("몇개의 장바구니를 삭제 하시겠습니까??(총" + basketCount + "개) : ");
				int count = Integer.parseInt(sc.nextLine());
				
				List<Integer> basketCodeList = new ArrayList<Integer>();
				if(count >= 1 && count <= basketCount) {
					for(int i = 1; i <= count; i++) {
						System.out.print("삭제할 장바구니 코드 : ");
						basketCodeList.add(Integer.parseInt(sc.nextLine()));
					}
					
					System.out.print("정말 삭제하시겠습니까??(Y/N) : ");
					String answer = sc.nextLine();
					if(answer.toUpperCase().equals("Y")) {
						BasketController.deletePartBasket(basketCodeList);
					} else if(answer.toUpperCase().equals("N")) {
						System.out.println("장바구니 상품삭제가 취소 되었습니다.");
					} else {
						System.out.println("Y또는N을 입력해주세요.");
					}
				} else {
					System.out.println("1~" + basketCount + "사이의 숫자를 입력해주세요.");
				}
			// 뒤로가기
			} else if(input == 3) {
				break;
			
			// 1~3사이 숫자를 입력하지 않았을 경우
			} else {
				System.out.println("1~3사이 숫자를 입력해주세요.");
				continue;
			}
		}
	}
	
	// 장바구니 담기 메뉴
	public static void printInsertBasket(MemberVO memberVO) {
		System.out.println("\n=== 상품 장바구니에 담기 ===");
		
		// 1개 이상 장바구니 상품담기 수정
		System.out.print("장바구니에 담을 상품 개수를 입력해주세요 : ");
		int count = Integer.parseInt(sc.nextLine());
		
		List<BasketVO> basketVoList = new ArrayList<>(); 
		for(int i = 1; i <= count; i++) {
			System.out.println("\n--------- " + i + "번째 장바구니 상품 ---------");
			
			System.out.print("상품코드 : ");
			int goodsCode = Integer.parseInt(sc.nextLine());
			
			System.out.print("구매개수 : ");
			int goodsCount = Integer.parseInt(sc.nextLine());
			
			BasketVO basketVO = new BasketVO(memberVO.getId(), goodsCode, goodsCount);
			basketVoList.add(basketVO);
		}
		
		BasketController.inputBasket(basketVoList);
	}
}