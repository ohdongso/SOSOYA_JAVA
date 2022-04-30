package sosoya.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.dto.MemberVO;

public interface ErDAO {
	/**
	 * 교환하기
	 * @param ErVO erVo
	 * @return int result
	 * */
	public int insertErExchange(ErVO erVo) throws SQLException;
	
	/**
	 * 환불하기
	 * @param ErVO erVo
	 * @return int result
	 * */
	public int insertErRefund(ErVO erVo) throws SQLException;
	
	/**
	 * (교환,환불) 내역전체조회
	 * @param ErVO erVo
	 * @return int result
	 * */
	public List<ErVO> selectAllEr(MemberVO memberVo) throws SQLException;
	
	/**
	 * (교환,환불)취소가능한 내역전체조회
	 * @param ErVO erVo
	 * @return int result
	 * */
	public List<ErVO> selectAllCancelEr(MemberVO memberVo) throws SQLException;
}