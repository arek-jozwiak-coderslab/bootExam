package exam.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import exam.Main8;

/**
 * Using http://stefanbirkner.github.io/system-rules/
 * @TODO - remove created files after test
 * @author dell
 *
 */
public class Main8Test {
	
	@Before
	public void setup() throws IOException{
		Path exam = Paths.get("exam.txt");
		Files.deleteIfExists(exam);
		Path examPassed = Paths.get("exam_passed.txt");
		Files.deleteIfExists(examPassed);
	}

	private static final String MAX_POINTS = "20";
	private static final String ROW_SIZE = "2";
	private static final String LOGIN_FAIL = "loginFail";
	private static final String LOGIN_FAIL_POINTS = "2";
	private static final String LOGIN_PASSED = "loginPassed";
	private static final String LOGIN_PASSED_POINTS = "13";
	private static final String EXAM_FILE_NAME = "exam.txt";
	private static final String EXAM_PASSED_FILE_NAME = "exam_passed.txt";

	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void givenRowSize2_whenSystemInData_thenFileExist() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = Main8.class.getDeclaredMethod("create", int.class);
		method.setAccessible(true);
		systemInMock.provideLines(ROW_SIZE, LOGIN_FAIL, LOGIN_FAIL_POINTS, LOGIN_PASSED, LOGIN_PASSED_POINTS);
		method.invoke(null, Integer.parseInt(MAX_POINTS));
		File file = new File(EXAM_FILE_NAME);
		assertTrue(file.exists());
	}

	@Test
	public void givenRowSize2_whenSystemInData_thenFileContainsGivenData()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		Method method = Main8.class.getDeclaredMethod("create", int.class);
		method.setAccessible(true);
		systemInMock.provideLines(ROW_SIZE, LOGIN_FAIL, LOGIN_FAIL_POINTS, LOGIN_PASSED, LOGIN_PASSED_POINTS);
		method.invoke(null, Integer.parseInt(MAX_POINTS));
		File file = new File(EXAM_FILE_NAME);
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		assertThat(lines, containsInAnyOrder(LOGIN_FAIL+ " " +LOGIN_FAIL_POINTS, LOGIN_PASSED+ " " +LOGIN_PASSED_POINTS));

	}
	
	@Test
	public void givenRowSize2_whenSystemInData_thenFilePassedContainsGivenData()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		Method method = Main8.class.getDeclaredMethod("create", int.class);
		method.setAccessible(true);
		systemInMock.provideLines(ROW_SIZE, LOGIN_FAIL, LOGIN_FAIL_POINTS, LOGIN_PASSED, LOGIN_PASSED_POINTS);
		method.invoke(null, Integer.parseInt(MAX_POINTS));
		File file = new File(EXAM_PASSED_FILE_NAME);
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		lines.replaceAll(String::trim);
		assertThat(lines, contains(LOGIN_PASSED+ " " +LOGIN_PASSED_POINTS));

	}

}
