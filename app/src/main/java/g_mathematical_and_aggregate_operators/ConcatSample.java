package g_mathematical_and_aggregate_operators;

import rx.Observable;

public class ConcatSample {

	public static final String FROM_MEMORY = "from_memory";
	public static final String FROM_DISK = "from_disk";
	public static final String FROM_NETWORK = "from_network";
	public static final String DEFAULT = "default";

	Observable<String> memory = Observable.just(FROM_MEMORY);
	Observable<String> disk = Observable.just(FROM_DISK);
	Observable<String> network = Observable.just(FROM_NETWORK);

	Observable<String> getFirstItemCheckingMultipleSources() {
		return Observable.concat(memory, disk, network).first();
	}


	Observable<String> getAllItemsCheckingMultipleSources() {
		return Observable.concat(memory, disk, network);
	}

	Observable<String> getFirstOrDefaultItemCheckingMultipleSources() {
		Observable<String> empty = Observable.empty();
		return Observable.concat(empty, empty).firstOrDefault(DEFAULT);
	}

	Observable<String> getAllItemsFromConcatWith() {
		return memory.concatWith(disk).concatWith(network);
	}
}
