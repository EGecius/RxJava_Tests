package a_creating_observables.just;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JustSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class JustSampleTest {

	TestSubscriber<String> subscriber = new TestSubscriber<>();

	@InjectMocks JustSample sample;

	@Test
	public void getJustEmittingNull() {
		//WHEN
		Observable<String> observable = sample.getJustEmittingNull();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		subscriber.assertCompleted();
		List<String> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(onNextEvents.get(0)).isEqualTo(null);
	}

}