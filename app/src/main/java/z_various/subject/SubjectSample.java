package z_various.subject;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

final class SubjectSample {

	int counter;

	private final Subject<Integer, Integer> behaviorSubject = BehaviorSubject.create();
	private final Subject<Integer, Integer> publishSubject = PublishSubject.create();

	public Observable<Integer> getBehaviorSubject() {
		return behaviorSubject;
	}

	public Observable<Integer> getPublishSubject() {
		return publishSubject;
	}

	void addEmission() {
		counter++;
		behaviorSubject.onNext(counter);
		publishSubject.onNext(counter);
	}

}
