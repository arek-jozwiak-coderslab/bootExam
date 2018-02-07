package exam.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import exam.Main7;

public class Main7Test {
	private static final String DECLARED_METHOD = "countNumbers";

	@Test
	public void given1a23b_whenCalculate_thenEquals5() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String str = "Wynik dodawania 1 i 2 to 3";
		
		Method method = Main7.class.getDeclaredMethod(DECLARED_METHOD, String.class);
		method.setAccessible(true);
		int result = (int) method.invoke(null, str);
		
		assertEquals(6, result);
	}

}
