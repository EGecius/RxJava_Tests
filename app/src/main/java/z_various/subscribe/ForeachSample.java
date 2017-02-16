package z_various.subscribe;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;

/**
 * Shows how for each work, which only calls subscribe(action)
 */
final class ForeachSample {

	private final ArrayList<String> stringArrayList = new ArrayList<String>();

	public ArrayList<String> getStringArrayList() {
		return stringArrayList;
	}
	void executeForEach() {
		Observable.just("one", "two", "three", "four", "five").forEach(new Action1<String>() {
			@Override
			public void call(final String string) {
				stringArrayList.add(string);
			}
		});
	}

}
