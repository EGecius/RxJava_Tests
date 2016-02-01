package combineLatest;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;
import rx.subjects.PublishSubject;

/**
 * Single Responsibility:
 *
 * Tests for {@link CombineLatestSimpleSample}
 */
public class CombineLatestSimpleSampleTest {

	public static final String VALID_EMAIL = "test@mail.com";
	public static final String VALID_PASSWORD = "password";
	public static final String INVALID = "inval";
	CombineLatestSimpleSample sample;
	TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
	PublishSubject<String> subjectEmail = PublishSubject.create();
	PublishSubject<String> subjectPassword = PublishSubject.create();

	@Before
	public void setup() {
		sample = new CombineLatestSimpleSample();
	}

	@Test
	public void given_subscribedToCombineLatest_when_onlyOneSourceObservableEmits_then_noEventsReceived() {
		//GIVEN
		subscribedToCombineLatest();
		//WHEN
		onlyOneSourceObservableEmits();
		//THEN
		noEventsReceived();
	}

	private void subscribedToCombineLatest() {
		sample.getObservable(subjectEmail, subjectPassword).subscribe(testSubscriber);
	}

	private void onlyOneSourceObservableEmits() {
		subjectEmail.onNext(VALID_EMAIL);
	}

	private void noEventsReceived() {
		testSubscriber.assertNoValues();
		testSubscriber.assertNoTerminalEvent();
	}

	@Test
	public void given_subscribedToCombineLatest_when_bothSourceObservablesEmitValidData_then_eventTrueReceived() {
		//GIVEN
		subscribedToCombineLatest();
		//WHEN
		bothSourceObservablesEmitValidData();
		//THEN
		eventTrueReceived();
	}

	private void bothSourceObservablesEmitValidData() {
		subjectEmail.onNext(VALID_EMAIL);
		subjectPassword.onNext(VALID_PASSWORD);
	}

	private void eventTrueReceived() {
		testSubscriber.assertValue(true);
	}

	@Test
	public void given_subscribedToCombineLatest_when_onSourceEmitsInvalidData_then_eventFalseReceived() {
		//GIVEN
		subscribedToCombineLatest();
		//WHEN
		onSourceEmitsInvalidData();
		//THEN
		eventFalseReceived();
	}

	private void onSourceEmitsInvalidData() {
		subjectEmail.onNext(INVALID);
		subjectPassword.onNext(VALID_PASSWORD);
	}

	private void eventFalseReceived() {
		testSubscriber.assertValue(false);
	}

}