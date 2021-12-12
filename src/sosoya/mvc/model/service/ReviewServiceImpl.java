package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dao.ReviewDAO;
import sosoya.mvc.model.dao.ReviewDAOImpl;
import sosoya.mvc.model.dto.ReviewVO;

public class ReviewServiceImpl implements ReviewService {
	private static ReviewDAO reviewDao = new ReviewDAOImpl();
	
	/**
	 * 리뷰등록하기
	 */
	@Override
	public void insertReview(ReviewVO reviewVO) throws SQLException {
		int result = reviewDao.insertReview(reviewVO);
		if(result == 0) throw new SQLException("리뷰가 등록되지 않았습니다.");
	}
}