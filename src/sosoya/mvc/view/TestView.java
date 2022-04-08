package sosoya.mvc.view;

import sosoya.mvc.model.dao.PaymentDAO;
import sosoya.mvc.model.dao.PaymentDAOImpl;
import sosoya.mvc.model.dto.MemberVO;

public class TestView {

	public static void main(String[] args) throws Exception {
		MemberVO memberVo = new MemberVO();
		memberVo.setId("dongso");
		PaymentDAO paymentDao = new PaymentDAOImpl();
		paymentDao.selectAllErPayment(memberVo);
	}

}
