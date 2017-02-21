package z_various.subject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Tests for {@link SubjectSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class SubjectSampleTest {

	@InjectMocks SubjectSample sample;

	TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

	/* BehaviorSubject */

	@Test
	public void given_BehaviorSubject_and_noEmissionsHaveReceivedBySource_then_send_notNotifications() {
		//WHEN
		Observable<Integer> observable = sample.getBehaviorSubject();
		observable.subscribe(testSubscriber);
		//THEN
		testSubscriber.assertNoValues();
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}


	@Test
	public void given_BehaviorSubject_and_noEmissionsHaveReceivedBySource_then_emitsOneValue() {
		//WHEN
		Observable<Integer> observable = sample.getBehaviorSubject();
		observable.subscribe(testSubscriber);
		sample.addEmission();
		//THEN
		testSubscriber.assertValue(1);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void evenWhenSubscribedToAfterEmission_subjectObservableEmitsLastReceivedValue() {
		//WHEN
		Observable<Integer> observable = sample.getBehaviorSubject();
		sample.addEmission();
		observable.subscribe(testSubscriber);
		//THEN
		testSubscriber.assertValue(1);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void evenWhenSubscribedToAfter3Emissions_subjectObservableEmitsLastReceivedValue() {
		//WHEN
		Observable<Integer> observable = sample.getBehaviorSubject();
		//emit 3 values - BehaviorSubject will remember only the last one
		sample.addEmission();
		sample.addEmission();
		sample.addEmission();
		observable.subscribe(testSubscriber);
		//THEN
		testSubscriber.assertValue(3);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

	/* Serialized Behavior subject -exactly same behavior as BehaviorSubject it wraps but thread safe */

	@Test
	public void give_serializedSubject_evenWhenSubscribedToAfterEmission_subjectObservableEmitsLastReceivedValue() {
		//WHEN
		Observable<Integer> observable = sample.getSerializedBehaviorSubject();
		//emit 3 values - BehaviorSubject will remember only the last one
		sample.addEmission();
		sample.addEmission();
		sample.addEmission();
		observable.subscribe(testSubscriber);
		//THEN
		testSubscriber.assertValue(3);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

	/* PublishSubject */

	@Test
	public void given_PublishSubject_and_noEmissionsHaveReceivedBySource_then_send_notNotifications() {
		//WHEN
		Observable<Integer> observable = sample.getPublishSubject();
		observable.subscribe(testSubscriber);
		//THEN
		testSubscriber.assertNoValues();
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void given_PublishSubject_and_noEmissionsHaveReceivedBySource_then_emitsOneValue() {
		//WHEN
		Observable<Integer> observable = sample.getPublishSubject();
		observable.subscribe(testSubscriber);
		sample.addEmission();
		//THEN
		testSubscriber.assertValue(1);
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void given_PublishSubject_whenSubscribedToAfterEmission_nothingIsEmitted() {
		//WHEN
		Observable<Integer> observable = sample.getPublishSubject();
		sample.addEmission();
		observable.subscribe(testSubscriber);
		//THEN
		testSubscriber.assertNoValues();
		testSubscriber.assertNoErrors();
		testSubscriber.assertNotCompleted();
	}

}