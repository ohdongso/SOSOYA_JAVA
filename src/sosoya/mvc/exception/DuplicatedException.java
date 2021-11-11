package sosoya.mvc.exception;

/**
 * unique해야 하는 경우에 중복이 발생했을 경우 예외
 * */
public class DuplicatedException extends Exception {
	public DuplicatedException() {}
	public DuplicatedException(String message) {
		super(message);
	}
}
