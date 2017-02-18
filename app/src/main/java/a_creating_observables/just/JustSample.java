package a_creating_observables.just;

import rx.Observable;

final class JustSample {

	/** onNext() calls can be called with null too. */
	public Observable<String> getJustEmittingNull() {
		return Observable.just(null);
	}
}
