package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import sosoya.mvc.controller.PaymentController;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.util.DbUtil;
import sosoya.mvc.view.PaymentView;

public class OrdersDAOImpl implements OrdersDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private GoodsDAO goodsDAO = new GoodsDAOImpl();
	private MemberDAO memberDAO = new MemberDAOImpl();
	
	/**
	 * 상품주문하기
	 * @param OrdersVO ordersVO
	 */
	@Override
	public int insertOrders(OrdersVO ordersVO) throws SQLException {
		/*
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ORDERS.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, ordersVO.getId());
			ps.setInt(2, ordersVO.getGoodsCode().get(0));
			ps.setString(3, ordersVO.getOrdersDi());
			
			GoodsVO goodsVO =  goodsDAO.selectByGoods(ordersVO.getGoodsCode().get(0));
			MemberVO memberVO = memberDAO.selectByMember(ordersVO.getId());
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
			
			// ordersTotalprice
			int ordersTotalprice = 0;
			ordersTotalprice = (int)((goodsVO.getGoodsPrice() * ordersVO.getOrdersGoodscount()) * discountRate);
			ordersVO.setOrdersTotalprice(ordersTotalprice);
			
			ps.setInt(4, ordersTotalprice);
			ps.setInt(5, ordersVO.getOrdersGoodscount());
			
			// 주문하기 전에 결제
			if(PaymentView.printPayment(memberVO, goodsVO, ordersVO)){
				// PAYMENT테이블에 데이터 삽입
				PaymentVO paymentVO = new PaymentVO(0, ordersVO.getOrdersCode(), memberVO.getId(), goodsVO.getGoodsCode(), null);
				new PaymentController().insertPayment(paymentVO);
				
			} else {
				throw new SQLException("결제가 취소 되었습니다.");
			}
			
			// ORDERS테이블에 데이터 삽입
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
		*/
		return 0;
	}
}