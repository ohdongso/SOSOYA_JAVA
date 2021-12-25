package sosoya.mvc.controller;

import java.sql.SQLException; 
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.ReviewVO;
import sosoya.mvc.model.service.ReviewService;
import sosoya.mvc.model.service.ReviewServiceImpl;
import sosoya.mvc.view.main.FailView;
import sosoya.mvc.view.main.SuccessView;

public class ReviewController {
	private static ReviewService reviewService = new ReviewServiceImpl();
	
	/**
	 * 리뷰작성하기
	 * */
	public static void insertReview(ReviewVO reviewVO) {
		try {
			reviewService.insertReview(reviewVO);
			SuccessView.printMessage("리뷰가 작성 되었습니다.");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 내가작성한 전체 리뷰보기
	 * */
	public static void selectAllReview(MemberVO memberVO) {
		try {
			List<ReviewVO> reviewVoList = reviewService.selectAllReview(memberVO);
			SuccessView.printAllReview(reviewVoList);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 상품이름으로 리뷰 검색하기
	 */
	public static void selectGoodsNameReview(MemberVO memberVO, String goodsName) {
		try {
			List<ReviewVO> reviewVoList = reviewService.selectGoodsNameReview(memberVO, goodsName);
			SuccessView.printAllReview(reviewVoList);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 리뷰수정하기
	 */
	public static void updateReview(ReviewVO reviewVO) {
		try {
			reviewService.updateReview(reviewVO);
			SuccessView.printMessage("리뷰수정이 완료 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}