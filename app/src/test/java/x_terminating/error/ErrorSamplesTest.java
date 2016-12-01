package x_terminating.error;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.exceptions.CompositeException;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Single Responsibility:
 *
 * Tests for {@link ErrorSamples}
 */
public class ErrorSamplesTest {

	ErrorSamples samples;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		samples = new ErrorSamples();
	}

	@Test
	public void when_errorObservableSubscribedTo_then_foundRuntimeExceptionType() {
		//WHEN
		errorObservableSubscribedTo();
		//THEN
		foundRuntimeExceptionType();
	}

	@Test
	public void when_errorObservableSubscribedTo_then_foundRuntimeExceptionInstance() {
		//WHEN
		errorObservableSubscribedTo();
		//THEN
		foundRuntimeExceptionInstance();
	}

	private void foundRuntimeExceptionInstance() {
		testSubscriber.assertError(ErrorSamples.RUNTIME_EXCEPTION);
	}

	private void foundRuntimeExceptionType() {
		testSubscriber.assertError(RuntimeException.class);
	}

	private void errorObservableSubscribedTo() {
		samples.getErrorObservable().subscribe(testSubscriber);
	}

	@Test
	public void when_errorObservableSubscribedTo_then_ourStringFoundInException() {
		//WHEN
		errorObservableSubscribedTo();
		//THEN
		ourStringFoundInException();
	}

	private void ourStringFoundInException() {
		List<Throwable> errorEvents = testSubscriber.getOnErrorEvents();
		RuntimeException exception = (RuntimeException) errorEvents.get(0);
		String msgFound = exception.getMessage();
		assertThat(msgFound).isEqualTo(ErrorSamples.MESSAGE);
	}

	@Test
	public void whenSubscribedTo_getErrorObservableWithRethrowing_then_findsCompositeExceptionWithBothExceptions() {
		//WHEN
		samples.getErrorObservableWithRethrowing().subscribe(testSubscriber);
		//THEN
		findsCompositeExceptionWithBothExceptions();
	}

	private void findsCompositeExceptionWithBothExceptions() {
		//composite exception found
		testSubscriber.assertError(CompositeException.class);
		List<Throwable> onErrorEvents = testSubscriber.getOnErrorEvents();
		assertThat(onErrorEvents.size()).isEqualTo(1);

		CompositeException compositeException = (CompositeException) onErrorEvents.get(0);
		List<Throwable> exceptions = compositeException.getExceptions();

		assertThat(exceptions.size()).isEqualTo(2);
		assertThat(exceptions.get(0)).isEqualTo(ErrorSamples.RUNTIME_EXCEPTION);
		assertThat(exceptions.get(1)).isEqualTo(ErrorSamples.RETHROWN_EXCEPTION);
	}

}