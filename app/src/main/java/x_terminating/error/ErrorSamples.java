package x_terminating.error;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Samples to test Error scenarios with
 */
public class ErrorSamples {

	public static final String MESSAGE = "our_message";
	@SuppressWarnings ("ThrowableInstanceNeverThrown")
	public static final RuntimeException RUNTIME_EXCEPTION = new RuntimeException(MESSAGE);
	public static final RethrownException RETHROWN_EXCEPTION = new RethrownException();

	/** Returns Observable which calls onError at the point of subscription */
	public Observable<String> getErrorObservable() {
		return Observable.error(RUNTIME_EXCEPTION);
	}

	/** Returns Observable which throws its own exception in doOnError() */
	public Observable<String> getErrorObservableWithRethrowing() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(final Subscriber<? super String> subscriber) {
				throw RUNTIME_EXCEPTION;
			}
		}).doOnError(new Action1<Throwable>() {
			@Override
			public void call(final Throwable throwable) {
				throw RETHROWN_EXCEPTION;
			}
		});
	}

}
