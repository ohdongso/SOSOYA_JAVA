package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.MemberVO;

public interface BasketService {
	/**
	 * 장바구니등록
	 * @param BasketVO basketVO
	 * @return
	 * */
	public void insertBasket(BasketVO basketVO) throws SQLException;
	
	/**
	 * 장바구니전체검색
	 * @param MemberVO memberVO
	 * @return List<BasketVO> list
	 * */
	public List<BasketVO> selectAllBasket(MemberVO memberVO) throws SQLException;
	
	/**
	 * 장바구니수정
	 * @param int basketCode, int goodsCount
	 * @return int result
	 * */
	public void updateBasket(int basketCode, int goodsCount) throws SQLException;
	
	/**
	 * 장바구니삭제
	 * @param MemberVO memberVO
	 * @return List<BasketVO> list
	 * */
	public void deleteBasket(int basketCode) throws SQLException;
}