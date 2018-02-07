package exam.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import exam.Main4;

public class Main4Test {

	private static final String DECLARED_METHOD = "returnTab";
	private static final String INPUT_SIZE = "4";
	private static final int SIZE = 4;

	@Before
	public void setData() {
		InputStream in = new ByteArrayInputStream(INPUT_SIZE.getBytes());
		System.setIn(in);
	}

	@Test
	public void given4_whenExecute_thenSizeEquals4() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = Main4.class.getDeclaredMethod(DECLARED_METHOD);
		method.setAccessible(true);
		int[] result = (int[]) method.invoke(null);
		assertEquals(SIZE, result.length);

	}

	@Test
	public void given4_whenExecute_thenArrayNotContain0() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = Main4.class.getDeclaredMethod(DECLARED_METHOD);
		method.setAccessible(true);
		int[] result = (int[]) method.invoke(null);
		System.out.println(Arrays.toString(result));
		Integer[] what = Arrays.stream(result).boxed().toArray(Integer[]::new);
		assertThat(what, not(hasItemInArray(0)));
	}

}
