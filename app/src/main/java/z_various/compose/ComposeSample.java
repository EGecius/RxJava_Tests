package z_various.compose;

import rx.Observable;
import rx.Observable.Transformer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Taken from this blog:
 * http://blog.danlew.net/2015/03/02/dont-break-the-chain/
 * */
final class ComposeSample {

	/** The bad way that makes code difficult to read: */
	<T> Observable<T> applySchedulers(Observable<T> observable) {
		return observable.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	Observable<String> badReadabiltiySample() {

		return applySchedulers(
				Observable.just(1)
				.map(integer -> "one")
		);
	}

	/** The good way way that would  */
	<T> Transformer<T, T> applySchedulers() {
		return new Transformer<T, T>() {
			@Override
			public Observable<T> call(Observable<T> observable) {
				return observable.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread());
			}
		};
	}

	Observable<String> goodReadabiltiySample() {
		return Observable.just(1)
				.map(integer -> "one")
				.compose(applySchedulers());
	}


}
