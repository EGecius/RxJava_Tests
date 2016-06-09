package z_various.rx_android;

import android.support.v7.widget.SearchView;

import rx.Observable;
import rx.Subscriber;

/**
 * Single Responsibility:
 *
 * RxJava helper for {@link SearchView}
 */
class RxHelper {

	/** Provides Rx Observer for SearchView events, which are either change of text or query submission  */
	Observable<SearchViewEvent> getObservable(SearchView view) {
		return Observable.create(new Observable.OnSubscribe<SearchViewEvent>() {

			String previousText = "";

			@Override
			public void call(Subscriber<? super SearchViewEvent> subscriber) {
				view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
					@Override
					public boolean onQueryTextSubmit(String query) {
						subscriber.onNext(new SearchViewEvent(/* isSubmitted */ true, /*isTextChanged*/ false, query));
						return true;
					}

					@Override
					public boolean onQueryTextChange(String newText) {
						if (!previousText.equals(newText)) {
							previousText = newText;
							subscriber.onNext(new SearchViewEvent(/* isSubmitted */ false, /*isTextChanged*/ true, newText));
						}
						return true;
					}
				});
			}
		});
	}

}