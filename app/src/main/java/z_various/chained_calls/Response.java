package z_various.chained_calls;

import android.support.annotation.Nullable;

abstract class Response {

	public final boolean success;
	@Nullable public final String errorMsg;

	public Response(@Nullable final String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
	}

	public Response() {
		this.success = true;
		errorMsg = null;
	}
}
