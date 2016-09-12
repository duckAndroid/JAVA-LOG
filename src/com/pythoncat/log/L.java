package com.pythoncat.log;

/**
 * Created by pythonCat on 2016/9/12.
 */
public class L {

    /**
     * 如果log信息不全，设置为 true
     */
    public static final boolean openAll = false;

    public static void e(Object message) {
        StringBuffer str = new StringBuffer();
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
//        System.out.println("stack.length==> " + stack.length);
        for (StackTraceElement aStack : stack) {
            String location = aStack.toString();
            boolean addAll;
            if (location.contains("L.e")) {
                continue;
            }
            if (location.startsWith("sun.reflect")
                    || location.startsWith("java.lang.Thread.getStackTrace")
                    || location.startsWith("java.lang.reflect")
                    || location.startsWith("com.intellij")) {
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
        System.err.println(message);
        String substring = str.toString().substring(0, str.toString().indexOf("\n") + 1);
        System.err.print(substring);
    }
}
