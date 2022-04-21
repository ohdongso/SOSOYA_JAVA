package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dao.ErDAO;
import sosoya.mvc.model.dao.ErDAOImpl;
import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.dto.MemberVO;

public class ErServiceImpl implements ErService {
	private ErDAO erDao = new ErDAOImpl();
	
	/**
	 * 교환하기
	 */
	@Override
	public void insertErExchange(ErVO erVo) throws SQLException {
		int result = erDao.insertErExchange(erVo);
		if(result == 0) {
			throw new SQLException("상품교환이 진행되지 않았습니다.");
		}
	}

	/**
	 * 환불하기
	 */
	@Override
	public void insertErRefund(ErVO erVo) throws SQLException {
		int result = erDao.insertErRefund(erVo);
		if(result == 0) {
			throw new SQLException("상품환불이 진행되지 않았습니다.");
		}
	}
	
	/**
	 * (교환,환불) 내역전체조회
	 */
	@Override
	public List<ErVO> selectAllEr(MemberVO memberVo) throws SQLException {
		List<ErVO> list = erDao.selectAllEr(memberVo);
		if(list == null || list.size() == 0) {
			throw new SQLException("(교환,환불)진행중인 내역이 없습니다.");
		}
		return list;
	}
}