package defer;

import org.junit.Before;
import org.junit.Test;

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
	public void when_getObservableCalledWithDefer_then_methodInJustNotExecutedImmediately() {
		//WHEN
		sample.getObservableWithDefer();
		//THEN
		assertFalse(sample.isJustExecuted());
	}

}