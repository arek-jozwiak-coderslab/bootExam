package exam.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import exam.Main2;

public class Main2Test {

	private static final String DECLARED_METHOD = "shorten";
	private static final String SHORTEN_STRING = "Naucz się programować od podstaw";
	private static final String EXPECTED_STRING = "Na";
	private static final int LENGTH = 2;

	@Test
	public void givenStr_whenExecute_thenEqualsNa() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = Main2.class.getDeclaredMethod(DECLARED_METHOD, String.class, int.class);
		method.setAccessible(true);
		String result = (String) method.invoke(null, SHORTEN_STRING, LENGTH);
		assertEquals(EXPECTED_STRING, result);
	}

}
