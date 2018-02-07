package exam.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import exam.Main6;

public class Main6Test {
	
	private static final String DECLARED_METHOD = "replaceStr";

	@Test
	public void givenCodeMyCode() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		String str = "code my code";
		String forReplace = "code";
		String replacement = "xxx";
		
		Method method = Main6.class.getDeclaredMethod(DECLARED_METHOD, String.class, String.class, String.class);
		method.setAccessible(true);
		String result = (String) method.invoke(null, str, forReplace, replacement);
		
		assertEquals("xxx my xxx", result);

	}

}
