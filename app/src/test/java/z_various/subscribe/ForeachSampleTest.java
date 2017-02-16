package z_various.subscribe;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ForeachSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class ForeachSampleTest {

	@InjectMocks ForeachSample sample;

	@Test
	public void showThatForEachWorksSameAsSubscribe() {
		//WHEN
		assertThat(sample.getStringArrayList().isEmpty()).isTrue();
		//THEN
		sample.executeForEach();
		ArrayList<String> list = sample.getStringArrayList();
		assertThat(list.size()).isEqualTo(5);
	}

}