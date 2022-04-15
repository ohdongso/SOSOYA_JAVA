package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.util.DbUtil;

public class ErDAOImpl implements ErDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private OrdersDetailsDAO orderDetailsDao = new OrdersDetailsDAOImpl();
	
	/**
	 * 교환하기
	 */
	@Override
	public int insertErVo(ErVO erVo) throws SQLException {
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
				throw new SQLException("교환, 환불 테이블에 데이터 삽입 실패...");
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
	
	
	
}
