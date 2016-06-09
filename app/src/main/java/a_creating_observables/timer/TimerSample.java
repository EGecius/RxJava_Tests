package a_creating_observables.timer;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Sample code to write tests with {@link Observable#timer(long, TimeUnit)}
 */
public class TimerSample {

	public static final int DELAY_MS = 100;

	public Observable<Long> getObservable() {
		return Observable.timer(DELAY_MS, TimeUnit.MILLISECONDS);
	}

}
