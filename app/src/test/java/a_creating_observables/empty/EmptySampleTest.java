package a_creating_observables.empty;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Tests for {@link EmptySample}
 */
@RunWith (MockitoJUnitRunner.class)
public class EmptySampleTest {

	@InjectMocks EmptySample sample;

	TestSubscriber<String> subscriber = new TestSubscriber<>();

	@Test
	public void getEmptyCompletesWithoutAnyValues() {
		//WHEN
		Observable<String> observable = sample.getEmpty();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		subscriber.assertNoValues();
		subscriber.assertCompleted();
	}

}