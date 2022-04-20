package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.model.dto.ErVO;

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
}