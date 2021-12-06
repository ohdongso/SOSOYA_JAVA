package sosoya.mvc.model.dao;

import java.sql.Connection;    
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;
import sosoya.mvc.model.dto.OrdersVO;
import sosoya.mvc.model.dto.PaymentVO;
import sosoya.mvc.util.DbUtil;
import sosoya.mvc.view.PaymentView;

public class OrdersDAOImpl implements OrdersDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	private GoodsDAO goodsDao = new GoodsDAOImpl();
	private MemberDAO memberDao = new MemberDAOImpl();
	private BasketDAO basketDao = new BasketDAOImpl();
	
	/**
	 * 상품주문하기
	 */
	@Override
	public int insertOrders(OrdersVO ordersVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ORDERS.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			// id, ordersDi, ordersTotalprice ==> 3개의 컬럼을 구해줘야한다.
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, ordersVO.getId());
			ps.setString(2, ordersVO.getOrdersDi());
			
			// 주문 전체금액
			// 주문 상세의 전체금액
			ps.setInt(3, getTotalPrice(ordersVO));
			
			// orders테이블에 데이터를 추가한다.
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("orders테이블에 데이터 삽입 실패...");
			} else {
				// 주문테이블에 데이터가 들어가면, 주문상세 테이블에도 데이터가 담긴다.
				// 주문된 정보를 담고있는 order객체를 매개변수로 전달한다.
				// connection객체를 들고 가야한다.	
				int[] re = orderDetailsInsert(con, ordersVO);
				for(int i : re) {
					// Statement.SUCCESS_NO_INFO는 SQL문이 성공적으로 실행 됐지만, 갱신 갯수를 알수 없을 경우 -2값을 가진다.
					// 여기서 배치를 통해, 정상적으로 수행된 구문들은 -2를 반환해서 이와 다를 경우 예외를 던진다.
					if(i != Statement.SUCCESS_NO_INFO) {
						con.rollback();
						throw new SQLException("orders_details테이블에 데이터 삽입 실패...");
					}
				}
				
				// 주문수량만큼 재고량 감소하기
				// order객체의 주문 상세 내역리스트를 넘긴다.
				re = decrementStock(con, ordersVO.getOrdersDetailsList());
				for(int i : re) {
					if(i != Statement.SUCCESS_NO_INFO) {
						con.rollback();
						throw new SQLException("goods테이블에 재고량 감소 실패...");
					}
				}
				
				// 결제
				MemberVO memberVO = memberDao.selectByMember(ordersVO.getId());
				List<GoodsVO> goodsVoList = new ArrayList<>();
				for(OrdersDetailsVO ordersDetailsVO : ordersVO.getOrdersDetailsList()) {
					goodsVoList.add(goodsDao.selectByGoods(ordersDetailsVO.getGoodsCode()));
				}
				
				// 총결제 금액 구하기.
				int totalPrice = this.getTotalPrice(ordersVO);
				ordersVO.setOrdersTotalprice(totalPrice);
					
				if(PaymentView.printPayment(memberVO, goodsVoList, ordersVO)){
					// PAYMENT테이블에 데이터 삽입
					PaymentVO paymentVO = new PaymentVO(0, 0, memberVO.getId(), null);
					result = this.insertPayment(con, paymentVO);
					
					// Statement.SUCCESS_NO_INFO는 SQL문이 성공적으로 실행 됐지만, 갱신 갯수를 알수 없을 경우 -2값을 가진다.
					// 여기서 배치를 통해, 정상적으로 수행된 구문들은 -2를 반환해서 이와 다를 경우 예외를 던진다.
					if(result != 1) {
						con.rollback();
						throw new SQLException("payment테이블에 데이터 삽입 실패...");
					}					
				} else {
					con.rollback();
					throw new SQLException("PayMentView에서 N을 입력했습니다.....");
				}
				
				// 회원 구매 횟수 증가
				result = this.memberPurchasecountUpdate(con, memberVO.getId()); 
				if(result != 1) {
					con.rollback();
					throw new SQLException("member테이블에서 회원구매 횟수가 수정 되지 않았습니다.....");
				}		
			} // else문 끝.
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * 장바구니상품전체주문
	 */
	@Override
	public int insertBasketAllOrder(OrdersVO ordersVO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ORDERS.INSERT");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
			
			// id, ordersDi, ordersTotalprice ==> 3개의 컬럼을 구해줘야한다.
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, ordersVO.getId());
			ps.setString(2, ordersVO.getOrdersDi());
			
			// 주문 전체금액
			ps.setInt(3, getTotalPrice(ordersVO));
			
			// orders테이블에 데이터를 추가한다.
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("orders테이블에 데이터 삽입 실패...");
			} else {
			// 주문테이블에 데이터가 들어가면, 주문상세 테이블에도 데이터가 담긴다.
			// 주문된 정보를 담고있는 order객체를 매개변수로 전달한다.
			// connection객체를 들고 가야한다.	
			int[] re = orderDetailsInsert(con, ordersVO);
			for(int i : re) {
				// Statement.SUCCESS_NO_INFO는 SQL문이 성공적으로 실행 됐지만, 갱신 갯수를 알수 없을 경우 -2값을 가진다.
				// 여기서 배치를 통해, 정상적으로 수행된 구문들은 -2를 반환해서 이와 다를 경우 예외를 던진다.
				if(i != Statement.SUCCESS_NO_INFO) {
					con.rollback();
					throw new SQLException("orders_details테이블에 데이터 삽입 실패...");
				}
			}
	
			// 주문수량만큼 재고량 감소하기
			// order객체의 주문 상세 내역리스트를 넘긴다.
			re = decrementStock(con, ordersVO.getOrdersDetailsList());
			for(int i : re) {
				if(i != Statement.SUCCESS_NO_INFO) {
					con.rollback();
					throw new SQLException("goods테이블에 재고량 감소 실패...");
				}
			}

			// 결제, 장바구니에 담긴상품을 종합하여, payment테이블에 담아야 한다.
			MemberVO memberVO = memberDao.selectByMember(ordersVO.getId());
			List<GoodsVO> goodsVoList = new ArrayList<>();
			System.out.println("확인2");
			for(OrdersDetailsVO ordersDetailsVO : ordersVO.getOrdersDetailsList()) {
				goodsVoList.add(goodsDao.selectByGoods(ordersDetailsVO.getGoodsCode()));
			}
			
			// 총결제 금액 구하기.
			int totalPrice = this.getTotalPrice(ordersVO);
			ordersVO.setOrdersTotalprice(totalPrice);
			
			if(PaymentView.printPayment(memberVO, goodsVoList, ordersVO)){
				// PAYMENT테이블에 데이터 삽입
				PaymentVO paymentVO = new PaymentVO(0, 0, memberVO.getId(), null);
				result = this.insertPayment(con, paymentVO);
				
				// Statement.SUCCESS_NO_INFO는 SQL문이 성공적으로 실행 됐지만, 갱신 갯수를 알수 없을 경우 -2값을 가진다.
				// 여기서 배치를 통해, 정상적으로 수행된 구문들은 -2를 반환해서 이와 다를 경우 예외를 던진다.
				if(result != 1) {
					con.rollback();
					throw new SQLException("payment테이블에 데이터 삽입 실패...");
				}					
			} else {
				con.rollback();
				throw new SQLException("PayMentView에서 N을 입력했습니다.....");
			}
			
			// 장바구니 데이터 삭제
			result = basketDao.deleteAllBasket(con, sql);
			if(result != 1) {
				con.rollback();
				throw new SQLException("basket테이블에 데이터 삭제 실패...");
			}				
			
			// 회원 구매 횟수 증가
			result = this.memberPurchasecountUpdate(con, memberVO.getId()); 
			if(result != 1) {
				con.rollback();
				throw new SQLException("member테이블에서 회원구매 횟수가 수정 되지 않았습니다.....");
			}		
			} // else문 끝.
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * 주문상세 등록하기
	 * */
	public int[] orderDetailsInsert(Connection con, OrdersVO order) throws SQLException {
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("ORDERSDETAILS.INSERT");
		
		int[] result = null;
		try {
			// 주문상세 테이블에 데이터를 넣는다.
			ps = con.prepareStatement(sql);
			
			// 주문의 정보를 가지고 있는 order객체의 주문상세내역 리스트를 가져와서 하나씩 꺼낸다.
			for(OrdersDetailsVO orderline : order.getOrdersDetailsList()) {		
				// 주문상세 내역에 있는, 상품id를 통해 상품을 가져와서 goods변수에 담는다.
				GoodsVO goods = goodsDao.selectByGoods(orderline.getGoodsCode());
				
				ps.setInt(1, orderline.getGoodsCode());
				ps.setInt(2, goods.getGoodsPrice());//가격
				ps.setInt(3, orderline.getOrdersDetailsCount());//구매상품수량
				
				// 각각의 주문상세 합계 구해주기
				int total = this.getDetailTotal(order, goods, orderline);
				ps.setInt(4, total);
				
				// ps실행구문을 쌓아둔다고 생각하면 된다.
				// order객체에 있는, 주문상세내역을 하나씩, 주문상세테이블에 저장하는 작업(일괄처리ㅣ)
				ps.addBatch(); // 일괄처리할 작업에 추가
				ps.clearParameters();
			} // for문 끝.
			
			// 일괄처리, int[]이 담긴다.
			result = ps.executeBatch();
		} finally {
			// connection 끊으면 안된다.
			DbUtil.close(null, ps, null);
		}	
		return result;
	}

	/**
	 * 결제 테이블에 데이터 삽입
	 * */
	public int insertPayment(Connection con, PaymentVO paymentVO) throws SQLException {
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("PAYMENT.INSERT");
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			
			// 결제 테이블에 데이터 삽입
			ps.setString(1, paymentVO.getId());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps, null);
		}	
		return result;
	}
	
	/**
	 * 상품으로 재고량 감소시키키
	 * */
	public int[] decrementStock(Connection con, List<OrdersDetailsVO> ordersDetailsVOList) throws SQLException {
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("GOODS.UPDATE");
		int[] result = null;
		try {
			ps = con.prepareStatement(sql);
			
			// 주문상세내역에 해당하는, 수량만큼, goods테이블에서 수량을 뺴준다.
			for(OrdersDetailsVO vo : ordersDetailsVOList) {
				ps.setInt(1, vo.getOrdersDetailsCount());
				ps.setInt(2, vo.getGoodsCode());
				
				ps.addBatch(); // 일괄처리할 작업에 추가
				ps.clearParameters();
			} // for문 끝.
			
			result = ps.executeBatch(); // 일괄처리
		} finally {
			DbUtil.close(null, ps, null);
		}
		return result;
	}
	
	/**
	 * 결제 후 회원 구매횟수 변경
	 * */
	public int memberPurchasecountUpdate(Connection con, String id) throws SQLException {
		PreparedStatement ps = null;
		String sql = sosoyaSql.getProperty("MEMBER.UPDATEMEMBERPURCHASESCOUNT");
		int result = 0;
		
		try {			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps, null);
		}
		
		return result;
	}
	
	/**
	 * 주문내역보기
	 * */
	@Override
	public List<OrdersVO> selectOrdersByMemberId(String memberId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdersVO> list = new ArrayList<>();
		String sql = sosoyaSql.getProperty("ORDERS.SELECT");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrdersVO ordersVO  = new OrdersVO(rs.getInt("ORDERS_CODE") , rs.getString("ID"), rs.getString("ORDERS_DI"), 
						rs.getInt("ORDERS_TOTOALPRICE"), rs.getString("ORDERS_DATE"));

				//주문번호에 해당하는 상세정보 가져오기
				List<OrdersDetailsVO> ordersDetailsVOList = selectOrdersDetailsVO(ordersVO.getOrdersCode());
				
				ordersVO.setOrdersDetailsList(ordersDetailsVOList);
	        	list.add(ordersVO);
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}
	
	/**
	 * 주문상세 가져오기
	 * */
	public List<OrdersDetailsVO> selectOrdersDetailsVO(int orderCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdersDetailsVO> list = new ArrayList<>();
		String sql = sosoyaSql.getProperty("ORDERS_DETAILS.SELECT");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrdersDetailsVO ordersDetailsVO = new OrdersDetailsVO(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getInt(4),rs.getInt(5),rs.getInt(6));
				list.add(ordersDetailsVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	/**
	 * 회원등급에 따른 총결제 금액 구하기
	 * 주문 객체안의 모든 주문상세 가격을 구한다.
	 * */
	public int getTotalPrice(OrdersVO orderVO) throws SQLException {
		// 주문에 대한 DTO(order)를 받고
		// order객체의 주문상세리스트 멤버변수를 가지고 온다.
		// 등급을 구해주기 위해, MemberId를 받는다.
		MemberVO memberVO = memberDao.selectByMember(orderVO.getId());
		List<OrdersDetailsVO> orderLineList = orderVO.getOrdersDetailsList();
		int ordersTotalprice = 0;
		
		// ordersVo안의, 주문상세 내역을 하나씩 꺼낸다.
		for(OrdersDetailsVO vo : orderLineList) {
			// 주문상세 내역의 상품코드를 꺼내서 상품객체를 들고온다.
			GoodsVO goodsVO = goodsDao.selectByGoods(vo.getGoodsCode());
			
			// 주문상세리스트의 주문상세객체에 해당하는 상품번호가 goods테이블에 없다면...
			if(goodsVO == null) throw new SQLException("상품번호 오류입니다.... 주문 실패..");
			
			// 상품의 재고가, 주문상세의 상품 주문수량보다 적으면....
			else if(goodsVO.getGoodsStock() < vo.getOrdersDetailsCount()) throw new SQLException("재고량 부족입니다...");
			
			// 상품의 재고가, 주문상세의 상품 주문수량 이상이면....
			// 등급에 따라 주문상세에 해당하는 총 금액을 구해준다.
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
			
			ordersTotalprice += (int)((goodsVO.getGoodsPrice() * vo.getOrdersDetailsCount()) * discountRate);
		}// for문 끝.
		return ordersTotalprice;
	}
	
	/**
	 * 각각의 주문상세의 합계를 구해준다.
	 * */
	public int getDetailTotal(OrdersVO orderVO, GoodsVO goodsVO, OrdersDetailsVO orderline) throws SQLException {
		int ordersTotalprice = 0;
		MemberVO memberVO = memberDao.selectByMember(orderVO.getId());
		
		// 상품의 재고가, 주문상세의 상품 주문수량 이상이면....
		// 등급에 따라 주문상세에 해당하는 총 금액을 구해준다.
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
		
		ordersTotalprice = (int)((goodsVO.getGoodsPrice() * orderline.getOrdersDetailsCount()) * discountRate);
		
		return ordersTotalprice;
	}
}