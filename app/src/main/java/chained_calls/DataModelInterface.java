package chained_calls;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Model in interface in 'Model View Presenter' pattern
 */
interface DataModelInterface {

	interface Callback<T> {

		/** Called when response is received */
		void onResponse(@NonNull T response);

		/** Response has not been received from server */
		void onFailure(@Nullable Throwable t);
	}

	void loginWithProfileFetch(String email, String password, Callback<ProfileResponse> callback);
}
