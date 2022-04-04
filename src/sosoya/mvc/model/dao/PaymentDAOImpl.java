package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.util.DbUtil;
import sosoya.mvc.view.OrderDetailView;

public class PaymentDAOImpl implements PaymentDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private OrdersDAO ordersDao = new OrdersDAOImpl();
	private GoodsDAO goodsDao = new GoodsDAOImpl();
	
	/**
	 * 전체결제내역 조회
	 */
	@Override
	public List<PaymentVO> selectAllPayment(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PaymentVO> paymentVoList = null;
		String sql = sosoyaSql.getProperty("PAYMENT.SELECTALL");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVO.getId());
			rs = ps.executeQuery();
			
			paymentVoList = new ArrayList<>();
			while(rs.next()) {
				// ordersVO멤버변수에 데이터 저장.
				int paymentCode = rs.getInt("PAYMENT_CODE");
				int orderCode = rs.getInt("ORDERS_CODE");
				
				// 주문코드에 해당하는, ordesVO객체를 가져온다.
				OrdersVO ordersVO = ordersDao.selectOrdersByOrderCode(orderCode);
				
				// 주문코드에 해당하는, 주문상세리스트를 ordersVO객체에 저장한다.
				// 주문상세에 저장 될 때 상품 이름이 저장 되야 한다.
				List<OrdersDetailsVO> ordersDetailsVoList = ordersDao.selectOrdersDetailsVO(orderCode);
				
				// 주문상세 리스트가 담긴상황에서, 각 주문상세의 상품코드에 해당하는 상품이름을 list에 저장해줘야 한다.
				for(OrdersDetailsVO vo : ordersDetailsVoList) {		
					String goodsName = goodsDao.selectByNameGoodsOne(vo.getGoodsCode());
					vo.setGoodsName(goodsName);
				}
						
				// 상품이름을 저장한 주문상세객체 리스트를 다시 주문객체에 저장해준다.
				ordersVO.setOrdersDetailsList(ordersDetailsVoList);
				
				PaymentVO paymentVO = new PaymentVO(paymentCode, orderCode, rs.getString(3), rs.getString(4), memberVO, ordersVO);
				paymentVoList.add(paymentVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return paymentVoList;
	}
	
	/**
	 * 후기작성가능한 결제내역 전체보기
	 */
	@Override
	public List<PaymentVO> selectAllReviewPayment(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PaymentVO> paymentVoList = null;
		String sql = sosoyaSql.getProperty("PAYMENT.SELECTALL");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVO.getId());
			
			// memberId에 해당하는 전체 결제내역을 가져온다.
			rs = ps.executeQuery();
			
			paymentVoList = new ArrayList<>();
			while(rs.next()) {
				// ordersVO멤버변수에 데이터 저장.
				int paymentCode = rs.getInt("PAYMENT_CODE");
				int orderCode = rs.getInt("ORDERS_CODE");
				
				// 주문코드에 해당하는, ordersVO객체를 가져온다.
				OrdersVO ordersVO = ordersDao.selectOrdersByOrderCode(orderCode);
				
				// 주문코드에 해당하는, 주문상세리스트를 ordersVO객체에 저장한다.
				// ordersDetailsVoListTemp에는 주문중 주문상세에 해당하는 내역에 리뷰가 작성안된 주문상세를 가져온다.
				List<OrdersDetailsVO> ordersDetailsVoList = ordersDao.selectOrdersReviewDetailsVO(orderCode);
				
				// size가 0이면 주문에 해당하는 주문상세의 모든 상품이 리뷰가 작성 됐다는 뜻이다.
				if(ordersDetailsVoList.size() == 0 || ordersDetailsVoList == null) {
					continue;
				} else {
					// 주문상세 리스트가 담긴상황에서, 각 주문상세의 상품코드에 해당하는 상품이름을 list에 저장해줘야 한다.
					for(OrdersDetailsVO vo : ordersDetailsVoList) {		
						String goodsName = goodsDao.selectByNameGoodsOne(vo.getGoodsCode());
						vo.setGoodsName(goodsName);
					}
					
					// 상품이름을 저장한 주문상세객체 리스트를 다시 주문객체에 저장해준다.
					ordersVO.setOrdersDetailsList(ordersDetailsVoList);
					
					PaymentVO paymentVO = new PaymentVO(paymentCode, orderCode, rs.getString(3), rs.getString(4), memberVO, ordersVO);
					paymentVoList.add(paymentVO);
				}
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return paymentVoList;
	}
}