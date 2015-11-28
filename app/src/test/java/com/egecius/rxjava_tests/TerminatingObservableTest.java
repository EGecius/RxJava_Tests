package com.egecius.rxjava_tests;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

/**
 * Tests for for Observable which after onNext calls terminates with either onComplete() or onError()
 */
public class TerminatingObservableTest {

	DaysOfTheWeekSamples samples;
	TestSubscriber<String> subscriber;

	@Before
	public void setup() {
		samples = new DaysOfTheWeekSamples();
		subscriber = new TestSubscriber<>();
		samples.getDayNamesObservable().subscribe(subscriber);
	}

	@Test
	public void observableCompletesSuccessfully() {
		//no  errors have occurred
		subscriber.assertNoErrors();
		// either onError() or onComplete() was called
		subscriber.assertCompleted();
		//since either onError() or onComplete() was called, nothing else to emmit. Subscriber has been unsubscribed
		subscriber.assertUnsubscribed();
	}

	@Test (expected = AssertionError.class)
	public void given_NotCompletedExpected_then_AssertionFails() {
		subscriber.assertNotCompleted();
	}

	@Test
	public void observableEmitsExpectedValues() {
		//THEN it emits the expected values
		subscriber.assertReceivedOnNext(samples.getDayNames());

		//expecting 7 days of the week
		subscriber.assertValueCount(7);
	}

	@Test (expected = AssertionError.class)
	public void given_ShorterListExpected_then_AssertionFails() {

		//removing first element from the list
		List<String> weekDaysModified = samples.getDayNames();
		weekDaysModified.remove(0);

		subscriber.assertReceivedOnNext(weekDaysModified);
	}
	@Test (expected = AssertionError.class)
	public void given_SlightlyModifiedListExpected_then_AssertionFails() {

		//settings first day of the week to Friday
		List<String> weekDaysModified = samples.getDayNames();
		weekDaysModified.remove(0);
		weekDaysModified.add(0, "Friday");

		subscriber.assertReceivedOnNext(weekDaysModified);
	}

	@Test (expected = AssertionError.class)
	public void given_OnlyOneValueIsExpected_then_AssertionFails() {
		subscriber.assertValue("Monday");
	}

	@Test (expected = AssertionError.class)
	public void given_NoValuesExpected_then_AssertionFails() {
		subscriber.assertNoValues();
	}

}