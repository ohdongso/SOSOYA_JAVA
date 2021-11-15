package sosoya.mvc.model.service;

import java.sql.SQLException;

import sosoya.mvc.exception.DuplicatedException;
import sosoya.mvc.exception.NotFoundException;
import sosoya.mvc.model.dao.MemberDAO;
import sosoya.mvc.model.dao.MemberDAOImpl;
import sosoya.mvc.model.dto.MemberVO;

public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO = new MemberDAOImpl();
	
	/**
	 * 회원등록
	 * */
	@Override
	public void inputMember(MemberVO memberVO) throws SQLException, DuplicatedException {
		if(!memberDAO.dulicateByMember(memberVO.getId())) {
			int result = memberDAO.insertMember(memberVO);
			if(result == 0) throw new SQLException("회원가입에 실패했습니다.\n");
		} else {
			throw new DuplicatedException(memberVO.getId() + "는 이미 가입된 아이디입니다. ID를 다시 입력해주세요.\n");
		}
	}
	
	/**
	 * 로그인
	 * */
	@Override
	public MemberVO login(String id, String password) throws NotFoundException, SQLException {
		MemberVO memberVO = memberDAO.login(id, password);
		if(memberVO == null) {
			throw new NotFoundException("정보를 다시 확인해주세요.\n");
		}
		return memberVO;
	}
}