package z_various.thread;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Single Responsibility:
 *
 * Tests for {@link ThreadSamples}
 * For some reason these tests fail with NPE when run from AllTestsSuite
 */
public class ThreadSamplesTest {

	ThreadSamples samples;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		samples = new ThreadSamples();
	}

	@Test
	public void when_getSimpleObservableCalled_and_subscribedOnDefaultScheduler_then_LastThreadSeenIsMain() {
		//WHEN
		Observable<String> observable = samples.getSimpleObservable();
		//AND
		observable.subscribe(testSubscriber);

		//THEN
		Thread thread = testSubscriber.getLastSeenThread();
		assertThat(thread.getName()).isEqualTo("main");
	}

	@Test
	public void when_getSimpleObservableCalled_and_subscribedOnIoScheduler_then_LastThreadSeenIsIo() {
		//WHEN
		Observable<String> observable = samples.getSimpleObservable();
		//AND
		observable.subscribeOn(Schedulers.io()).subscribe(testSubscriber);

		//THEN
		Thread thread = testSubscriber.getLastSeenThread();
		assertThat(thread.getName()).isEqualTo("RxCachedThreadScheduler-1");
	}

}