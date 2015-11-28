package com.egecius.rxjava_tests;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Returns Observable and data used in testing
 */
public class TestSubscriberSamples {

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
}
