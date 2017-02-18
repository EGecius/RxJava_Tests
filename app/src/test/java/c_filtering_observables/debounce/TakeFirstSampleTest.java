package c_filtering_observables.debounce;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static c_filtering_observables.debounce.TakeFirstSample.THREE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TakeFirstSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class TakeFirstSampleTest {

	@InjectMocks TakeFirstSample sample;

	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Test
	public void takeFirst_with_matchingCriteria() {
		//WHEN
		Observable<String> observable = sample.tryTakeFirstWithMatchingCriteria();
		//THEN
		observable.subscribe(testSubscriber);
		testSubscriber.assertNoErrors();
		List<String> onNextEvents = testSubscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(onNextEvents.get(0)).isEqualTo(THREE);
	}

	@Test
	public void takeFirst_without_matchingCriteria() {
		//WHEN
		Observable<String> observable = sample.tryTakeFirstWithoutMatchingCriteria();
		//THEN
		observable.subscribe(testSubscriber);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNoValues();
		testSubscriber.assertCompleted();
	}

	@Test
	public void takeFirst_ontOnEmptyObservable() {
		//WHEN
		Observable<String> observable = sample.tryTakeFirstOnEmptyObservable();
		//THEN
		observable.subscribe(testSubscriber);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNoValues();
		testSubscriber.assertCompleted();
	}


}