package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.util.DbUtil;

public class ErDAOImpl implements ErDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private OrdersDetailsDAO orderDetailsDao = new OrdersDetailsDAOImpl();
	
	/**
	 * 교환하기
	 */
	@Override
	public int insertErExchange(ErVO erVo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ER.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			ps.setString(1, erVo.getId());
			ps.setInt(2, erVo.getOrdersCode());
			ps.setInt(3, erVo.getGoodsCode());
			ps.setInt(4, erVo.getErCategory());
			ps.setString(5, erVo.getErTitle());
			ps.setString(6, erVo.getErContent());
			ps.setInt(7, erVo.getErState());
			ps.setString(8, erVo.getErDi());
			ps.setInt(9, erVo.getOdersDetailsCode());
			
			// ER테이블 insert쿼리
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("교환 데이터 삽입 실패...");
			}
			
			// 주문상세테이블 상태 2로 변경
			result = orderDetailsDao.updateOrderDetailState(2, erVo.getOdersDetailsCode(), con); 
			if(result == 0) {
				con.rollback();
				throw new SQLException("주문상세 테이블 상태2로 변경 실패...");
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * (교환,환불) 내역전체조회
	 */
	@Override
	public List<ErVO> selectAllEr(MemberVO memberVo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ErVO> list = new ArrayList<ErVO>();
		String sql = sosoyaSql.getProperty("ER.SELECTALLER");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVo.getId());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ErVO erVo = new ErVO();
				erVo.setErCode(rs.getInt("ER_CODE"));
				erVo.setId(rs.getString("ID"));
				erVo.setOrdersCode(rs.getInt("ORDERS_CODE"));
				erVo.setGoodsCode(rs.getInt("GOODS_CODE"));
				erVo.setErCategory(rs.getInt("ER_CATEGORY"));
				erVo.setErTitle(rs.getString("ER_TITLE"));
				erVo.setErContent(rs.getString("ER_CONTENT"));
				erVo.setErRegdate(rs.getString("ER_REGDATE"));
				erVo.setErState(rs.getInt("ER_STATE"));
				erVo.setErDi(rs.getString("ER_DI"));
				erVo.setOdersDetailsCode(rs.getInt("ORDERS_DETAILS_CODE"));
				
				OrdersDetailsVO ordersDetailsVo = orderDetailsDao.selectOrdersDetailsCode(rs.getInt("ORDERS_DETAILS_CODE"));
				erVo.setOrdersDetailsVo(ordersDetailsVo);
				
				list.add(erVo);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}	
		return list;
	}

	/**
	 * 환불하기
	 */
	@Override
	public int insertErRefund(ErVO erVo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ER.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			ps.setString(1, erVo.getId());
			ps.setInt(2, erVo.getOrdersCode());
			ps.setInt(3, erVo.getGoodsCode());
			ps.setInt(4, erVo.getErCategory()); // 2
			ps.setString(5, erVo.getErTitle());
			ps.setString(6, erVo.getErContent());
			ps.setInt(7, erVo.getErState()); // 1
			ps.setString(8, erVo.getErDi());
			ps.setInt(9, erVo.getOdersDetailsCode());
			
			// ER테이블 insert쿼리
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("환불 데이터 삽입 실패...");
			}
			
			// 주문상세테이블 상태 3로 변경
			result = orderDetailsDao.updateOrderDetailState(3, erVo.getOdersDetailsCode(), con); 
			if(result == 0) {
				con.rollback();
				throw new SQLException("주문상세 테이블 상태3로 변경 실패...");
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/**
	 * (교환,환불)취소가능한 내역전체조회
	 */
	@Override
	public List<ErVO> selectAllCancelEr(MemberVO memberVo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ErVO> list = new ArrayList<ErVO>();
		String sql = sosoyaSql.getProperty("ER.SELECTALLCANCEL");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVo.getId());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ErVO erVo = new ErVO();
				erVo.setErCode(rs.getInt("ER_CODE"));
				erVo.setId(rs.getString("ID"));
				erVo.setOrdersCode(rs.getInt("ORDERS_CODE"));
				erVo.setGoodsCode(rs.getInt("GOODS_CODE"));
				erVo.setErCategory(rs.getInt("ER_CATEGORY"));
				erVo.setErTitle(rs.getString("ER_TITLE"));
				erVo.setErContent(rs.getString("ER_CONTENT"));
				erVo.setErRegdate(rs.getString("ER_REGDATE"));
				erVo.setErState(rs.getInt("ER_STATE"));
				erVo.setErDi(rs.getString("ER_DI"));
				erVo.setOdersDetailsCode(rs.getInt("ORDERS_DETAILS_CODE"));
				
				OrdersDetailsVO ordersDetailsVo = orderDetailsDao.selectOrdersDetailsCode(rs.getInt("ORDERS_DETAILS_CODE"));
				erVo.setOrdersDetailsVo(ordersDetailsVo);
				
				list.add(erVo);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}	
		return list;
	}
}