package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dto.ErVO;
import sosoya.mvc.model.dto.MemberVO;

public interface ErService {
	/**
	 * 교환하기
	 * @param ErVO erVo
	 * @return
	 * */
	public void insertErExchange(ErVO erVo) throws SQLException;
	
	/**
	 * 환불하기
	 * @param ErVO erVo
	 * @return int result
	 * */
	public void insertErRefund(ErVO erVo) throws SQLException;
	
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