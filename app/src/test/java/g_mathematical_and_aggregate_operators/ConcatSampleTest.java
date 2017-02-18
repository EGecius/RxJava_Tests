package g_mathematical_and_aggregate_operators;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static g_mathematical_and_aggregate_operators.ConcatSample.DEFAULT;
import static g_mathematical_and_aggregate_operators.ConcatSample.FROM_DISK;
import static g_mathematical_and_aggregate_operators.ConcatSample.FROM_MEMORY;
import static g_mathematical_and_aggregate_operators.ConcatSample.FROM_NETWORK;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ConcatSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class ConcatSampleTest {

	TestSubscriber<String> subscriber = new TestSubscriber<>();

	@InjectMocks ConcatSample sample;

	@Test
	public void when_getFirstItemCheckingMultipleSources_then_returns1stItemOnly() {
		//WHEN
		Observable<String> observable = sample.getFirstItemCheckingMultipleSources();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		List<String> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(onNextEvents.get(0)).isEqualTo(FROM_MEMORY);
	}

	@Test
	public void when_getAllItemsCheckingMultipleSources_then_returnsAllItems() {
		//WHEN
		Observable<String> observable = sample.getAllItemsCheckingMultipleSources();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		List<String> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(3);
		assertThat(onNextEvents.get(0)).isEqualTo(FROM_MEMORY);
		assertThat(onNextEvents.get(1)).isEqualTo(FROM_DISK);
		assertThat(onNextEvents.get(2)).isEqualTo(FROM_NETWORK);
	}


	@Test
	public void when_getFirstOrDefaultItemCheckingMultipleSources_then_returnsDefaultValue() {
		//WHEN
		Observable<String> observable = sample.getFirstOrDefaultItemCheckingMultipleSources();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		List<String> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(1);
		assertThat(onNextEvents.get(0)).isEqualTo(DEFAULT);
	}

	@Test
	public void when_getAllItemsFromConcatWith_then_returnsAllItems() {
		//WHEN
		Observable<String> observable = sample.getAllItemsFromConcatWith();
		//THEN
		observable.subscribe(subscriber);
		subscriber.assertNoErrors();
		List<String> onNextEvents = subscriber.getOnNextEvents();
		assertThat(onNextEvents.size()).isEqualTo(3);
		assertThat(onNextEvents.get(0)).isEqualTo(FROM_MEMORY);
		assertThat(onNextEvents.get(1)).isEqualTo(FROM_DISK);
		assertThat(onNextEvents.get(2)).isEqualTo(FROM_NETWORK);
	}

}