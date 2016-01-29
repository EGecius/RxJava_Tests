package io_thread;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Single Responsibility:
 *
 * Tests for {@link IoThreadSample}
 */
public class IoThreadSampleTest {

	IoThreadSample sample;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		sample = new IoThreadSample();
	}

	@Test
	public void when_subscribedToIoThreadObservable_then_eventsReceivedOnIoThread() {
		//WHEN
		subscribedToIoThreadObservable();
		//THEN
		eventsReceivedOnIoThread();
	}

	private void subscribedToIoThreadObservable() {
		sample.getIoThreadObservable().subscribe(testSubscriber);
	}

	private void eventsReceivedOnIoThread() {
		Thread seenThread = testSubscriber.getLastSeenThread();
		assertThat(seenThread.getName()).isEqualTo("RxCachedThreadScheduler-1");
	}

}