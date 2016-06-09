package c_combining_observables.zip;

import rx.Observable;
import rx.functions.Func2;

/**
 * Sample code for tests related to {@link rx.Observable#zip(Observable, Observable, Func2)}
 */
public class ZipSamples {

	public static final String[] STRINGS = new String[] {" One", " Two", " Three", " Four"};
	public static final Integer[] INTEGERS = new Integer[] {1, 2, 3};

	public Observable<String> getObservable() {
		return Observable.zip(getIntegersObservable(), getStringsObservable(), new Func2<Integer, String, String>() {
			@Override
			public String call(Integer integer, String s) {
				return integer + s;
			}
		});
	}

	private Observable<String> getStringsObservable() {
		return Observable.from(STRINGS);
	}

	private Observable<Integer> getIntegersObservable() {
		return Observable.from(INTEGERS);
	}

}
