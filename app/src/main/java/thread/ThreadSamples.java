package thread;

import rx.Observable;

/**
 * Observable to perform thread-related assertions on.
 */
public class ThreadSamples {

	public Observable<String> getSimpleObservable() {
		return Observable.just("test");
	}

}
