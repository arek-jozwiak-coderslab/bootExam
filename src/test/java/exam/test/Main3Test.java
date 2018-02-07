package exam.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import exam.Main3;

public class Main3Test {

	private static final String DECLARED_METHOD = "div";
	private static final int SIZE = 10;
	private static final int INTERVAL = 20;
	private static final int FIRST_DIVIDER = 2;
	private static final int SECOND_DIVIDER = 3;

	/**
	 * @TODO - do it in more elegant way
	 * @param tab
	 * @return
	 */
	public boolean checkDivisibility(int[] tab) {
		for (int val : tab) {
			if (val % FIRST_DIVIDER != 0 && val % SECOND_DIVIDER == 0) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void givenSize20andInterval10_whenCalculate_check_divisibility_2_and_3() throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = Main3.class.getDeclaredMethod(DECLARED_METHOD, int.class, int.class);
		method.setAccessible(true);
		int[] result = (int[]) method.invoke(null, SIZE, INTERVAL);
		System.out.println("ex3:" +Arrays.toString(result));
		assertTrue(checkDivisibility(result));
	}

	@Test
	public void givenSize20andInterval10_whenCalculate_thenSizeEquals10() throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = Main3.class.getDeclaredMethod(DECLARED_METHOD, int.class, int.class);
		method.setAccessible(true);
		int[] result = (int[]) method.invoke(null, SIZE, INTERVAL);
		System.out.println("ex3:" +Arrays.toString(result));
		assertEquals(SIZE, result.length);
	}

	@Test
	public void givenSize20andInterval10_whenCalculate_thenArrayNotContain0() throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = Main3.class.getDeclaredMethod(DECLARED_METHOD, int.class, int.class);
		method.setAccessible(true);
		int[] result = (int[]) method.invoke(null, SIZE, INTERVAL);
		System.out.println("ex3:" +Arrays.toString(result));
		Integer[] what = Arrays.stream(result).boxed().toArray(Integer[]::new);
		assertThat(what, not(hasItemInArray(0)));
	}

}
