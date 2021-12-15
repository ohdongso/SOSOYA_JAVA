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
		// ID와 GOODS_CODE에 해당하는 리뷰가 작성 돼 있는지 검사한다.
		ReviewVO reviewVoCheck = reviewDao.selectByReview(reviewVO);
		if(reviewVoCheck != null) throw new SQLException("이미 작성된 리뷰입니다.");
		
		int result = reviewDao.insertReview(reviewVO);
		if(result == 0) throw new SQLException("리뷰가 등록되지 않았습니다.");
	}
}