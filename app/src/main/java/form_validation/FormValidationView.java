package form_validation;

import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * This is pseudoView which supposedly contains user login form with two EditText (email & password) and submit button
 */
public class FormValidationView {

	EditText emailEditText;
	EditText passwordEditText;
	Button submitButton;

	FormValidationPresenter presenter = new FormValidationPresenter();

	FormValidationView() {
		init();
	}

	private void init() {
		initEmailAndPasswordTextChangeListener();
	}

	private void initEmailAndPasswordTextChangeListener() {

		Observable<CharSequence> emailObservable = RxTextView.textChanges(emailEditText);
		Observable<CharSequence> passwordObservable = RxTextView.textChanges(passwordEditText);

		Observable.combineLatest(emailObservable, passwordObservable, new Func2<CharSequence, CharSequence,
				Boolean>() {
			@Override
			public Boolean call(CharSequence email, CharSequence password) {
				return presenter.shouldSubmitButtonBeEnabled(email, password);
			}
		}).subscribe(new Action1<Boolean>() {
			@Override
			public void call(Boolean aBoolean) {
				submitButton.setEnabled(aBoolean);
			}
		});
	}

}
