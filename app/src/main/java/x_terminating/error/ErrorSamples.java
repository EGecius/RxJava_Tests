package x_terminating.error;

import rx.Observable;

/**
 * Samples to test Error scenarios with
 */
public class ErrorSamples {

	public static final String MESSAGE = "our_message";
	@SuppressWarnings ("ThrowableInstanceNeverThrown")
	public static final RuntimeException RUNTIME_EXCEPTION = new RuntimeException(MESSAGE);

	/** Returns Observable which calls onError at the point of subscription */
	public Observable<String> getErrorObservable() {
		return Observable.error(RUNTIME_EXCEPTION);
	}

}
