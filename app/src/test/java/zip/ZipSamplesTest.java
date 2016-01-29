package zip;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Single Responsibility:
 *
 * Tests for {@link ZipSamples}
 */
public class ZipSamplesTest {

	ZipSamples samples;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		samples = new ZipSamples();
	}

	@Test
	public void when_subscribedToObservable_then_expectedEmissionsReceived() {
		//WHEN
		subscribedToObservable();
		//THEN
		expectedEmissionsReceived();
	}

	private void subscribedToObservable() {
		samples.getObservable().subscribe(testSubscriber);
	}

	private void expectedEmissionsReceived() {
		List<String> onNextEvents = testSubscriber.getOnNextEvents();
		for (int i = 0; i < onNextEvents.size(); i++) {
			String actual = onNextEvents.get(i);
			String expected = ZipSamples.INTEGERS[i] + ZipSamples.STRINGS[i];
			assertThat(actual).isEqualTo(expected);
		}
	}

}