package z_various.thread;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
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

	// Schedulers.immediate() performs work immediately on your current thread
	@Test
	public void when_getSimpleObservableCalled_and_subscribedOnImmediateScheduler_then_LastThreadSeenIsMain() {
		//WHEN
		Observable<String> observable = samples.getSimpleObservable();
		//AND
		observable
				.subscribeOn(Schedulers.immediate())
				.subscribe(testSubscriber);

		//THEN
		Thread thread = testSubscriber.getLastSeenThread();
		assertThat(thread.getName()).isEqualTo("main");
	}

	// Schedulers.immediate() performs work immediately on your current thread
	@Test
	public void when_getSimpleObservableCalled_and_subscribedOnImmediateScheduler_then_LastThreadSeenIsIo() {
		//WHEN
		Observable<String> observable = samples.getSimpleObservable();
		//AND
		observable
				.subscribeOn(Schedulers.io())
				.subscribeOn(Schedulers.immediate())
				.subscribe(testSubscriber);

		//THEN
		Thread thread = testSubscriber.getLastSeenThread();
		assertThat(thread.getName()).isEqualTo("RxCachedThreadScheduler-1");
	}

	@Test
	public void when_getSimpleObservableCalled_and_subscribedOnIoScheduler_then_LastThreadSeenIsIo() {
		//WHEN
		Observable<String> observable = samples.getSimpleObservable();
		//AND
		observable
				.subscribeOn(Schedulers.io())
				.subscribe(testSubscriber);

		//THEN
		Thread thread = testSubscriber.getLastSeenThread();
		assertThat(thread.getName()).isEqualTo("RxCachedThreadScheduler-1");
	}

	// this test fails with "java.lang.RuntimeException: Method getMainLooper in android.os.Looper not mocked. See
	// http://g.co/androidstudio/not-mocked"
	//this is because AndroidSchedulers calls Looper.getMainLooper()
	@Ignore
	@Test
	public void
	when_getSimpleObservableCalled_and_subscribedOnIoScheduler_and_observedOnImmediateThread_then_LastThreadSeenIsMain() {
		//WHEN
		Observable<String> observable = samples.getSimpleObservable();
		//AND
		observable
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(testSubscriber);

		//THEN
		Thread thread = testSubscriber.getLastSeenThread();
		assertThat(thread.getName()).isEqualTo("main");
	}

}