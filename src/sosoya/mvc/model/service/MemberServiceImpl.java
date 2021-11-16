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
			if(result == 0) throw new SQLException("회원가입에 실패했습니다.");
		} else {
			throw new DuplicatedException(memberVO.getId() + "는 이미 가입된 아이디입니다. ID를 다시 입력해주세요.\n");
		}
	}
	
	/**
	 * 아이디에 해당하는 회원검색
	 */
	@Override
	public MemberVO selectByMember(String id) throws SQLException {
		MemberVO result = memberDAO.selectByMember(id);
		if(result == null) throw new SQLException("아이디에 해당하는 회원을 찾지 못했습니다.");
		return result;
	}

	/**
	 * 회원탈퇴
	 * */
	@Override
	public void memberStateUpdate(String id) throws SQLException {
		int result = memberDAO.memberStateUpdate(id);
		if(result == 0) throw new SQLException("회원탈퇴가 되지 않았습니다.");
	}
	
	/**
	 * 로그인
	 * */
	@Override
	public MemberVO login(String id, String password) throws NotFoundException, SQLException {
		MemberVO memberVO = memberDAO.login(id, password);
		if(memberVO == null) {
			throw new NotFoundException("정보를 다시 확인해주세요.");
		}
		return memberVO;
	}
	
	/**
	 * 회원정보수정
	 * */
	@Override
	public void updateMember(MemberVO memberVO) throws SQLException {
		int result = memberDAO.updateMember(memberVO);
		if(result == 0) {
			throw new SQLException("회원 정보가 수정되지 않았습니다.\n");
		}
	}
}