package sosoya.mvc.model.dao;

import java.sql.Connection; 
import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;
import sosoya.mvc.model.dto.OrdersDetailsVO;

public interface BasketDAO {
	/**
	 * 장바구니등록
	 * @param BasketVO basketVO
	 * @return int result
	 * */
	public int insertBasket(BasketVO basketVO) throws SQLException;
	
	/**
	 * 장바구니 코드에 해당하는 상품 검색
	 * @param int basketCode
	 * @return int result
	 * */
	public BasketVO selectByBasketCode(int basketCode) throws SQLException;
	
	/**
	 * 장바구니전체검색
	 * @param MemberVO memberVO
	 * @return List<BasketVO> list
	 * */
	public List<BasketVO> selectAllBasket(MemberVO memberVO) throws SQLException;
	
	/**
	 * 장바구니 수정
	 * @param int basketCode, int goodsCount
	 * @return int result
	 * */
	public int updateBasket(int basketCode, int goodsCount) throws SQLException;
	
	/**
	 * 장바구니 삭제
	 * @param int basketCode
	 * @return int result
	 * */
	public int deleteBasket(int basketCode) throws SQLException;
	
	/**
	 * 장바구니 선택삭제(일반)
	 * @param int[] basketCode
	 * @return void
	 * */
	public void deletePartBasketNomal(List<Integer> basketCodeList) throws SQLException;
	
	/**
	 * 장바구니 전체삭제(일반)
	 * @param
	 * @return
	 * */
	public int deleteAllBasketNomal(String memberId) throws SQLException;
			
	/**
	 * ID에 해당하는 장바구니 전체삭제(트랜잭션으로 처리)
	 * @param String memberId
	 * @return int result
	 * */
	public int deleteAllBasket(Connection con, String memberId) throws SQLException;
	
	/**
	 * 선택한 장바구니 삭제(트랜잭션으로 처리)
	 * @param Connection con, OrdersVO ordersVO
	 * @return int[] result
	 */
	public int[] deleteByBasket(Connection con, List<OrdersDetailsVO> ordersVO) throws SQLException;
}