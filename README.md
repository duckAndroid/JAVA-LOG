# JAVA-LOG
一个纯ＪＡＶＡ平台的ｌｏｇ输出定位库

* 支持定位,调用简单：
> 调用：` L.e("hello world!"); `
> 效果如下：
```java
hello world!
	at com.pythoncat.Main.main(Main.java:9)
```
* 实现简单,源码如下：
```java
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
```
