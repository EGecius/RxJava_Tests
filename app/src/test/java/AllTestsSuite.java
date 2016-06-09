import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import a_creating_observables.create.BlockingObservableTest;
import a_creating_observables.create.NonTerminatingObservableTest;
import a_creating_observables.create.TerminatingObservableTest;
import b_transforming_observables.buffer.BufferSamplesTest;
import c_combining_observables.combineLatest.CombineLatestSimpleSampleTest;
import c_combining_observables.zip.ZipSamplesTest;
import c_filtering_observables.debounce.DebounceSampleTest;
import c_filtering_observables.debounce.IntervalDebounceSampleTest;
import e_observable_utility_operators.delay.DelaySamplesTest;
import x_terminating.error.ErrorSamplesTest;
import y_use_cases.form_validation.FormValidationPresenterTest;
import z_various.rx_android.RxHelperTest;

/**
 * Runs all unit tests
 */
@RunWith (Suite.class)
@Suite.SuiteClasses({
		DelaySamplesTest.class,
		BlockingObservableTest.class,
		BufferSamplesTest.class,
		CombineLatestSimpleSampleTest.class,
		DebounceSampleTest.class,
		ErrorSamplesTest.class,
		FormValidationPresenterTest.class,
		IntervalDebounceSampleTest.class,
		NonTerminatingObservableTest.class,
		RxHelperTest.class,
		TerminatingObservableTest.class,
		ZipSamplesTest.class
})
public class AllTestsSuite {
}
