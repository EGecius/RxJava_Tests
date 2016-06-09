package z_various.chained_calls;

/**
 * Interface for saving user account data
 */
interface AccountController {

	void saveCredentials(String email, int token);
}
