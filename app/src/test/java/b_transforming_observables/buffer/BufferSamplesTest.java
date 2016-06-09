package b_transforming_observables.buffer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Single Responsibility:
 *
 * Tests for {@link BufferSamples}
 */
@RunWith (MockitoJUnitRunner.class)
public class BufferSamplesTest {

	BufferSamples samples;
	TestSubscriber<List<String>> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		samples = new BufferSamples();
	}

	@Test
	public void when_bufferObservableSubscribedTo_and_weWaitPassedBufferedTime_then_singleListEmittedWithExpectedValues
			() {
		//WHEN
		bufferObservableSubscribedTo();
		//AND
		weWaitPassedBufferedTime();
		//THEN
		singleListEmittedWithExpectedValues();
	}

	@Test
	public void when_bufferObservableSubscribedTo_and_weWaitPassedBufferedTime_then_subscriberStillSubscribed() {
		//WHEN
		bufferObservableSubscribedTo();
		//AND
		weWaitPassedBufferedTime();
		//THEN
		subscriberStillSubscribed();
	}

	private void subscriberStillSubscribed() {
		assertThat(testSubscriber.isUnsubscribed()).isFalse();
	}

	private void singleListEmittedWithExpectedValues() {

		//get all lists of batched emissions emitted by buffer Observable
		List<List<String>> onNextEvents = testSubscriber.getOnNextEvents();
		List<String> firstEvent = onNextEvents.get(0);

		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(firstEvent.size()).isEqualTo(3);

		List<String> expected = samples.getExpectedList();

		for (int i = 0; i < firstEvent.size(); i++) {
			assertThat(firstEvent.get(i)).isEqualTo(expected.get(i));
		}
	}

	private void weWaitPassedBufferedTime() {
		testSubscriber.awaitTerminalEvent(BufferSamples.BUFFER_MS + 50, TimeUnit.MILLISECONDS);
	}

	private void bufferObservableSubscribedTo() {
		samples.getBufferObservable().subscribe(testSubscriber);
	}

}