package sosoya.mvc.model.dto;

public class MemberVO {
	// 멤버변수
	private String id;
	private String password;
	private String name;
	private int purchasescount;
	private String grade; // A, B, C
	private int state; // 1,활동중 2,관리자 3,탈퇴회원
	private String email;
	private String phone;
	private String address;
	private String regdate;
	
	// 생성자
	public MemberVO() {}
	public MemberVO(String id, String password) {
		this.id = id;
		this.password = password;
	}
	public MemberVO(String id, String password, String name, int purchasescount, String grade, int state, String email,
			String phone, String address, String regdate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.purchasescount = purchasescount;
		this.grade = grade;
		this.state = state;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.regdate = regdate;
	}
	
	// getter, setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPurchasescount() {
		return purchasescount;
	}
	public void setPurchasescount(int purchasescount) {
		this.purchasescount = purchasescount;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	// toString()
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", purchasescount=" + purchasescount
				+ ", grade=" + grade + ", state=" + state + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", regdate=" + regdate + "]";
	}
}