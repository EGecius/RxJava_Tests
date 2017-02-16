package c_filtering_observables.debounce;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DistinctSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class DistinctSampleTest {

	@InjectMocks DistinctSample sample;

	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Test
	public void getDistinct() {
		//WHEN
		Observable<String> distinctObservable = sample.getDistinct();
		//THEN
		distinctObservable.subscribe(testSubscriber);
		List<String> onNextEvents = testSubscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(3);
		assertThat(onNextEvents.get(0)).isEqualTo("one");
		assertThat(onNextEvents.get(1)).isEqualTo("two");
		assertThat(onNextEvents.get(2)).isEqualTo("zero");
	}

}