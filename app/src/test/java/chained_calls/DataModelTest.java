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
	public static final int TOKEN = 123;
	public static final String ERROR_MSG = "error_msg";
	DataModel model;

	@Mock RestService restService;
	@Mock AccountController accountController;

	@Mock DataModelInterface.Callback<ProfileResponse> callback;


	private ProfileResponse profileResponseSuccess = new ProfileResponse(true);
	private ProfileResponse profileResponseFailure = new ProfileResponse(false);

	private final Exception loginBackendException = new Exception("backend");
	private final IllegalAccessException illegalAccessException = new IllegalAccessException();
	private final Exception profileException = new Exception("profile");
	private final RxChainingException rxChainingException = new RxChainingException(profileResponseFailure);


	private LoginResponse loginResponseSuccess = new LoginResponse(TOKEN);
	private LoginResponse loginResponseFailure = new LoginResponse(ERROR_MSG);

	TestSubscriber<LoginResponse> testSubscriber = new TestSubscriber<>();

	private Observable<LoginResponse> loginObservableSuccess = Observable.just(loginResponseSuccess);
	private Observable<LoginResponse> loginObservableResponseFailure = Observable.just(loginResponseFailure);
	private Observable<LoginResponse> loginObservableError = Observable.error(loginBackendException);

	private Observable<ProfileResponse> profileObservableSuccess = Observable.just(profileResponseSuccess);
	private Observable<ProfileResponse> profileObservableResponseFailure = Observable.just(profileResponseFailure);
	private Observable<ProfileResponse> profileObservableError = Observable.error(profileException);

	@Before
	public void setup() {
		model = new DataModel(restService, accountController);
		when(restService.getProfile(/* token */ -1)).thenReturn(Observable.error(illegalAccessException));
	}

	private void loginResponseSuccess() {
		when(restService.login(EMAIL, PASSWORD)).thenReturn(loginObservableSuccess);
	}

	private void loginResponseFailure() {
		when(restService.login(EMAIL, PASSWORD)).thenReturn(loginObservableResponseFailure);
	}

	private void loginError() {
		when(restService.login(any(), any())).thenReturn(loginObservableError);
	}


	private void profileResponseSuccess() {
		when(restService.getProfile(TOKEN)).thenReturn(profileObservableSuccess);
	}

	private void profileResponseFailure() {
		when(restService.getProfile(TOKEN)).thenReturn(profileObservableResponseFailure);
	}

	private void profileError() {
		when(restService.getProfile(any())).thenReturn(profileObservableError);
	}

	private void subscribesToProfileObservable() {
		model.getProfileObservable(EMAIL, PASSWORD).subscribe(testSubscriber);
	}

	private void expectedEmissionsReceived() {
		List<LoginResponse> emissions = testSubscriber.getOnNextEvents();

		assertThat(emissions.size()).isEqualTo(1);
		assertThat(emissions.get(0)).isEqualTo(profileResponseSuccess);
	}

	private void noEmissionsReceived() {
		List<LoginResponse> emissions = testSubscriber.getOnNextEvents();
		assertThat(emissions.isEmpty()).isTrue();
	}

	private void emissionReceived(final Object expectedEmission) {
		List<LoginResponse> emissions = testSubscriber.getOnNextEvents();
		assertThat(emissions.size()).isEqualTo(1);
		assertThat(emissions.get(0)).isEqualTo(expectedEmission);
	}

	private void errorReceived(final Exception expected) {
		List<Throwable> errors = testSubscriber.getOnErrorEvents();
		assertThat(errors.size()).isEqualTo(1);
		assertThat(errors.get(0)).isEqualTo(expected);
	}

	private void noErrorsReceived() {
		 testSubscriber.assertNoErrors();
	}

	private void userProfileDataSaved() {
		verify(accountController).saveCredentials(EMAIL, TOKEN);
	}

	private void noUserProfileDataSave() {
		verify(accountController, times(0)).saveCredentials(any(), any());
	}



	/* 1 ) login is error */

	@Test
	public void when_bothAreErrors_then_NoEmissionsReceived_and_noUserProfileDataSaved() {
		//WHEN
		loginError();
		subscribesToProfileObservable();
		//THEN
		noEmissionsReceived();
		errorReceived(loginBackendException);
		noUserProfileDataSave();
	}


	/* 2 ) login is response failure */

	@Test
	public void when_loginResponseFailure_then_NoEmissionsReceived_and_noUserProfileDataSaved() {
		//WHEN
		loginResponseFailure();
		subscribesToProfileObservable();
		//THEN
		noEmissionsReceived();
		errorReceived(illegalAccessException);
		noUserProfileDataSave();
	}

	/* 3 ) login is success but profile is error  */

	@Test
	public void when_loginSuccess_and_profileError_then_NoEmissionsReceived_and_errorReceived_and_userProfileDataSaved
			() {
		//WHEN
		loginResponseSuccess();
		profileError();
		subscribesToProfileObservable();
		//THEN
		noEmissionsReceived();
		errorReceived(profileException);
		userProfileDataSaved();
	}

	/* 4 ) login is success but profile is response failure  */

	@Test
	public void
	when_loginSuccess_and_profileResponseFailure_then_NoEmissionsReceived_and_errorReceived_and_userProfileDataSaved
			() {
		//WHEN
		loginResponseSuccess();
		profileResponseFailure();
		subscribesToProfileObservable();
		//THEN
		noEmissionsReceived();
		errorReceived(rxChainingException);
		userProfileDataSaved();
	}

	/* 5) both responses are success */

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

}