package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.egecius.rxjava_tests.R;

import debounce.IntervalDebounceSample;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		subscribeToIntervalDebounce();
	}

	IntervalDebounceSample sample = new IntervalDebounceSample();

	private void subscribeToIntervalDebounce() {
		Log.v("Eg:MainActivity:25", "subscribeToIntervalDebounce");

		sample.getObservable().subscribe(new Subscriber<Long>() {
			@Override
			public void onCompleted() {
				Log.d("Eg:MainActivity:27", "onCompleted ");
			}

			@Override
			public void onError(Throwable e) {
				Log.e("Eg:MainActivity:33", "onError ");
			}

			@Override
			public void onNext(Long aLong) {
				Log.i("Eg:MainActivity:38", "onNext aLong " + aLong);
			}
		});
	}
}
