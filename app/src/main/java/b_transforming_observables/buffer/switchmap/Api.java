package b_transforming_observables.buffer.switchmap;

import android.os.Handler;

import rx.Observable;
import rx.Subscriber;

final class Api {

	public static final int DELAY_MILLIS = 500;

	Handler handler = new Handler();

	/**
	 * Pretends to do a network request. It will a delay, pretending to be doing network operations
	 * @param charSequence
	 */
	public Observable<Item> searchItems(final CharSequence charSequence) {

		return Observable.create(new Observable.OnSubscribe<Item>() {
			@Override
			public void call(final Subscriber<? super Item> subscriber) {
				handler.postDelayed(() -> {
					subscriber.onNext(new Item(charSequence.toString()));
					subscriber.onCompleted();
				}, DELAY_MILLIS);
			}
		});
	}
}
