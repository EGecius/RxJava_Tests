package thead;

import rx.Observable;

/**
 * Observable to perform thread-realated assertions on
 */
public class ThreadSamples {

	public Observable<String> getSimpleObservable() {
		return Observable.just("test");
	}

}