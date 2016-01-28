package activities;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Returns Observable and data used in testing
 */
public class DaysOfTheWeekSamples {

	public List<String> getDayNames() {
		List<String> months = new ArrayList<>(7);

		months.add("Monday");
		months.add("Tuesday");
		months.add("Wednesday");
		months.add("Thursday");
		months.add("Friday");
		months.add("Saturday");
		months.add("Sunday");

		return months;
	}

	public Observable<String> getDayNamesObservable() {
		return Observable.from(getDayNames());
	}

	/** Returns observable which never calls onComplete() or onError() */
	public Observable<String> getNonTerminatingDaysNamesObservable() {

		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {

				List<String> daysOfWeek = getDayNames();

				for (String day : daysOfWeek) {
					subscriber.onNext(day);
				}

				//deliberately not calling onCompete() or onError() at the end
			}
		});
	}

}
