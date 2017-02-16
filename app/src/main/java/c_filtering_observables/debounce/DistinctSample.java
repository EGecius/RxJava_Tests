package c_filtering_observables.debounce;

import rx.Observable;

final class DistinctSample {

	Observable<String> getDistinct() {
		return Observable.just("one", "two", "one", "two", "zero").distinct();
	}

}
