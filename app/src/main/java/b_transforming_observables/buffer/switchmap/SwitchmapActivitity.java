package b_transforming_observables.buffer.switchmap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.egecius.rxjava_tests.R;
import com.jakewharton.rxbinding.widget.RxTextView;


import rx.Observable;
import rx.functions.Func1;

/**
 * Switchmap, when receives a new value, cancels previous observable.
 *
 * For example, if user types in a search query 'j' and before network has returned types 'jo', then no response will
 * be returned for 'j' or all previous queries. It allows avoiding network race conditions
 * */
public class SwitchmapActivitity extends AppCompatActivity {

	Api api = new Api();

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUi();
	}

	private void setupUi() {
		setContentView(R.layout.switchmap_activity);
		EditText editText = (EditText) findViewById(R.id.editText);

		observeWithSwitchmp(editText);
	}

	private void observeWithSwitchmp(final EditText editText) {
		RxTextView.textChanges(editText)
				.switchMap(new Func1<CharSequence, Observable<Item>>() {
					@Override
					public Observable<Item> call(final CharSequence text) {
						Log.i("Eg:SwitchmapActivitity:41", "call text " + text);
						return api.searchItems(text);
					}
				})
				.subscribe(this:: updateUI, this:: showError);
	}

	private void updateUI(final Item item) {
		Log.v("Eg:SwitchmapActivitity:50", "updateUI " + item);
	}

	private void showError(final Throwable throwable) {
		Log.e("Eg:SwitchmapActivitity:56", "showError " + throwable);
	}

}
