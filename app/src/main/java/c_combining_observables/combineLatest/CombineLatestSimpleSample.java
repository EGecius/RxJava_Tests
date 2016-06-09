package c_combining_observables.combineLatest;


import rx.Observable;
import rx.functions.Func2;

/**
 * Sample code to write test for {@link rx.Observable#combineLatest(Observable, Observable, Func2)}
 */
public class CombineLatestSimpleSample {


	public Observable<Boolean> getObservable(Observable<String> email, Observable<String> password) {
		return Observable.combineLatest(email, password, new Func2<String, String, Boolean>() {
			@Override
			public Boolean call(String s, String s2) {
				return s.length() > 5 && s2.length() > 5;
			}
		});
	}
}
