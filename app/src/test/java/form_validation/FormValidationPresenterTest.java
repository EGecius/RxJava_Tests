package form_validation;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Single Responsibility:
 *
 * Tests for {@link FormValidationPresenter}
 */
public class FormValidationPresenterTest {

	public static final String INVALID = "inval";
	public static final String VALID_EMAIL = "valid@mail.com";
	public static final String VALID_PASSWORD = "valid_password";
	FormValidationPresenter presenter;

	@Before
	public void setup() {
		presenter = new FormValidationPresenter();
	}

	//shouldSubmitButtonBeEnabled() TESTS

	@Test
	public void when_calledWithInvalidCredentials_then_returnsFalse() {
		//WHEN
		Boolean enabled = presenter.shouldSubmitButtonBeEnabled(INVALID, INVALID);
		//THEN
		assertFalse(enabled);
	}

	@Test
	public void when_calledValidEmailOnly_then_returnsFalse() {
		//WHEN
		Boolean enabled = presenter.shouldSubmitButtonBeEnabled(VALID_EMAIL, INVALID);
		//THEN
		assertFalse(enabled);
	}

	@Test
	public void when_calledValidPasswordOnly_then_returnsFalse() {
		//WHEN
		Boolean enabled = presenter.shouldSubmitButtonBeEnabled(INVALID, VALID_PASSWORD);
		//THEN
		assertFalse(enabled);
	}

	@Test
	public void when_calledValidCredentials_then_returnsTrue() {
		//WHEN
		Boolean enabled = presenter.shouldSubmitButtonBeEnabled(VALID_EMAIL, VALID_PASSWORD);
		//THEN
		assertTrue(enabled);
	}

}