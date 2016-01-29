package accumulate_calls;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Demos how to user Observable.buffer
 */
public class BufferSamples {

	public static final int BUFFER_MS = 100;

	public List<String> getExpectedList() {
		return Arrays.asList("One", "Two", "Three");
	}

	public Observable<List<String>> getBufferObservable() {

		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				for (String s : getExpectedList()) {
					subscriber.onNext(s);
				}
			}
		}).buffer(BUFFER_MS, TimeUnit.MILLISECONDS);
	}

}
