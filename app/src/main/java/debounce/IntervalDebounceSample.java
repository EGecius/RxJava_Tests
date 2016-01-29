package debounce;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Sample for to run on tests related to {@link Observable#debounce(long, TimeUnit)} in combination
 * with {@link Observable#interval(long, TimeUnit)}
 */
public class IntervalDebounceSample {

	public static final int INTERVAL_PERIOD = 400;
	public static final int DEBOUNCE_MS = 400;

	public Observable<Long> getObservable() {
		return Observable
				.interval(INTERVAL_PERIOD, TimeUnit.MILLISECONDS)
				.debounce(DEBOUNCE_MS, TimeUnit.MILLISECONDS);
	}

}
