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
									  final Callback<LoginResponse> callback) {
		getProfileObservable(email, password)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<LoginResponse>() {
					@Override
					public void onCompleted() {}

					@Override
					public void onError(final Throwable e) {
						callback.onFailure(e);
					}

					@Override
					public void onNext(final LoginResponse profileResponse) {
						callback.onResponse(profileResponse);
					}
				});
	}

	Observable<LoginResponse> getProfileObservable(final String email, final String password) {

		final LoginResponse[] loginResponseLocal = new LoginResponse[1];

		return restService.login(email, password)
				.flatMap(loginResponse -> {
					loginResponseLocal[0] = loginResponse;
					DataModel.this.saveCredentialsIfValid(email, loginResponse.token);
					return restService.getProfile(loginResponse.token);
				})
				.flatMap(profileResponse -> {
					if (isSuccess(profileResponse)) {
						return Observable.just(loginResponseLocal[0]);
					} else {
						throw new RxChainingException(profileResponse);
					}
				});
	}

	private boolean isSuccess(final Response profileResponse) {
		return profileResponse.success;
	}

	private void saveCredentialsIfValid(final String email, final int token) {
		if (email != null && token != -1) {
			accountController.saveCredentials(email, token);
		}
	}
}
