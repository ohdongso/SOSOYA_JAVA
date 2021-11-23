package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.util.DbUtil;

public class BasketDAOImpl implements BasketDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private GoodsDAO goodsDao = new GoodsDAOImpl();
	private MemberDAO memberDAO = new MemberDAOImpl();
	
	/**
	 * 장바구니등록
	 */
	@Override
	public int insertBasket(BasketVO basketVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("BASKET.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, basketVO.getId());
			ps.setInt(2, basketVO.getGoodsCode());
			
			// 총금액 구하기
			GoodsVO goodsVO = basketVO.getGoodsVO();
			MemberVO memberVO = memberDAO.selectByMember(basketVO.getId());
			float discountRate = 0.0f;
			
			switch(memberVO.getGrade()) {
				case "A":
					discountRate = 0.9f;
					break;
				case "B":
					discountRate = 0.95f;
					break;
				case "C":
					discountRate = 1.0f;
					break;
			}
			
			int total = (int)((goodsVO.getGoodsPrice() * basketVO.getBasketGoodsCount()) * discountRate);
			
			ps.setInt(3, total);
			ps.setInt(4, basketVO.getBasketGoodsCount());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}
	
	/**
	 * 장바구니전체검색
	 */
	@Override
	public List<BasketVO> selectAllBasket(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = sosoyaSql.getProperty("BASKET.SELECTALL");
		BasketVO basketVO = null;
		List<BasketVO> list = new ArrayList<>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVO.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				GoodsVO goodsVO = goodsDao.selectByGoods(rs.getInt("GOODS_CODE"));
				basketVO = new BasketVO(rs.getInt("BASKET_CODE"), rs.getString("ID"), rs.getInt("GOODS_CODE"),
						rs.getString("BASKET_REGDATE"), rs.getInt("BASKET_TOTALPRICE"), rs.getInt("BASKET_GOODSCOUNT"), goodsVO);

				list.add(basketVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	/**
	 * 장바구니 코드에 해당하는 상품 검색
	 */
	@Override
	public BasketVO selectByBasketCode(int basketCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = sosoyaSql.getProperty("BASKET.SELECTBYBASKET");
		BasketVO basketVO = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, basketCode);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {				
				basketVO = new BasketVO(rs.getInt("BASKET_CODE"), rs.getString("ID"), rs.getInt("GOODS_CODE"),
						rs.getString("BASKET_REGDATE"), rs.getInt("BASKET_TOTALPRICE"), rs.getInt("BASKET_GOODSCOUNT"));
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}	
		return basketVO;
	}

	/**
	 * 장바구니 수정
	 */
	@Override
	public int updateBasket(int basketCode, int goodsCount) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = sosoyaSql.getProperty("BASKET.UPDATE");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			// BASKET_TOTALPRICE
			BasketVO basketVO = this.selectByBasketCode(basketCode);
			basketVO.setGoodsVO(goodsDao.selectByGoods(basketVO.getGoodsCode())); // DB에는 Goods객체가 없기 때문에따로 넣어야 한다.
			MemberVO memberVO = memberDAO.selectByMember(basketVO.getId());
			float discountRate = 0.0f;
			
			switch(memberVO.getGrade()) {
				case "A":
					discountRate = 0.9f;
					break;
				case "B":
					discountRate = 0.95f;
					break;
				case "C":
					discountRate = 1.0f;
					break;
			}
			
			int totalPrice = (int)((basketVO.getGoodsVO().getGoodsPrice() * goodsCount) * discountRate);
			// BASKET_TOTALPRICE
			ps.setInt(1, totalPrice);
			
			// BASKET_GOODSOUNT
			ps.setInt(2, goodsCount);
			
			// BASKET_GOODSOUNT
			ps.setInt(3, basketCode);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 장바구니 삭제
	 */
	@Override
	public int deleteBasket(int basketCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = sosoyaSql.getProperty("BASKET.DELETE");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, basketCode);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
}