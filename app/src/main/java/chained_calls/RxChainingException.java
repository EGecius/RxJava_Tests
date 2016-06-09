package chained_calls;

/**
 * Tells that exception has occurred while chaining Rx code
 */
public class RxChainingException extends RuntimeException {

	public final String errorMsg;

	public RxChainingException(final Response response) {
		errorMsg = response.errorMsg;
	}
}
