package chained_calls;

/**
 * Pretends to be a response received when fetching user profile data
 */
final class ProfileResponse {

	private final boolean success;

	public ProfileResponse(final boolean success) {
		this.success = success;
	}
}
