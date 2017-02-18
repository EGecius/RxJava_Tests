package a_creating_observables.just;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JustSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class JustSampleTest {


	@InjectMocks JustSample sample;

	@Test
	public void observableCanEmitNulls() {
		TestSubscriber<String> subscriber = new TestSubscriber<>();

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

	@Test
	public void observableCanArraysOfPrimitives() {
		TestSubscriber<boolean[]> subscriber = new TestSubscriber<>();

		//WHEN
		Observable<boolean[]> observable = sample.getJustEmittingBooleanPrimitives();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		subscriber.assertCompleted();
		List<boolean[]> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(onNextEvents.get(0)).isEqualTo(new boolean[1]);
	}

	@Test
	public void observableCanArraysOfObjects() {
		TestSubscriber<Boolean[]> subscriber = new TestSubscriber<>();

		//WHEN
		Observable<Boolean[]> observable = sample.getJustEmittingBooleanObjects();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		subscriber.assertCompleted();
		List<Boolean[]> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(onNextEvents.get(0)).isEqualTo(new Boolean[1]);
	}


}