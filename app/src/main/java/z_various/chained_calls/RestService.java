package z_various.chained_calls;

import rx.Observable;

/**
 * Service that fetches back-end data, much like Retrofit would do it
 */
interface RestService {

	/** Logs user in */
	Observable<LoginResponse> login(String email, String password);

	/** Fetches user's profile data */
	Observable<ProfileResponse> getProfile(int token);
}
