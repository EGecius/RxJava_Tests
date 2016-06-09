package a_creating_observables.defer;

import rx.Observable;
import rx.functions.Func0;

/**
 * Sample code with {@link Observable#defer(Func0)}
 */
public class DeferSample {

	private Boolean isJustExecuted = false;

	public Observable<Boolean> getJustObservable() {
		return Observable.just(getIsJustExecuted());
	}

	private Boolean getIsJustExecuted() {
		isJustExecuted = true;
		return isJustExecuted;
	}

	public Boolean isJustExecuted() {
		return isJustExecuted;
	}

	public Observable<Boolean> getObservableWithDefer() {
		return Observable.defer(new Func0<Observable<Boolean>>() {
			@Override
			public Observable<Boolean> call() {
				return getJustObservable();
			}
		});
	}

}
