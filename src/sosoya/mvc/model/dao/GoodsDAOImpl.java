package sosoya.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sosoya.mvc.model.dto.GoodsVO;
import sosoya.mvc.util.DbUtil;

public class GoodsDAOImpl implements GoodsDAO {
	private Properties sosoyaSql = DbUtil.getProFile();
	
	/**
	 * 전체검색
	 */
	@Override
	public List<GoodsVO> selectAllGoods() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = sosoyaSql.getProperty("GOODS.SELECTALL");
		
		List<GoodsVO> list = new ArrayList<>();
		GoodsVO goodsVO = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {			
				goodsVO = new GoodsVO(rs.getInt("GOODS_CODE"), rs.getString("GOODS_NAME"), rs.getInt("GOODS_PRICE"), 
						rs.getInt("GOODS_STOCK"), rs.getString("GOODS_REGDATE"), rs.getInt("GOODS_REVIEWCOUNT"), rs.getInt("GOODS_CATEGORY"), rs.getFloat("GOODS_GRADEAVG"));
				list.add(goodsVO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}
	
		
}