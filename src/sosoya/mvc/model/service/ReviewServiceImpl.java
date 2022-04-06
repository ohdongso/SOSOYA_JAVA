package sosoya.mvc.model.service;

import java.sql.SQLException; 
import java.util.List;

import sosoya.mvc.model.dao.ReviewDAO;
import sosoya.mvc.model.dao.ReviewDAOImpl;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.ReviewVO;

public class ReviewServiceImpl implements ReviewService {
	private static ReviewDAO reviewDao = new ReviewDAOImpl();
	
	/**
	 * 리뷰등록하기
	 */
	@Override
	public void insertReview(ReviewVO reviewVO) throws SQLException {
		// ID와 주문상세에 해당하는 리뷰가 작성 돼 있는지 검사한다.
		String memberId = reviewVO.getId();
		int orderDetailsCode = reviewVO.getOrdersDetailsCode();
		ReviewVO reviewVoCheck = reviewDao.selectByIdOrderDetailCode(memberId, orderDetailsCode);
		if(reviewVoCheck != null) throw new SQLException("이미 작성된 리뷰입니다.");
		
		int result = reviewDao.insertReview(reviewVO);
		if(result == 0) throw new SQLException("리뷰가 등록되지 않았습니다.");
	}

	/**
	 * 내가 작성한 전체 리뷰조회
	 */
	@Override
	public List<ReviewVO> selectAllReview(MemberVO memberVO) throws SQLException {
		List<ReviewVO> reviewVoList = reviewDao.selectAllReview(memberVO);
		if(reviewVoList.size() == 0) throw new SQLException("작성된 리뷰가 없습니다.");
		
		return reviewVoList;
	}
	
	/**
	 * 상품이름으로 리뷰 검색하기
	 */
	@Override
	public List<ReviewVO> selectGoodsNameReview(MemberVO memberVO, String goodsName) throws SQLException {
		List<ReviewVO> reviewVoList = reviewDao.selectGoodsNameReview(memberVO, goodsName);
		if(reviewVoList.size() == 0) throw new SQLException("검색한 이름에 해당하는 상품이 없습니다.");
		
		return reviewVoList;
	}

	/**
	 * 리뷰수정
	 */
	@Override
	public void updateReview(ReviewVO reviewVO) throws SQLException {
		ReviewVO checkReviewVo = reviewDao.selectByReveiwCode(reviewVO.getReviewCode());
		if(checkReviewVo == null) throw new SQLException("리뷰코드에 해당하는 리뷰가 존재하지 않습니다.");
		
		int result = reviewDao.updateReview(reviewVO);
		if(result == 0) throw new SQLException("리뷰가 수정되지 않았습니다.");
	}
}