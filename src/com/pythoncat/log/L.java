package com.pythoncat.utils;

public class L {

	/**
	 * 如果log信息不全，设置为 true
	 */
	public static final boolean openAll = false;

	public static void e(Object message) {

		System.err.println(getLog(message));
	}

	public static void d(Object message) {

		System.out.println(getLog(message));
	}

	private static String getLog(Object message) {
		StringBuffer str = new StringBuffer();
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		// System.out.println("stack.length==> " + stack.length);
		for (StackTraceElement aStack : stack) {
			String location = aStack.toString();
			boolean addAll;
			if (location.contains("L.e")) {
				continue;
			}
			if (location.startsWith("sun.reflect") || location.startsWith("java.lang.Thread.getStackTrace")
					|| location.startsWith("java.lang.reflect") || location.startsWith("com.intellij")) {
				addAll = true;
			} else {
				addAll = false;
			}
			if (!addAll || openAll) {
				str.append("\tat ");
				str.append(location);
				str.append("\n");
			}
		}
		String substring = str.toString().substring(0, str.toString().indexOf("\n") + 1);
		String log = message.toString() + "\r\n" + substring;
		return log;
	}
}
