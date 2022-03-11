package sosoya.mvc.controller;

import java.sql.SQLException;  
import java.util.List;

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.service.BasketService;
import sosoya.mvc.model.service.BasketServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class BasketController {
	private static BasketService basketService = new BasketServiceImpl();
	
	/**
	 * 장바구니등록
	 */
	public static void inputBasket(BasketVO basketVO) {
		try {
			basketService.insertBasket(basketVO);
			SuccessView.printMessage(basketVO.getId() + "님 장바구니에 상품이 담겼습니다.\n");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 전체 검색
	 * */
	public static void selectAllBasket(MemberVO memberVO) {
		try {
			List<BasketVO> list = basketService.selectAllBasket(memberVO);
			SuccessView.printSelectBasket(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니수정
	 * */
	public static void updateBasket(int updateBasket, int goodsCount) {
		try {
			basketService.updateBasket(updateBasket, goodsCount);
			SuccessView.printMessage("장바구니 상품 수정이 완료 되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니삭제
	 * */
	public static void deleteBasket(int basketCode) {
		try {
			basketService.deleteBasket(basketCode);
			SuccessView.printMessage("장바구니 상품 삭제가 완료 되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 선택삭제(일반)
	 * */
	public static void deletePartBasket(List<Integer> basketCodeList) {
		try {
			basketService.deletePartBasketNomal(basketCodeList);
			SuccessView.printMessage("장바구니 상품 선택삭제가 완료 되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니전체삭제(일반)
	 * */
	public static void deleteAllBasket(String memberId) {
		try {
			basketService.deleteAllBasketNomal(memberId);
			SuccessView.printMessage("장바구니 상품 전체삭제가 완료 되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}