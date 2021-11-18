package sosoya.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;

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

}