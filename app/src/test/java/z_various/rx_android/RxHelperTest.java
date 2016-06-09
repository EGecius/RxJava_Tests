package z_various.rx_android;

import android.support.v7.widget.SearchView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * Examples how to test an Observable wrapping a listener
 */
@RunWith (MockitoJUnitRunner.class)
public class RxHelperTest {

	public static final String TEXT_AFTER_CHANGE = "TEXT_AFTER_CHANGE";
	public static final String TEXT_SUBMITTED = "TEXT_SUBMITTED";

	@Mock SearchView searchView;
	private TestSubscriber<SearchViewEvent> subscriber = new TestSubscriber<>();
	RxHelper helper = new RxHelper();

	@Test
	public void when_getObservableCalled_then_onQueryTextChangeEmitsCorrectly() {

		//GIVEN
		searchViewWillHaveQueryTextChanged();

		//WHEN
		helper.getObservable(searchView).subscribe(subscriber);

		//THEN
		List<SearchViewEvent> eventsReceived = subscriber.getOnNextEvents();
		SearchViewEvent event = eventsReceived.get(0);

		assertEquals(1, eventsReceived.size());
		assertEquals(TEXT_AFTER_CHANGE, event.text);
		assertTrue(event.isTextChanged);
		assertFalse(event.isSubmitted);
	}

	private void searchViewWillHaveQueryTextChanged() {
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				SearchView.OnQueryTextListener listener = (SearchView.OnQueryTextListener) invocation.getArguments()[0];
				listener.onQueryTextChange(TEXT_AFTER_CHANGE);
				return null;
			}
		}).when(searchView).setOnQueryTextListener(any());
	}

	@Test
	public void when_getObservableCalled_then_onQueryTextSubmitEmitsCorrectly() {

		//GIVEN
		searchViewWillHaveQuerySubmitted();

		//WHEN
		helper.getObservable(searchView).subscribe(subscriber);

		//THEN
		List<SearchViewEvent> eventsReceived = subscriber.getOnNextEvents();
		SearchViewEvent event = eventsReceived.get(0);

		assertEquals(1, eventsReceived.size());
		assertEquals(event.text, TEXT_SUBMITTED);
		assertTrue(event.isSubmitted);
		assertFalse(event.isTextChanged);
	}

	private void searchViewWillHaveQuerySubmitted() {
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				SearchView.OnQueryTextListener listener = (SearchView.OnQueryTextListener) invocation.getArguments()[0];
				listener.onQueryTextSubmit(TEXT_SUBMITTED);
				return null;
			}
		}).when(searchView).setOnQueryTextListener(any());
	}

}