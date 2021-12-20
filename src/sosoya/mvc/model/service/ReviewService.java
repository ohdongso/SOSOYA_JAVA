package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.ReviewVO;

public interface ReviewService {
	/**
	 * 리뷰등록하기
	 * @param ReviewVO reviewVOㄴ
	 * @return
	 * */
	public void insertReview(ReviewVO reviewVO) throws SQLException;
	
	/**
	 * 내가 작성한 전체 리뷰조회
	 * @param MemberVO memberVO
	 * @return List<ReviewVO> reviewVoList
	 * */
	public List<ReviewVO> selectAllReview(MemberVO memberVO) throws SQLException;
	
	/**
	 * 상품이름으로 리뷰 검색하기
	 * @param MemberVO memberVO, String goodsName
	 * @return List<ReviewVO> reviewVoList
	 * */
	public List<ReviewVO> selectGoodsNameReview(MemberVO memberVO, String goodsName) throws SQLException;
	
	/**
	 * 리뷰수정
	 * @param ReviewVO reviewVO
	 * @return
	 * */
	public void updateReview(ReviewVO reviewVO) throws SQLException;
}