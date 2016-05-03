package chained_calls;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

final class DataModel implements DataModelInterface {

	private final RestService restService;
	private AccountController accountController;

	public DataModel(final RestService restService, final AccountController accountController) {
		this.restService = restService;
		this.accountController = accountController;
	}

	@Override
	public void loginWithProfileFetch(final String email, final String password,
									  final Callback<ProfileResponse> callback) {
		getProfileObservable(email, password)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<ProfileResponse>() {
					@Override
					public void onCompleted() {}

					@Override
					public void onError(final Throwable e) {
						callback.onFailure(e);
					}

					@Override
					public void onNext(final ProfileResponse profileResponse) {
						callback.onResponse(profileResponse);
					}
				});
	}

	Observable<ProfileResponse> getProfileObservable(final String email, final String password) {
		return restService.login(email, password)
				.flatMap(loginResponse -> {
					saveCredentials(email, loginResponse.token);
					return restService.getProfile(loginResponse.token);
				});
	}

	private void saveCredentials(final String email, final String token) {
		accountController.saveCredentials(email, token);
	}
}
