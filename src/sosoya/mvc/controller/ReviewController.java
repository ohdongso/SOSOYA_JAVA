package sosoya.mvc.controller;

import java.sql.SQLException;

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
			e.printStackTrace();
		}
	}
}