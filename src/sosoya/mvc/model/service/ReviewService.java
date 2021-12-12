package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dto.ReviewVO;

public interface ReviewService {
	/**
	 * 리뷰등록하기
	 * @param ReviewVO reviewVOㄴ
	 * @return
	 * */
	public void insertReview(ReviewVO reviewVO) throws SQLException;
}