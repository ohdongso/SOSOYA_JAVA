package sosoya.mvc.model.dao;

import java.sql.SQLException;

import sosoya.mvc.model.dto.ReviewVO;

public interface ReviewDAO {
	/**
	 * 상품주문하기
	 * @param ReviewVO reviewVO
	 * @return int result
	 * */
	public int insertReview(ReviewVO reviewVO) throws SQLException;
}