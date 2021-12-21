package sosoya.mvc.model.dao;

import java.sql.Connection;  
import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.MemberVO;
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
	
	/**
	 * ID와 GOODS_CODE에 해당하는 리뷰가 작성 돼 있는지 검사한다.
	 * @param ReviewVO reviewVO
	 * @return ReviewVO
	 * */
	public ReviewVO selectByReview(ReviewVO reviewVO) throws SQLException;
	
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
	 * 리뷰코드로 리뷰검색
	 * @param int reviewCode
	 * @return ReviewVO reviewVO
	 * */
	public ReviewVO selectByReveiwCode(int reviewCode) throws SQLException;
	
	/**
	 * 리뷰수정
	 * @param ReviewVO reviewVO
	 * @return int result
	 * */
	public int updateReview(ReviewVO reviewVO) throws SQLException;
}