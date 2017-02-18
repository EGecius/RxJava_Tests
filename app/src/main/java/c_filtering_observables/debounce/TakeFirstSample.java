package c_filtering_observables.debounce;

import rx.Observable;
import rx.functions.Func1;

final class TakeFirstSample {

	public static final String THREE = "three";
	public static final String FOUR = "four";

	Observable<String> tryTakeFirstWithMatchingCriteria() {
		return Observable.just("one", "two", THREE)
				.takeFirst(new Func1<String, Boolean>() {
					@Override
					public Boolean call(final String s) {
						return s.equals(THREE);
					}
				});
	}

	/** takeFirst() emits onComplete() if no matching elements have been found */
	Observable<String> tryTakeFirstWithoutMatchingCriteria() {
		return Observable.just("one", "two", THREE)
				.takeFirst(new Func1<String, Boolean>() {
					@Override
					public Boolean call(final String s) {
						return s.equals(FOUR);
					}
				});
	}

	/** takeFirst() emits onComplete() if no matching elements have been found. In this case it's called on empty
	 * observable */
	Observable<String> tryTakeFirstOnEmptyObservable() {
		return Observable.<String> empty()
				.takeFirst(new Func1<String, Boolean>() {
					@Override
					public Boolean call(final String s) {
						return s.equals(FOUR);
					}
				});
	}

}
