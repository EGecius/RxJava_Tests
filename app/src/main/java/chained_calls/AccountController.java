package chained_calls;

/**
 * Interface for saving user account data
 */
interface AccountController {

	void saveCredentials(String email, String token);
}
