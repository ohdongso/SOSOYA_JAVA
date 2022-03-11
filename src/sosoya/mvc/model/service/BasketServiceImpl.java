package sosoya.mvc.model.service;

import java.sql.SQLException;  
import java.util.ArrayList;
import java.util.List;

import sosoya.mvc.model.dao.BasketDAO;
import sosoya.mvc.model.dao.BasketDAOImpl;
import sosoya.mvc.model.dao.GoodsDAO;
import sosoya.mvc.model.dao.GoodsDAOImpl;
import sosoya.mvc.model.dto.BasketVO;
import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.model.dto.MemberVO;

public class BasketServiceImpl implements BasketService {
	private BasketDAO basketDAO = new BasketDAOImpl();
	private GoodsDAO goodsDAO = new GoodsDAOImpl();
	
	/**
	 * 장바구니등록
	 */
	@Override
	public void insertBasket(BasketVO basketVO) throws SQLException {
		GoodsVO goodsVO = goodsDAO.selectByGoods(basketVO.getGoodsCode());
		basketVO.setGoodsVO(goodsVO);
		if(basketVO.getBasketGoodsCount() > basketVO.getGoodsVO().getGoodsStock()) throw new SQLException("상품 재고량이 부족합니다. " + 
		"[ 상품이름: " + goodsVO.getGoodsName() + ", 재고량: " + goodsVO.getGoodsStock() + "개 ]");
		
		int result = basketDAO.insertBasket(basketVO);
		if(result == 0) throw new SQLException("상품이 장바구니에 담기지 않았습니다.");
	}
	
	/**
	 * 장바구니전체검색
	 */
	@Override
	public List<BasketVO> selectAllBasket(MemberVO memberVO) throws SQLException {
		List<BasketVO> list = new ArrayList<>();
		list = basketDAO.selectAllBasket(memberVO);
		if(list.size() == 0) throw new SQLException("장바구니에 등록된 상품이 없습니다.");
		return list;
	}
	
	/**
	 * 장바구니수정
	 */
	@Override
	public void updateBasket(int basketCode, int goodsCount) throws SQLException {
		// 장바구니 상품의 개수를 수정할 때, 상품의 남은 재고보다 많을 경우 예외를 발생시켜야 한다.
		// basketCode를 이용해서, basket객체를 가져오고, 객체의 goodsCode를 이용해서, 상품의 개수를 가져온다.
		BasketVO basketVO = basketDAO.selectByBasketCode(basketCode);
		
		// 입력한 장바구니 코드가 없을 때 예외를 발생시켜야 한다.
		if(basketVO == null) throw new SQLException("장바구니코드에 해당하는 상품이 존재하지 않습니다.");
		
		GoodsVO goodsVO = goodsDAO.selectByGoods(basketVO.getGoodsCode());
		if(goodsCount > goodsVO.getGoodsStock()) throw new SQLException("상품 재고량이 부족합니다. " + 
		"[ 상품이름: " + goodsVO.getGoodsName() + ", 재고량: " + goodsVO.getGoodsStock() + "개 ]");
		
		int result = basketDAO.updateBasket(basketCode, goodsCount);
		
		if(result == 0) throw new SQLException("상품이 수정되지 않았습니다.");
	}
	
	/**
	 * 장바구니삭제
	 */
	@Override
	public void deleteBasket(int basketCode) throws SQLException {
		int result = basketDAO.deleteBasket(basketCode);
		if(result == 0) throw new SQLException("장바구니코드에 해당하는 상품이 없습니다.");
	}
	
	/**
	 * 장바구니 선택삭제(일반)
	 */
	@Override
	public void deletePartBasketNomal(List<Integer> basketCodeList) throws SQLException {
		basketDAO.deletePartBasketNomal(basketCodeList);
	}
	
	/**
	 * 장바구니 전체삭제(일반)
	 */
	@Override
	public void deleteAllBasketNomal(String memberId) throws SQLException {
		// delete했을 때 result는 삭제한 총 개수가 저장 된다. 삭제가 안 됐을때는 0이 저장된다.
		int result = basketDAO.deleteAllBasketNomal(memberId);
		if(result == 0) throw new SQLException("장바구니 전체삭제가 되지 않았습니다.");
	}
}