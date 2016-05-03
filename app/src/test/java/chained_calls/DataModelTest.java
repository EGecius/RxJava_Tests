package chained_calls;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DataModel}
 */
@RunWith (MockitoJUnitRunner.class)
public class DataModelTest {

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String TOKEN = "token";
	DataModel model;

	@Mock RestService restService;
	@Mock AccountController accountController;

	@Mock DataModelInterface.Callback<ProfileResponse> callback;
	private ProfileResponse profileResponseSuccess = new ProfileResponse(true);
	private ProfileResponse profileResponseFailure = new ProfileResponse(false);


	private LoginResponse loginResponseSuccess = new LoginResponse(TOKEN, true);
	private LoginResponse loginResponseFailure = new LoginResponse(null, false);

	TestSubscriber<ProfileResponse> testSubscriber = new TestSubscriber<>();

	private Observable<LoginResponse> loginObservableSuccess = Observable.just(loginResponseSuccess);
	private final Exception loginException = new Exception();
	private Observable<LoginResponse> loginObservableError = Observable.error(loginException);
	private Observable<ProfileResponse> profileObservableSuccess = Observable.just(profileResponseSuccess);
	private Observable<ProfileResponse> profileObservableError = Observable.error(new Exception());

	@Before
	public void setup() {
		model = new DataModel(restService, accountController);
	}
	private void loginResponseSuccess() {
		when(restService.login(EMAIL, PASSWORD)).thenReturn(loginObservableSuccess);
	}

	private void profileResponseSuccess() {
		when(restService.getProfile(TOKEN)).thenReturn(profileObservableSuccess);
	}

	private void loginError() {
		when(restService.login(any(), any())).thenReturn(loginObservableError);
	}

	private void profileError() {
		when(restService.getProfile(any())).thenReturn(profileObservableError);
	}

	private void subscribesToProfileObservable() {
		model.getProfileObservable(EMAIL, PASSWORD).subscribe(testSubscriber);
	}

	private void expectedEmissionsReceived() {
		List<ProfileResponse> emissions = testSubscriber.getOnNextEvents();

		assertThat(emissions.size()).isEqualTo(1);
		assertThat(emissions.get(0)).isEqualTo(profileResponseSuccess);
	}

	private void noEmissionsReceived() {
		List<ProfileResponse> emissions = testSubscriber.getOnNextEvents();
		assertThat(emissions.isEmpty()).isTrue();
	}

	private void errorReceived(final Exception excpected) {
		List<Throwable> errors = testSubscriber.getOnErrorEvents();
		assertThat(errors.size()).isEqualTo(1);
		assertThat(errors.get(0)).isEqualTo(excpected);
	}

	private void userProfileDataSaved() {
		verify(accountController).saveCredentials(EMAIL, TOKEN);
	}

	private void noUserProfileDataSave() {
		verify(accountController, times(0)).saveCredentials(any(), any());
	}



	/* 1) both responses are success */

	@Test
	public void when_both_responsesSuccess_then_expectedEmissionsReceived_and_userProfileDataSaved() {
		//WHEN
		loginResponseSuccess();
		profileResponseSuccess();
		subscribesToProfileObservable();
		//THEN
		expectedEmissionsReceived();
		userProfileDataSaved();
	}

		/* 2 ) both responses are error*/

	@Test
	public void when_bothAreErrors_then_NoEmissionsReceived_and_noUserProfileDataSaved() {
		//WHEN
		loginError();
		profileError();
		subscribesToProfileObservable();
		//THEN
		noEmissionsReceived();
		errorReceived(loginException);
		noUserProfileDataSave();
	}



	// TODO: 03/05/2016 test for error scenarios - all combinations
}