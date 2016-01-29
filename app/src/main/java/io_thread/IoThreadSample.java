package io_thread;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Samples to run tests related to background threading
 */
public class IoThreadSample {

	public Observable<String> getIoThreadObservable() {
		return Observable.just("test").subscribeOn(Schedulers.io());
	}

}
