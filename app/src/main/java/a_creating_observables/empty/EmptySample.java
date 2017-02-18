package a_creating_observables.empty;

import rx.Observable;

final class EmptySample {

	/** empty() immediately calls onComplete */
	Observable<String> getEmpty() {
		return Observable.empty();
	}
}
