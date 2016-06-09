import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import combineLatest.CombineLatestSimpleSampleTest;
import debounce.DebounceSampleTest;
import debounce.IntervalDebounceSampleTest;
import error.ErrorSamplesTest;
import form_validation.FormValidationPresenterTest;
import rx_android.RxHelperTest;
import simple.BlockingObservableTest;
import simple.NonTerminatingObservableTest;
import simple.TerminatingObservableTest;
import zip.ZipSamplesTest;

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
