package chained_calls;

import android.support.annotation.Nullable;

final class LoginResponse {

	@Nullable public final String token;
	public final boolean success;

	public LoginResponse(@Nullable final String token, final boolean success) {
		this.token = token;
		this.success = success;
	}
}
