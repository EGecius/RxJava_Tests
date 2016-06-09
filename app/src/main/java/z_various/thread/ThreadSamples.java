package z_various.thread;

import rx.Observable;

/**
 * Observable to perform z_various.thread-related assertions on.
 */
class ThreadSamples {

	public Observable<String> getSimpleObservable() {
		return Observable.just("test");
	}

}
