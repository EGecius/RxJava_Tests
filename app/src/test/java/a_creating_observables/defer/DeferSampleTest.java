package a_creating_observables.defer;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Single Responsibility:
 *
 * Tests for {@link DeferSample}
 */
public class DeferSampleTest {

	DeferSample sample;

	@Before
	public void setup() {
		sample = new DeferSample();
	}

	@Test
	public void when_getObservableCalled_then_methodInJustExecutedImmediately() {
		//WHEN
		sample.getJustObservable();
		//THEN
		assertTrue(sample.isJustExecuted());
	}

	@Test
	public void when_getObservableCalledWithDefer_then_methodInJustIsExecutedOnlyOnSubscription() {
		//WHEN
		Observable<Boolean> observableWithDefer = sample.getObservableWithDefer();
		//THEN
		assertFalse(sample.isJustExecuted());

		observableWithDefer.subscribe();

		//now just should be executed
		assertTrue(sample.isJustExecuted());
	}

	

}