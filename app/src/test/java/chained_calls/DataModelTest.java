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

	private Observable<LoginResponse> loginObservable = Observable.just(loginResponseSuccess);
	private Observable<ProfileResponse> profileObservable = Observable.just(profileResponseSuccess);

	@Before
	public void setup() {
		model = new DataModel(restService, accountController);
	}

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

	private void loginResponseSuccess() {
		when(restService.login(EMAIL, PASSWORD)).thenReturn(loginObservable);
	}

	private void profileResponseSuccess() {
		when(restService.getProfile(TOKEN)).thenReturn(profileObservable);
	}

	private void subscribesToProfileObservable() {
		model.getProfileObservable(EMAIL, PASSWORD).subscribe(testSubscriber);
	}

	private void expectedEmissionsReceived() {
		List<ProfileResponse> emissions = testSubscriber.getOnNextEvents();

		assertThat(emissions.size()).isEqualTo(1);
		assertThat(emissions.get(0)).isEqualTo(profileResponseSuccess);
	}

	private void userProfileDataSaved() {
		verify(accountController).saveCredentials(EMAIL, TOKEN);
	}

	// TODO: 03/05/2016 test for error scenarious - all combinations
}