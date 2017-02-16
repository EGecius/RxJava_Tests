package z_various.subscribe;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.functions.Action1;

/**
 * Tests for {@link ErrorSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class ErrorSampleTest {

	@InjectMocks ErrorSample sample;

	/**
	 * This test shows that calling subscribe only with action may result in exception thrown from subscribe()
	 * method
	 * */
	@Test
	public void wilNotThrowError() {
		//WHEN
		Observable<String> stringObservable = sample.wilNotThrowError();
		//THEN
		stringObservable.subscribe(new Action1<String>() {
			@Override
			public void call(final String s) {

			}
		});
	}

	/** This test shows that calling subscribe only with action may result in exception thrown from subscribe()
	 * method */
	@Test (expected = Exception.class)
	public void willThrowError() {
		//WHEN
		Observable<String> stringObservable = sample.willThrowError();
		//THEN
		stringObservable.subscribe(new Action1<String>() {
			@Override
			public void call(final String s) {

			}
		});
	}

	/**
	 * However, if you also add error handing action, exception will be passed there instead of potentially crashing
	 *  the application */
	@Test
	public void willThrowError_butErrorActionWillHandleIt() {
		//WHEN
		Observable<String> stringObservable = sample.willThrowError();
		//THEN
		stringObservable.subscribe(new Action1<String>() {
			@Override
			public void call(final String s) {

			}
		}, new Action1<Throwable>() {
			@Override
			public void call(final Throwable throwable) {

			}
		});
	}

}