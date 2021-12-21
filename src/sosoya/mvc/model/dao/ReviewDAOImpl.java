package sosoya.mvc.model.dao;

import java.sql.Connection;    
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.ReviewVO;
import sosoya.mvc.util.DbUtil;

public class ReviewDAOImpl implements ReviewDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private GoodsDAO goodsDao = new GoodsDAOImpl();
	private MemberDAO memberDao = new MemberDAOImpl();
	
	/**
	 * 리뷰등록하기
	 */
	@Override
	public int insertReview(ReviewVO reviewVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("REVIEW.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, reviewVO.getId());
			ps.setInt(2, reviewVO.getGoodsCode());
			ps.setString(3, reviewVO.getReviewTitle());
			ps.setString(4, reviewVO.getReviewContent());
			ps.setInt(5, reviewVO.getReviewGrade());
			
			// REVIEW테이블에 데이터를 추가한다.
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("review테이블에 데이터 삽입 실패...");
			} else {
				// goods테이블에 리뷰개수 업데이트
				result = goodsDao.updateReviewCount(reviewVO.getGoodsCode(), con);
				if(result == 0) {
					con.rollback();
					throw new SQLException("review테이블의 리뷰개수가 증가되지 않았습니다.");
				}
				
				// 현재 상품에 평점평균을 가져온다.
				GoodsVO goodsVO = goodsDao.selectGoodsAvg(reviewVO.getGoodsCode(), con);
				
				if(goodsVO == null) {
					con.rollback();
					throw new SQLException("goods테이블의 평점평균을 가져오지 못했습니다.");
				} else {
					// insert된 review개수가 저장된다.
					int reviewCount = selectReviewCount(reviewVO.getGoodsCode(), con);
					
					// 현재상품의 평점평균 * 
					float total = 0.0f;
					float goodsAvg = 0.0f;
					if(reviewCount == 1) {
						goodsAvg = reviewVO.getReviewGrade();
					} else {
						// 업데이트 전 상품 평점평균을 가져온다.
						System.out.println(goodsVO.getGoodsGradeAvg());
						total = goodsVO.getGoodsGradeAvg() * (reviewCount - 1);
						
						goodsAvg = (total + reviewVO.getReviewGrade()) / reviewCount;
						
						goodsAvg = Math.round(goodsAvg*10)/10.0f;
					}
					
					goodsDao.updateGoodsAvg(reviewVO.getGoodsCode(), goodsAvg, con);
				}
			}	
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * ID와 GOODS_CODE에 해당하는 리뷰가 작성 돼 있는지 검사한다.
	 * */
	@Override
	public ReviewVO selectByReview(ReviewVO reviewVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = sosoyaSql.getProperty("REVIEW.SELECTBYREVIEW");
		ReviewVO reviewVoCheck = null;
		
		try {
			con = DbUtil.getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, reviewVO.getId());
			ps.setInt(2, reviewVO.getGoodsCode());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				reviewVoCheck = new ReviewVO();
				reviewVoCheck.setReviewCode(rs.getInt(1));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return reviewVoCheck;
	}

	/**
	 * GOODS_CODE에 해당하는 리뷰 개수 구하기
	 */
	@Override
	public int selectReviewCount(int goodsCode, Connection con) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		String sql = sosoyaSql.getProperty("REVIEW.SELECTREVIEWCOUNT");
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, goodsCode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return count;
	}
	
	/**
	 * 내가 작성한 전체 리뷰조회
	 */
	@Override
	public List<ReviewVO> selectAllReview(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ReviewVO> reviewVoList = new ArrayList<ReviewVO>();
		String sql = sosoyaSql.getProperty("REVEIW.SELECTALLREVIEW");
		
		try {
			con = DbUtil.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVO.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewVO reviewVO = new ReviewVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getString(7));
				
				// 의존성 주입
				GoodsVO goodsVO = goodsDao.selectByGoods(reviewVO.getGoodsCode());
				reviewVO.setGoodsVO(goodsVO);
				
				reviewVO.setMemberVO(memberVO);
				
				reviewVoList.add(reviewVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}		
		return reviewVoList;
	}
	
	/**
	 * 상품이름으로 리뷰 검색하기
	 */
	@Override
	public List<ReviewVO> selectGoodsNameReview(MemberVO memberVO, String goodsName) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ReviewVO> reviewVoList = new ArrayList<ReviewVO>();
		String sql = sosoyaSql.getProperty("REVIEW.SELECTGOODSNAMEREVIEW");
		
		try {
			con = DbUtil.getConnection();
			
			ps = con.prepareStatement(sql);			
			ps.setString(1, memberVO.getId());
			ps.setString(2, "%" + goodsName + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewVO reviewVO = new ReviewVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getString(7));
				
				// 의존성 주입
				GoodsVO goodsVO = goodsDao.selectByGoods(reviewVO.getGoodsCode());
				reviewVO.setGoodsVO(goodsVO);
				
				reviewVO.setMemberVO(memberVO);
				
				reviewVoList.add(reviewVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}	
		return reviewVoList;
	}

	/**
	 * 리뷰수정
	 */
	@Override
	public int updateReview(ReviewVO reviewVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = sosoyaSql.getProperty("REVIEW.UPDATEREVIEW");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, reviewVO.getReviewTitle());
			ps.setString(2, reviewVO.getReviewContent());
			ps.setInt(3, reviewVO.getReviewGrade());
			ps.setInt(4, reviewVO.getReviewCode());
			
			// REVIEW테이블에 데이터를 수정한다.
			result = ps.executeUpdate();
			
			if(result == 0) {
				con.rollback();
				throw new SQLException("review테이블에 데이터 수정 실패...");
			} else {				
				// update를 하면 commit을 하기전에 변경된 데이터가 검색되는지 확인.
				// update구문을 실행 됐지만, commit이 실행되지 않았기 때문에 수정전 데이터가 출력된다.
				// System.out.println(this.selectByReveiwCode(reviewVO.getReviewCode()));
				// 즉 commit을 하기전에는 기존데이터가 검색된다.
				
				// goods테이블의 상품에 대한 평점평균을 변경
				// ==> 여기서 부터 하면 된다.
				result = goodsDao.updateReviewCount(reviewVO.getGoodsCode(), con);
				
				if(result == 0) {
					con.rollback();
					throw new SQLException("review테이블의 리뷰개수가 증가되지 않았습니다.");
				}
				
				// 현재 상품에 평점평균을 가져온다.
				GoodsVO goodsVO = goodsDao.selectGoodsAvg(reviewVO.getGoodsCode(), con);
				
				if(goodsVO == null) {
					con.rollback();
					throw new SQLException("goods테이블의 평점평균을 가져오지 못했습니다.");
				} else {
					// insert된 review개수가 저장된다.
					int reviewCount = selectReviewCount(reviewVO.getGoodsCode(), con);
					
					// 현재상품의 평점평균 * 
					float total = 0.0f;
					float goodsAvg = 0.0f;
					if(reviewCount == 1) {
						goodsAvg = reviewVO.getReviewGrade();
					} else {
						// 업데이트 전 상품 평점평균을 가져온다.
						System.out.println(goodsVO.getGoodsGradeAvg());
						total = goodsVO.getGoodsGradeAvg() * (reviewCount - 1);
						
						goodsAvg = (total + reviewVO.getReviewGrade()) / reviewCount;
						
						goodsAvg = Math.round(goodsAvg*10)/10.0f;
					}
					
					goodsDao.updateGoodsAvg(reviewVO.getGoodsCode(), goodsAvg, con);
				}
			}	
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * 리뷰코드로 리뷰검색
	 */
	@Override
	public ReviewVO selectByReveiwCode(int reviewCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewVO reviewVO = null;
		String sql = sosoyaSql.getProperty("REVIEW.SELECTBYREVIEWCODE");
		
		try {
			con = DbUtil.getConnection();
			
			ps = con.prepareStatement(sql);			
			ps.setInt(1, reviewCode);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				reviewVO = new ReviewVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getString(7));
				
				// 의존성 주입
				GoodsVO goodsVO = goodsDao.selectByGoods(reviewVO.getGoodsCode());
				reviewVO.setGoodsVO(goodsVO);
				
				MemberVO memberVO = memberDao.selectByMember(reviewVO.getId());
				reviewVO.setMemberVO(memberVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}	
		return reviewVO;
	}
}