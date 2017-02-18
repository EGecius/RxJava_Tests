package a_creating_observables.just;

import rx.Observable;

final class JustSample {

	/** onNext() calls can be called with null too. */
	public Observable<String> getJustEmittingNull() {
		return Observable.just(null);
	}

	/** Generics does not allow using a primitive as a type - does not compile */
//	public Observable<boolean> getJustEmittingBooleanPrimitives() {
//		return Observable.just(false);
//	}

	/** However, generics do allow array of primitives - compiles */
	public Observable<boolean[]> getJustEmittingBooleanPrimitives() {
		boolean[] value = new boolean[1];
		return Observable.just(value);
	}

	/** Very similarly, Observable can return array of Boolean objects */
	public Observable<Boolean[]> getJustEmittingBooleanObjects() {
		Boolean[] value = new Boolean[1];
		return Observable.just(value);
	}


}
