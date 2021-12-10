package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.util.DbUtil;

public class PaymentDAOImpl implements PaymentDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private OrdersDAO ordersDao = new OrdersDAOImpl();
	
	/**
	 * 전체결제내역 조회
	 */
	@Override
	public PaymentVO selectAllPayment(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PaymentVO paymentVO = null;
		String sql = sosoyaSql.getProperty("PAYMENT.SELECTALL");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVO.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				// ordersVO멤버변수에 데이터 저장.
				int paymentCode = rs.getInt(1);
				int orderCode = rs.getInt(2);
				
				// 결제코드에 해당하는, ordesVO객체를 가져온다.
				OrdersVO ordersVO = ordersDao.selectOrdersByOrderCode(orderCode);
				
				// 주문코드에 해당하는, 주문상세리스트를 ordersVO객체에 저장한다.
				List<OrdersDetailsVO> ordersDetailsVoList = ordersDao.selectOrdersDetailsVO(orderCode);
				ordersVO.setOrdersDetailsList(ordersDetailsVoList);
				
				paymentVO = new PaymentVO(paymentCode, orderCode, rs.getString(3), rs.getString(4), memberVO, ordersVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return paymentVO;
	}
}