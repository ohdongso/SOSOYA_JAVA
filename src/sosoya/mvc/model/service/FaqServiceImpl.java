package sosoya.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import sosoya.mvc.model.dao.FaqDAO;
import sosoya.mvc.model.dao.FaqDAOImpl;
import sosoya.mvc.model.dto.FaqVO;

public class FaqServiceImpl  implements FaqService {
	private FaqDAO faqDAO = new FaqDAOImpl();
	
	/**
	 * 전체검색
	 */
	@Override
	public List<FaqVO> selectAllFaq() throws SQLException {
		List<FaqVO> list = faqDAO.selectAllFaq();
		if(list.size() == 0) throw new SQLException("검색된 데이터가 없습니다.\n");
		
		return list;
	}
	
	/**
	 * 조건검색
	 * */
	@Override
	public List<FaqVO> selectByFaq(int category) throws SQLException {
		List<FaqVO> list = faqDAO.selectByFaq(category);
		if(list.size() == 0) throw new SQLException("검색된 데이터가 없습니다.\n");
		
		return list;
	}
	
	
}