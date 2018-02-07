package exam.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import exam.Main4;
import exam.Main5;

public class Main5Test {

	private static final String DECLARED_METHOD = "ball";
	private static final String INPUT_SIZE = "3";

	@Before
	public void setData() {
		InputStream in = new ByteArrayInputStream(INPUT_SIZE.getBytes());
		System.setIn(in);
	}

	@Test
	public void given3_when_calculate_thenEqual113() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = Main5.class.getDeclaredMethod(DECLARED_METHOD);
		method.setAccessible(true);
		double result = (double) method.invoke(null);

		assertEquals(113, result, 1);
	}
}
