import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class OblongRootPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_OblongRoot = "OblongRoot";
	protected static final String CLASS_NAME_OblongRoot = "OblongRoot";
	protected static final String METHOD_NAME_f = "f";
	protected static final String METHOD_NAME_fTailRec = "fTailRec";
	protected static final String METHOD_NAME_fTailRecHelper = "fTailRecHelper";
	protected static final String METHOD_NAME_fMem = "fMem";
	protected static final String METHOD_NAME_fIterative = "fIterative";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 1000)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 30000)
	public void pubTest__f() {
		OblongRootPublicTest.runTest__f();
	}

	@Test(timeout = 10000)
	public void pubTest__fTailRec() {
		OblongRootPublicTest.runTest__fTailRec(123);
	}

	@Test(timeout = 1000)
	public void pubTest__fMem__full() {
		OblongRootPublicTest.runTest__fMem__full(123);
	}

	@Test(timeout = 10000)
	public void pubTest__fMem__part() {
		for (int pass = 0; pass < 5; pass++) {
			OblongRootPublicTest.runTest__fMem__part(123);
		}
	}

	@Test(timeout = 1000)
	public void pubTest__fIterative() {
		OblongRootPublicTest.runTest__fIterative(123);
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION!
	private static Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	private static Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}

	private void check__Intestines() {
		Class<OblongRoot> clazz = OblongRoot.class;
		assertTrue(clazz + " must be public!", Modifier.isPublic(clazz.getModifiers()));
		assertFalse(clazz + " must not be abstract!", Modifier.isAbstract(clazz.getModifiers()));
		assertFalse(clazz + " must not be an annotation!", clazz.isAnnotation());
		assertFalse(clazz + " must not be an enumeration!", clazz.isEnum());
		assertFalse(clazz + " must not be an interface!", clazz.isInterface());
		assertSame(clazz + " must extend a certain super-class!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " must implement a certain number of interfaces!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " must declare a certain number of inner annotations!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " must declare a certain number of inner classes!", 0, getDeclaredClasses(clazz).length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " must declare a certain number of fields!", 1, fields.length);
		for (Field field : fields) {
			assertTrue(field + " - field must be private!", Modifier.isPrivate(field.getModifiers()));
			assertTrue(field + " - field must be final!", Modifier.isFinal(field.getModifiers()));
			assertTrue(field + " - field must have a certain type (must be array)!", fields[0].getType().isArray());
			assertSame(field + " - field must have a certain type (wrong base type)!", int.class, fields[0].getType().getComponentType());
		}
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals(clazz + " must declare a certain number of constructors (possibly including default constructor)!", 1, constructors.length);
		for (Constructor<?> constructor : constructors) {
			assertTrue(constructor + " - constructor must be public!", Modifier.isPublic(constructor.getModifiers()));
			assertEquals(constructor + " - constructor must have a certain number of parameters!", 1, constructor.getParameterTypes().length);
			assertEquals(constructor + " - constructor must have parameters of certain types!", int.class, constructor.getParameterTypes()[0]);
		}
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals(clazz + " must declare a certain number of methods!", 5, methods.length);
		for (Method method : methods) {
			switch (method.getName()) {
				case METHOD_NAME_f:
				case METHOD_NAME_fTailRec:
				case METHOD_NAME_fIterative:
					assertTrue(method + " - method must be public!", Modifier.isPublic(method.getModifiers()));
					assertTrue(method + " - method must be static!", Modifier.isStatic(method.getModifiers()));
					assertEquals(method + " - method must have a certain number of parameters!", 2, method.getParameterTypes().length);
					assertEquals(method + " - method must have parameters of certain types!", int.class, method.getParameterTypes()[0]);
					assertEquals(method + " - method must have parameters of certain types!", RecCheck.class, method.getParameterTypes()[1]);
					break;
				case METHOD_NAME_fTailRecHelper:
					assertTrue(method + " - method must be private!", Modifier.isPrivate(method.getModifiers()));
					assertTrue(method + " - method must be static!", Modifier.isStatic(method.getModifiers()));
					for (Class<?> parameterType : method.getParameterTypes()) {
						assertTrue(method + " - method must have parameters of certain types!", parameterType == int.class || parameterType == RecCheck.class);
					}
					break;
				case METHOD_NAME_fMem:
					assertTrue(method + " - method must be public!", Modifier.isPublic(method.getModifiers()));
					assertFalse(method + " - method must not be static!", Modifier.isStatic(method.getModifiers()));
					assertEquals(method + " - method must have parameters of certain types!", int.class, method.getParameterTypes()[0]);
					assertEquals(method + " - method must have parameters of certain types!", RecCheck.class, method.getParameterTypes()[1]);
					break;
				default:
					fail(method + " - method not expected here!");
					break;
			}
		}
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements RecCheck {
		private final LinkedList<long[]> stackTraceLog = new LinkedList<>();
		private long callCount = 0;

		@Override
		public void checkRec() {
			callCount++;
			long[] stackDepth = new long[5];
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(CLASS_NAME_OblongRoot)) {
					switch (ste.getMethodName()) {
						case METHOD_NAME_f:
							stackDepth[0]++;
							break;
						case METHOD_NAME_fTailRec:
							stackDepth[1]++;
							break;
						case METHOD_NAME_fTailRecHelper:
							stackDepth[2]++;
							break;
						case METHOD_NAME_fMem:
							stackDepth[3]++;
							break;
						case METHOD_NAME_fIterative:
							stackDepth[4]++;
							break;
					}
				}
			}
			stackTraceLog.add(stackDepth);
		}

		private long[] getMinStackTraceDepth() {
			boolean first = true;
			long[] min = new long[5];
			for (long[] log : stackTraceLog) {
				for (int i = 0; i < log.length; i++) {
					min[i] = (first || log[i] < min[i]) ? log[i] : min[i];
				}
				first = false;
			}
			return min;
		}

		private long[] getMaxStackTraceDepth() {
			boolean first = true;
			long[] max = new long[5];
			for (long[] log : stackTraceLog) {
				for (int i = 0; i < log.length; i++) {
					max[i] = (first || log[i] > max[i]) ? log[i] : max[i];
				}
				first = false;
			}
			return max;
		}

		private long getCallCount() {
			return callCount;
		}
	}

	// ========== HELPER ==========
	protected static void runTest__f() {
		long[] recCallExp = {1, 1, 4, 7, 13, 22, 40, 61, 97, 145, 220, 340, 526, 733, 1051, 1537, 2161, 3040, 4312, 6190, 8878, 11773, 15865, 21715, 30067, 40483, 54418};
		long v = 1, vCount = 0;
		for (int n = 1; n <= recCallExp.length; n++) {
			OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
			int actual = OblongRoot.f(n, rc);
			assertEquals("Result is wrong.", v, actual);
			vCount = ++vCount >= 2 * v ? 0 : vCount;
			v += vCount == 0 ? 1 : 0;
			long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
			for (int i = 0; i < minStack.length; i++) {
				if (i == 0) {
					assertEquals("Recursion min-depth (" + METHOD_NAME_f + ") is wrong.", 1, minStack[i]);
					assertEquals("Recursion max-depth (" + METHOD_NAME_f + ") is wrong.", n == 1 ? 1 : n - 1, maxStack[i]);
				} else {
					assertEquals("Recursion min-depth (other than " + METHOD_NAME_f + ") is wrong.", 0, minStack[i]);
					assertEquals("Recursion max-depth (other than " + METHOD_NAME_f + ") is wrong.", 0, maxStack[i]);
				}
			}
			assertEquals("Recursion width is wrong.", recCallExp[n - 1], rc.getCallCount());
		}
	}

	protected static void runTest__fTailRec(int bound) {
		long v = 1, vCount = 0;
		for (int n = 1; n <= bound; n++) {
			OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
			int actual = OblongRoot.fTailRec(n, rc);
			assertEquals("Result is wrong.", v, actual);
			vCount = ++vCount >= 2 * v ? 0 : vCount;
			v += vCount == 0 ? 1 : 0;
			long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
			for (int i = 0; i < minStack.length; i++) {
				if (i == 1) {
					assertEquals("Recursion min-depth (" + METHOD_NAME_fTailRec + ") is wrong.", 1, minStack[i]);
					assertEquals("Recursion max-depth (" + METHOD_NAME_fTailRec + ") is wrong.", 1, maxStack[i]);
				} else if (i == 2) {
					assertEquals("Recursion min-depth (" + METHOD_NAME_fTailRecHelper + ") is wrong.", 0, minStack[i]);
					assertEquals("Recursion max-depth (" + METHOD_NAME_fTailRecHelper + ") is wrong.", n, maxStack[i]);
				} else {
					assertEquals("Recursion min-depth (other than " + METHOD_NAME_fTailRec + " or " + METHOD_NAME_fTailRecHelper + ") is wrong.", 0, minStack[i]);
					assertEquals("Recursion max-depth (other than " + METHOD_NAME_fTailRec + " or " + METHOD_NAME_fTailRecHelper + ") is wrong.", 0, maxStack[i]);
				}
			}
			assertEquals("Recursion width is wrong.", n + 1, rc.getCallCount());
		}
	}

	protected static void runTest__fMem__full(int bound) {
		OblongRoot dp = new OblongRoot(bound + 1);
		for (int pass = 0; pass < 5; pass++) {
			long v = 1, vCount = 0;
			for (int n = 1; n <= bound; n++) {
				OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
				int actual = dp.fMem(n, rc);
				assertEquals("Result is wrong.", v, actual);
				vCount = ++vCount >= 2 * v ? 0 : vCount;
				v += vCount == 0 ? 1 : 0;
				long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
				for (int i = 0; i < minStack.length; i++) {
					if (i == 3) {
						assertEquals("Recursion min-depth (" + METHOD_NAME_fMem + ") is wrong.", 1, minStack[i]);
						assertEquals("Recursion max-depth (" + METHOD_NAME_fMem + ") is wrong.", pass > 0 ? 1 : n < 3 ? 1 : 2, maxStack[i]);
					} else {
						assertEquals("Recursion min-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, minStack[i]);
						assertEquals("Recursion max-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, maxStack[i]);
					}
				}
				assertEquals("Recursion width is wrong.", pass > 0 ? 1 : n < 3 ? 1 : 4, rc.getCallCount());
			}
		}
	}

	protected static void runTest__fMem__part(int bound) {
		long v, vCount;
		int delta = 15 + RND.nextInt(16);
		int max = bound - delta;
		OblongRoot dp = new OblongRoot(max);
		v = 1;
		vCount = 0;
		for (int n = 1; n <= bound; n++) {
			OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
			int actual = dp.fMem(n, rc);
			assertEquals("Result is wrong.", v, actual);
			vCount = ++vCount >= 2 * v ? 0 : vCount;
			v += vCount == 0 ? 1 : 0;
			long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
			for (int i = 0; i < minStack.length; i++) {
				if (i == 3) {
					assertEquals("Recursion min-depth (" + METHOD_NAME_fMem + ") is wrong.", 1, minStack[i]);
					assertEquals("Recursion max-depth (" + METHOD_NAME_fMem + ") is wrong.", n < 3 ? 1 : n <= max ? 2 : n - max + 1, maxStack[i]);
				} else {
					assertEquals("Recursion min-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, minStack[i]);
					assertEquals("Recursion max-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, maxStack[i]);
				}
			}
		}
		for (int pass = 0; pass < 5; pass++) {
			v = 1;
			vCount = 0;
			for (int n = 1; n <= max + 2; n++) {
				OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
				int actual = dp.fMem(n, rc);
				assertEquals("Result is wrong.", v, actual);
				vCount = ++vCount >= 2 * v ? 0 : vCount;
				v += vCount == 0 ? 1 : 0;
				long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
				for (int i = 0; i < minStack.length; i++) {
					if (i == 3) {
						assertEquals("Recursion min-depth (" + METHOD_NAME_fMem + ") is wrong.", 1, minStack[i]);
						assertEquals("Recursion max-depth (" + METHOD_NAME_fMem + ") is wrong.", n == max + 1 ? 2 : n == max + 2 ? 3 : 1, maxStack[i]);
					} else {
						assertEquals("Recursion min-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, minStack[i]);
						assertEquals("Recursion max-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, maxStack[i]);
					}
				}
			}
		}
		v = 1;
		vCount = 0;
		for (int n = 1; n <= bound; n++) {
			OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
			int actual = dp.fMem(n, rc);
			assertEquals("Result is wrong.", v, actual);
			vCount = ++vCount >= 2 * v ? 0 : vCount;
			v += vCount == 0 ? 1 : 0;
			long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
			for (int i = 0; i < minStack.length; i++) {
				if (i == 3) {
					assertEquals("Recursion min-depth (" + METHOD_NAME_fMem + ") is wrong.", 1, minStack[i]);
					assertEquals("Recursion max-depth (" + METHOD_NAME_fMem + ") is wrong.", n <= max ? 1 : n - max + 1, maxStack[i]);
				} else {
					assertEquals("Recursion min-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, minStack[i]);
					assertEquals("Recursion max-depth (other than " + METHOD_NAME_fMem + ") is wrong.", 0, maxStack[i]);
				}
			}
		}
	}

	protected static void runTest__fIterative(int bound) {
		long v = 1, vCount = 0;
		for (int n = 1; n <= bound; n++) {
			OblongRootPublicTest.BigBrother rc = new OblongRootPublicTest.BigBrother();
			int actual = OblongRoot.fIterative(n, rc);
			assertEquals("Result is wrong.", v, actual);
			vCount = ++vCount >= 2 * v ? 0 : vCount;
			v += vCount == 0 ? 1 : 0;
			long[] minStack = rc.getMinStackTraceDepth(), maxStack = rc.getMaxStackTraceDepth();
			for (int i = 0; i < minStack.length; i++) {
				if (i == 4) {
					assertEquals("Recursion min-depth (" + METHOD_NAME_fIterative + ") is wrong.", 1, minStack[i]);
					assertEquals("Recursion max-depth (" + METHOD_NAME_fIterative + ") is wrong.", 1, maxStack[i]);
				} else {
					assertEquals("Recursion min-depth (other than " + METHOD_NAME_fIterative + ") is wrong.", 0, minStack[i]);
					assertEquals("Recursion max-depth (other than " + METHOD_NAME_fIterative + ") is wrong.", 0, maxStack[i]);
				}
			}
			assertEquals("Recursion width is wrong.", 1, rc.getCallCount());
		}
	}
}
