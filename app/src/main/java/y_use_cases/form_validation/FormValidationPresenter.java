package y_use_cases.form_validation;

/**
 * Pseudo presenter for {@link FormValidationView}
 */
public class FormValidationPresenter {

	public Boolean shouldSubmitButtonBeEnabled(CharSequence email, CharSequence password) {
		return email.length() > 5 && password.length() > 5 ;
	}

}
