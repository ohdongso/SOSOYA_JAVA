package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import sosoya.mvc.model.dto.ReviewVO;

public interface ReviewDAO {
	/**
	 * 리뷰등록하기
	 * @param ReviewVO reviewVO
	 * @return int result
	 * */
	public int insertReview(ReviewVO reviewVO) throws SQLException;
	
	
	/**
	 * GOODS_CODE에 해당하는 리뷰 개수 구하기
	 * @param int goodsCode, Connection con
	 * @return int count
	 * */
	public int selectReviewCount(int goodsCode, Connection con) throws SQLException;
}