package simple;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import activities.DaysOfTheWeekSamples;
import rx.Observable;
import rx.observables.BlockingObservable;

import static org.junit.Assert.assertEquals;

/**
 * Tests using Observable.toBlocking() method call
 */
public class BlockingObservableTest {

	DaysOfTheWeekSamples samples;
	private Observable<String> observable;

	@Before
	public void setup() {
		samples = new DaysOfTheWeekSamples();
		observable = samples.getDayNamesObservable();
	}

	/** Calling toBlocking() on Observable allows synchronous testing of what would normally be asynchronous
	 * operations */
	@Test
	public void whenBlockingCalled_thenEmissionsReceivedOnTheSameThread() {

		//getting the first emission
		BlockingObservable<String> blockingObservable = observable.toBlocking();
		String firstDayOfTheWeek = blockingObservable.first();

		assertEquals("Monday", firstDayOfTheWeek);

		assertEquals("Sunday", blockingObservable.last());
	}

	/** Tests that all emissions received from Observable are same as original data in {@link DaysOfTheWeekSamples} */
	@Test
	public void allEmissionsReceived() {
		Iterator<String> iterator = observable.toBlocking().getIterator();

		List<String> emissions = new ArrayList<>();

		while (iterator.hasNext()) {
			emissions.add(iterator.next());
		}

		assertEquals(samples.getDayNames(), emissions);
	}

}