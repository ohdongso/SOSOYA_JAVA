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
			
			// 주문 전체금액(상세를 모두 포함한 가격)
			int totalPrice = getTotalPrice(ordersVO);
			
			// getTotalPrice(ordersVO)함수에서 정가를 저장하고 반환 후 출력 되는지 확인
			// System.out.println("확인 할인가 : " + totalPrice);
			// System.out.println("확인 정가 : " + ordersVO.getOriginalPrice());
			
			ps.setInt(3, totalPrice);
			
			// orders테이블에 데이터를 추가한다.
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				System.out.println("확인");
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
				// order객체의 주문상세 리스트를 넘긴다.
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
				
				// 총결제 금액 주문객체에 저장하기.(할인률 적용)
				ordersVO.setOrdersTotalprice(totalPrice);
				
				// === 결제하기 ===
				if(PaymentView.printPaymentDirectOrder(memberVO, goodsVoList, ordersVO)){
					// 결제테이블에 데이터 삽입(ID만 넣으면 된다.)
					PaymentVO paymentVO = new PaymentVO(0, 0, memberVO.getId(), null);
					result = this.insertPayment(con, paymentVO);
					
					if(result != 1) {
						con.rollback();
						throw new SQLException("payment테이블에 데이터 삽입 실패...");
					}
				} else {
					con.rollback();
					throw new SQLException("N을 눌러 결제가 취소 되었습니다.");
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

		// 주문상세에는(제품단가, 구매수량, 총가격, 상품이름)이 서비스 단에서 저장 돼 넘어왔다.
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
						
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			
			// memberId, ordersDi, ordersTotalprice ==> 3개의 컬럼을 구해줘야한다.
			ps.setString(1, ordersVO.getId());
			ps.setString(2, ordersVO.getOrdersDi());
			
			// 주문 전체금액(상세를 모두 포함한 가격)
			int totalPrice = this.getTotalPrice(ordersVO);
			ps.setInt(3, totalPrice);
			
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
				for(OrdersDetailsVO ordersDetailsVO : ordersVO.getOrdersDetailsList()) {
					goodsVoList.add(goodsDao.selectByGoods(ordersDetailsVO.getGoodsCode()));
				}
				
				// 총결제 금액 구하기.
				ordersVO.setOrdersTotalprice(totalPrice);
				
				if(PaymentView.printPayment(memberVO, goodsVoList, ordersVO)){
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
				
				// 장바구니 데이터 전체삭제
				result = basketDao.deleteAllBasket(con, memberVO.getId());
				if(result == 0) {
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
	 * 장바구니상품선택주문
	 */
	@Override
	public int insertBasketByOrder(OrdersVO ordersVO) throws SQLException {
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
			re = basketDao.deleteByBasket(con, ordersVO.getOrdersDetailsList());
			for(int i : re) {
				if(i != Statement.SUCCESS_NO_INFO) {
					con.rollback();
					throw new SQLException("basket테이블에 장바구니 데이터 삭제 실패...");
				}
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
		// 상품코드, 상품가격, 상품구매개수, 상품구매전체가격
		String sql = sosoyaSql.getProperty("ORDERSDETAILS.INSERT");
		
		int[] result = null;
		try {
			// 주문상세 테이블에 데이터를 넣는다.
			ps = con.prepareStatement(sql);
			
			// 주문의 정보를 가지고 있는 order객체의 주문상세내역 리스트를 가져와서 하나씩 꺼낸다.
			for(OrdersDetailsVO orderDetailVo : order.getOrdersDetailsList()) {		
				// 주문상세 내역에 있는, 상품id를 통해 상품객체를 가져와서 goods변수에 담는다.
				GoodsVO goods = goodsDao.selectByGoods(orderDetailVo.getGoodsCode());
				
				ps.setInt(1, orderDetailVo.getGoodsCode());
				ps.setInt(2, goods.getGoodsPrice()); 
				ps.setInt(3, orderDetailVo.getOrdersDetailsCount());
				
				// 각각의 주문상세 합계 구해주기
				int total = this.getDetailTotal(order, goods, orderDetailVo);
				ps.setInt(4, total);
				
				// ps실행구문을 쌓아둔다고 생각하면 된다.
				// order객체에 있는, 주문상세내역을 하나씩, 주문상세테이블에 저장하는 작업(일괄처리)
				ps.addBatch(); // 일괄처리할 작업에 추가
				
				// 반복루프가 돌면서 앞의 ps도 전부 닫아주기 위해 사용
				// PreparedStatement의 객체를 새로할당하는게 아니라 파라미터를 할당하고 
				// 실행한 다음에 ps.clearParameters();로 파라미터를 클리어해버린다. 
				// 루프돌면서 다시 할당하고 이렇게 돌리면 PreparedStatement객체를 여러번 사용할 수 있다. 
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
	 * 주문코드에 해당하는 주문내역 가져오기
	 */
	@Override
	public OrdersVO selectOrdersByOrderCode(int orderCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersVO ordersVO = null;
		String sql = sosoyaSql.getProperty("ORDERS.SELECTBYORDERCODE");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderCode);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ordersVO = new OrdersVO(rs.getInt("ORDERS_CODE") , rs.getString("ID"), rs.getString("ORDERS_DI"), 
						rs.getInt("ORDERS_TOTOALPRICE"), rs.getString("ORDERS_DATE"));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return ordersVO;
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
			
			// 주문상세내역에서 주문수량만큼 goods테이블에서 수량을 뺴준다.
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
			
			// ID에 해당하는 모든 주문목록을 가져온다.
			while(rs.next()) {
				OrdersVO ordersVO  = new OrdersVO(rs.getInt("ORDERS_CODE") , rs.getString("ID"), rs.getString("ORDERS_DI"), 
						rs.getInt("ORDERS_TOTOALPRICE"), rs.getString("ORDERS_DATE"));

				// 각각의 주문코드에 해당하는 주문상세목록을 주문객체에 저장한다.
				List<OrdersDetailsVO> ordersDetailsVOList = selectOrdersDetailsVO(ordersVO.getOrdersCode());
				
				// 주문상세 리스트가 담긴상황에서, 각 주문상세의 상품코드에 해당하는 상품이름을 list에 저장해줘야 한다.
				for(OrdersDetailsVO vo : ordersDetailsVOList) {		
					String goodsName = goodsDao.selectByNameGoodsOne(vo.getGoodsCode());
					vo.setGoodsName(goodsName);
				}
				
				// 상품이름을 저장한 주문상세객체 리스트를 다시 주문객체에 저장해준다.
				ordersVO.setOrdersDetailsList(ordersDetailsVOList);
		
				// 최종적으로 저장된 주문객체를, 리스트에 저장한다.
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
	@Override
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
				// 주문상세 상태값 저장되어 있는 상태. rs.getInt(7)
				OrdersDetailsVO ordersDetailsVO = new OrdersDetailsVO(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
				list.add(ordersDetailsVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	/**
	 * 주문코드에 해당하는 주문상세중 리뷰가 작성되지않은 주문상세를 가져온다.
	 */
	@Override
	public List<OrdersDetailsVO> selectOrdersReviewDetailsVO(int orderCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdersDetailsVO> list = new ArrayList<>();
		String sql = sosoyaSql.getProperty("ORDERS_DETAILS.SELECTREVIEWNULL");
											
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
		// 등급을 구해주기 위해, MemberVO객체를 구해준다.
		MemberVO memberVO = memberDao.selectByMember(orderVO.getId());
		
		// orderVO객체의 주문상세리스트 멤버변수를 가지고 온다.
		List<OrdersDetailsVO> orderLineList = orderVO.getOrdersDetailsList();
		int ordersTotalprice = 0;
		
		// 정가 총 금액
		int originalTotalPrice = 0;
		
		// OrdersVO안의, 주문상세 내역을 하나씩 꺼낸다.
		for(OrdersDetailsVO vo : orderLineList) {
			// 주문상세 내역의 상품코드로 상품객체를 들고온다.
			GoodsVO goodsVO = goodsDao.selectByGoods(vo.getGoodsCode());
			
			// 주문상세리스트의 주문상세객체에 해당하는 상품번호가 goods테이블에 없다면...
			if(goodsVO == null) throw new SQLException("상품번호 오류입니다.... 주문 실패..");
			// 상품의 재고가, 주문상세의 상품 주문수량보다 적으면....
			else if(goodsVO.getGoodsStock() < vo.getOrdersDetailsCount()) throw new SQLException(goodsVO.getGoodsName() + "상품 재고량이 부족합니다.");
			
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
			// 할인가 적용된 총 금액
			ordersTotalprice += (int)((goodsVO.getGoodsPrice() * vo.getOrdersDetailsCount()) * discountRate);
			
			// 정가 총 금액
			originalTotalPrice += goodsVO.getGoodsPrice() * vo.getOrdersDetailsCount();
		}// for문 끝.
		orderVO.setOriginalPrice(originalTotalPrice);
		return ordersTotalprice;
	}

	/**
	 * 각각의 주문상세의 합계를 구해준다.
	 * */
	public int getDetailTotal(OrdersVO orderVO, GoodsVO goodsVO, OrdersDetailsVO orderDetailVo) throws SQLException {
		int orderDetailTotalPrice = 0;
		MemberVO memberVO = memberDao.selectByMember(orderVO.getId());
		
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
		
		orderDetailTotalPrice = (int)((goodsVO.getGoodsPrice() * orderDetailVo.getOrdersDetailsCount()) * discountRate);
		
		return orderDetailTotalPrice;
	}
	
	/**
	 * 주문코드에 해당하는 주문내역 삭제하기
	 */
	@Override
	public int deleteOrderList(List<Integer> orderCodeList) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;	
		String sql = sosoyaSql.getProperty("ORDERS.DELETE");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			// 오토커밋을 하지 않겠다.
			con.setAutoCommit(false);
						
			// 트랜잭션 시작
			ps = con.prepareStatement(sql);
			
			for(int orderCode : orderCodeList) {
				ps.setInt(1, orderCode);
				result = ps.executeUpdate();
				if(result == 0) {
					con.rollback();
					throw new SQLException("ORDER테이블에 주문내역삭제중 상태 2로변경 실패...");
				}
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
}