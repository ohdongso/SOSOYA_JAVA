package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sosoya.mvc.model.dto.FaqVO;
import sosoya.mvc.util.DbUtil;

public class FaqDAOImpl implements FaqDAO{
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 전체검색
	 */
	@Override
	public List<FaqVO> selectAllFaq() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FaqVO> list = new ArrayList<>();
		String sql = sosoyaSql.getProperty("FAQ.SELECTALL");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FaqVO faqVO = new FaqVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				list.add(faqVO);
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}

	/**
	 * 조건검색
	 */
	@Override
	public List<FaqVO> selectByFaq(int category) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FaqVO> list = new ArrayList<>();
		String sql = sosoyaSql.getProperty("FAQ.SELECTBYFAQ");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, category);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				FaqVO faqVO = new FaqVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				list.add(faqVO);
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}
}