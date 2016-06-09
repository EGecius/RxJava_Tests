package chained_calls;

import android.support.annotation.Nullable;

final class LoginResponse extends Response {

	public final int token;

	public LoginResponse(final int token) {
		super();
		this.token = token;
	}

	public LoginResponse(@Nullable final String errorMsg) {
		super(errorMsg);
		this.token = -1;
	}

}
