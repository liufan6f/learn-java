package com.liufan.learn.reflection;

/*
什么是反射？反射就是Reflection，Java的反射是指程序在运行期可以拿到一个对象的所有信息。
正常情况下，如果我们要调用一个对象的方法，或者访问一个对象的字段，通常会传入对象实例：
String getFullName(Person p) {
    return p.getFirstName() + " " + p.getLastName();
}

但是，如果不能获得Person类，只有一个Object实例，比如这样：
String getFullName(Object obj) {
    return ???
}

怎么办？有童鞋会说：强制转型啊！
String getFullName(Object obj) {
    Person p = (Person) obj;
    return p.getFirstName() + " " + p.getLastName();
}

强制转型的时候，你会发现一个问题：编译上面的代码，仍然需要引用Person类。不然，去掉import语句，你看能不能编译通过？
所以，反射是为了解决在运行期，对某个实例一无所知的情况下，如何调用其方法。
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射（Reflection）
 * <p>
 * 通过 Class 实例获取 class 信息的方法称为反射（Reflection）。
 */
public class Reflection {
    private static final Log log = LogFactory.getLog(Reflection.class);
    /**
     * 类名为 Class 的 class
     * @see com.liufan.learn.oop.OOProgramming#downcasting()
     */
    public static void practiceClass() {
        /*
        除了int等基本类型外，Java的其他类型全部都是class（包括interface）。
        仔细思考，我们可以得出结论：class（包括interface）的本质是数据类型（Type）。无继承关系的数据类型无法赋值：
        Number n = new Double(123.456); // OK
        String s = new Double(123.456); // compile error!

        class是由JVM在执行过程中动态加载的。JVM在第一次读取到一种class类型时，将其加载进内存。每加载一种class，
        JVM就为其创建一个Class类型的实例，并关联起来。注意：这里的Class类型是一个名叫Class的class。它长这样：
        public final class Class {
            private Class() {}
        }

        以String类为例，当JVM加载String类时，它首先读取String.class文件到内存，然后，为String类创建一个Class实例并关联起来：
        Class cls = new Class(String);

        这个Class实例是JVM内部创建的，如果我们查看JDK源码，可以发现Class类的构造方法是private，只有JVM能创建Class实例，我们自己的Java程序是无法创建Class实例的。
        所以，JVM持有的每个Class实例都指向一个数据类型（class或interface）：
        ┌───────────────────────────┐
        │      Class Instance       │────▶ String
        ├───────────────────────────┤
        │name = "java.lang.String"  │
        └───────────────────────────┘
        ┌───────────────────────────┐
        │      Class Instance       │────▶ Random
        ├───────────────────────────┤
        │name = "java.util.Random"  │
        └───────────────────────────┘
        ┌───────────────────────────┐
        │      Class Instance       │────▶ Runnable
        ├───────────────────────────┤
        │name = "java.lang.Runnable"│
        └───────────────────────────┘
        一个Class实例包含了该class的所有完整信息：
        ┌───────────────────────────┐
        │      Class Instance       │────▶ String
        ├───────────────────────────┤
        │name = "java.lang.String"  │
        ├───────────────────────────┤
        │package = "java.lang"      │
        ├───────────────────────────┤
        │super = "java.lang.Object" │
        ├───────────────────────────┤
        │interface = CharSequence...│
        ├───────────────────────────┤
        │field = value[],hash,...   │
        ├───────────────────────────┤
        │method = indexOf()...      │
        └───────────────────────────┘
        由于JVM为每个加载的class创建了对应的Class实例，并在实例中保存了该class的所有信息，包括类名、包名、父类、
        实现的接口、所有方法、字段等，因此，如果获取了某个Class实例，我们就可以通过这个Class实例获取到该实例对应的class的所有信息。
        这种通过Class实例获取class信息的方法称为反射（Reflection）。
         */

        // 获取一个class的Class实例有三个方法：
        Class cls1 = String.class; // 方法一：通过一个class的静态变量class获取
        Class cls2;
        try {
            cls2 = Class.forName("java.lang.String"); // 方法三：通过Class.forName("完整类名")方法获取
        } catch (ClassNotFoundException e) {
            log.info("got exception", e);
            cls2 = new String("Hello").getClass(); // 方法二：通过一个实例的getClass()方法获取
        }
        // 因为Class实例在JVM中是唯一的，所以，上述方法获取的Class实例是同一个实例。可以用==比较两个Class实例：
        System.out.println("cls1 == cls2，" + (cls1 == cls2));

        // ⚠️需要注意一下Class实例比较和instanceof的差别：
        Integer n = Integer.valueOf(123);
        System.out.println("n instanceof Integer，" + (n instanceof Integer)); // true
        System.out.println("n instanceof Number，" + (n instanceof Number));   // true，因为n是Number类型的子类
        Class c1 = n.getClass();
        System.out.println("c1 == Integer.class，" + (c1 == Integer.class)); // true，因为n.getClass()返回Integer.class
        System.out.println("c1 == Number.class，" + (c1 == Number.class));   // false，因为Integer.class != Number.class
        /*
        用 instanceof 不但匹配指定类型，还匹配指定类型的子类。而用==判断class实例可以精确地判断数据类型，但不能作子类型比较。
        通常情况下，我们应该用instanceof判断数据类型，因为面向抽象编程的时候，我们不关心具体的子类型。
        只有在需要精确判断一个类型是不是某个class的时候，我们才使用==判断class实例。
         */

        // 因为反射的目的是为了获得某个实例的信息。因此，当我们拿到某个Object实例时，我们可以通过反射获取该Object的class信息。
        printClassInfo(String[].class); // 数组 String[] 也是一种类，它的类名是[Ljava.lang.String
        printClassInfo(int.class);      // JVM为每一种基本类型如int也创建了Class实例

        // 如果获取到了一个Class实例，我们就可以通过该Class实例来创建对应类型的实例
        Class cls = String.class;
        try {
            String s = (String) cls.newInstance();
            System.out.println(s);
        } catch (Exception e) {
            log.info("got exception", e);
        }
        // 上述代码相当于 new String()，它的局限是：只能调用public的无参数构造方法。
        // 带参数的构造方法，或者非public的构造方法都无法通过Class.newInstance()被调用。
    }
    private static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }

    /**
     * 动态加载
     * <p>
     * JVM总是动态加载class，可以在运行期根据条件来控制加载class。
     */
    public static void dynamicLoading() {
        /*
        JVM在执行Java程序的时候，并不是一次性把所有用到的class全部加载到内存，而是第一次需要用到class时才加载。例如：
        public class Main {
            public static void main(String[] args) {
                if (args.length > 0) {
                    create(args[0]);
                }
            }
            static void create(String name) {
                Person p = new Person(name);
            }
        }

        当执行Main.java时，由于用到了Main，因此，JVM首先会把Main.class加载到内存。然而，并不会加载Person.class，
        除非程序执行到create()方法，JVM发现需要加载Person类时，才会首次加载Person.class。
        如果没有执行create()方法，那么Person.class根本就不会被加载。这就是JVM动态加载class的特性。

        动态加载class的特性对于Java程序非常重要。利用JVM动态加载class的特性，我们才能在运行期根据条件加载不同的实现类。
        例如，Commons Logging总是优先使用Log4j，只有当Log4j不存在时，才使用JDK的logging。利用JVM动态加载特性，大致的实现代码如下：
        // Commons Logging优先使用Log4j:
        LogFactory factory = null;
        if (isClassPresent("org.apache.logging.log4j.Logger")) {
            factory = createLog4j();
        } else {
            factory = createJdkLog();
        }
        boolean isClassPresent(String name) {
            try {
                Class.forName(name);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        这就是为什么我们只需要把Log4j的jar包放到classpath中，Commons Logging就会自动使用Log4j的原因。
         */
        System.out.println("JVM总是动态加载class，可以在运行期根据条件来控制加载class。");
    }

    /**
     * 访问字段
     */
    public static void field() {
        /*
        对任意的一个Object实例，只要我们获取了它的Class，就可以获取它的一切信息。
        我们先看看如何通过Class实例获取字段信息。Class类提供了以下几个方法来获取字段：
        1、Field getField(name)：        根据字段名获取某个public的field（包括父类）
        2、Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
        3、Field[] getFields()：         获取所有public的field（包括父类）
        4、Field[] getDeclaredFields()： 获取当前类的所有field（不包括父类）
         */
        Class stdCls = Student.class;
        try {
            System.out.println(stdCls.getField("score"));
            // 获取继承的public字段name
            System.out.println(stdCls.getField("name"));
            // 获取private字段grade
            System.out.println(stdCls.getDeclaredField("grade"));
        } catch (NoSuchFieldException e) {
            log.info("got exception", e);
        }

        /*
        一个Field对象包含了一个字段的所有信息：
        1、getName()：返回字段名称，例如，"name"；
        2、getType()：返回字段类型，也是一个Class实例，例如，String.class；
        3、getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
         */
        try {
            Field f = String.class.getDeclaredField("value");
            System.out.println(f.getName()); // "value"
            System.out.println(f.getType()); // class [B，表示byte[]类型
            int m = f.getModifiers();
            System.out.println(Modifier.isFinal(m));     // true
            System.out.println(Modifier.isPublic(m));    // false
            System.out.println(Modifier.isProtected(m)); // false
            System.out.println(Modifier.isPrivate(m));   // true
            System.out.println(Modifier.isStatic(m));    // false
        } catch (NoSuchFieldException e) {
            log.info("got exception", e);
        }

        // 获取字段值
        var p = new com.liufan.learn.reflection.field.Person("Tom");
        Class c = p.getClass();
        try {
            Field f = c.getDeclaredField("name");
            f.setAccessible(true); // 调用Field.setAccessible(true)的意思是，别管这个字段是不是public，一律允许访问。
            Object value = f.get(p);
            System.out.println(value);
        } catch (Exception e) {
            log.info("got exception", e);
        }
        /*
        ⚠️执行上述代码，如果不出意外，会得到一个IllegalAccessException，这是因为name被定义为一个private字段，
          正常情况下，外界无法访问Person类的private字段。要修复错误，可以将private改为public，或者，
          在调用Object value = f.get(p);前，先写一句：
          f.setAccessible(true);

        那么这里不禁有个疑问：如果使用反射可以获取private字段的值，那么类的封装还有什么意义？
        答案是正常情况下，我们总是通过p.name来访问Person的name字段，编译器会根据public、protected和private决定是否允许访问字段，
        这样就达到了数据封装的目的。而反射是一种非常规的用法，使用反射，首先代码非常繁琐，其次，它更多地是给工具或者底层框架来使用，
        目的是在不知道目标实例任何信息的情况下，获取特定字段的值。此外，setAccessible(true)可能会失败。
        如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。
        例如，某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。
         */

        // 设置字段值
        System.out.println(p.getName()); // "Tom"
        try {
            Field f = c.getDeclaredField("name");
            f.setAccessible(true); // 调用Field.setAccessible(true)的意思是，别管这个字段是不是public，一律允许访问。
            f.set(p, "Jerry");
            System.out.println(p.getName()); // "Jerry"
        } catch (Exception e) {
            log.info("got exception", e);
        }
    }

    /**
     * 调用方法
     */
    public static void method() {
        /*
        Class类提供了以下几个方法来获取Method：
        1、Method getMethod(name, Class...)：        获取某个public的Method（包括父类）
        2、Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）
        3、Method[] getMethods()：                   获取所有public的Method（包括父类）
        4、Method[] getDeclaredMethods()：           获取当前类的所有Method（不包括父类）
         */
        Class stdCls = com.liufan.learn.reflection.method.Student.class;
        try {
            Method m = stdCls.getMethod("getScore", String.class);
            System.out.println(m);
            /*
            一个Method对象包含一个方法的所有信息：
            1、getName()：返回方法名称，例如："getScore"；
            2、getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
            3、getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
            4、getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
             */
            System.out.println(m.getName());              // "getScore"
            System.out.println(m.getReturnType());        // int
            System.out.println(m.getParameterTypes());
            int mod = m.getModifiers();
            System.out.println(Modifier.isFinal(mod));     // false
            System.out.println(Modifier.isPublic(mod));    // true
            System.out.println(Modifier.isProtected(mod)); // false
            System.out.println(Modifier.isPrivate(mod));   // false
            System.out.println(Modifier.isStatic(mod));    // false

            // 获取继承的public方法getName，无参数
            System.out.println(stdCls.getMethod("getName"));
            // 获取private方法getGrade，参数为int
            System.out.println(stdCls.getDeclaredMethod("getGrade", int.class));

        } catch (NoSuchMethodException e) {
            log.info("got exception", e);
        }

        // 调用方法
        String s = "Hello world";
        System.out.println(s.substring(6)); // "world"
        System.out.println(s.substring(6, 8));        // "wo"
        // 用反射来调用substring方法
        try {
            Method m = String.class.getMethod("substring", int.class);
            System.out.println((String) m.invoke(s, 6)); // "world"
            m = String.class.getMethod("substring", int.class, int.class);
            System.out.println((String) m.invoke(s, 6, 8)); // "wo"
        } catch (Exception e) {
            log.info("got exception", e);
        }

        // 调用静态方法
        try {
            // 获取Integer.parseInt(String)方法，参数为String:
            Method m = Integer.class.getMethod("parseInt", String.class);
            // 调用静态方法时，由于无需指定实例对象，所以invoke方法传入的第一个参数永远为null
            Integer n = (Integer) m.invoke(null, "12345");
            // 打印调用结果:
            System.out.println(n);
        } catch (Exception e) {
            log.info("got exception", e);
        }

        /*
        调用非public方法

        和Field类似，对于非public方法，我们虽然可以通过Class.getDeclaredMethod()获取该方法实例，
        但直接对其调用将得到一个IllegalAccessException。为了调用非public方法，我们通过Method.setAccessible(true)允许其调用。

        setAccessible(true)可能会失败。如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。
        例如，某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。
         */
        var p = new com.liufan.learn.reflection.method.Person();
        try {
            Method m = p.getClass().getDeclaredMethod("setName", String.class);
            m.setAccessible(true);
            m.invoke(p, "Jerry");
            System.out.println(p.getName()); // "Jerry"
        } catch (Exception e) {
            log.info("got exception", e);
        }
    }

    /**
     * 多态
     */
    public static void polymorphic() {
        try {
            // 获取Person的hello方法
            Method m = com.liufan.learn.reflection.method.polymorphic.Person.class.getMethod("hello");
            // 对Student实例调用hello方法
            System.out.println(m.invoke(new com.liufan.learn.reflection.method.polymorphic.Student())); // Student:hello
        } catch (Exception e) {
            log.info("got exception", e);
        }
        /*
        运行上述代码，发现打印出的是Student:hello，因此，使用反射调用方法时，仍然遵循多态原则：即总是调用实际类型的覆写方法（如果存在）。
        上述的反射代码，实际上相当于：
        Person p = new Student();
        p.hello();
         */
    }

    /**
     * 构造方法
     */
    public static void constructorMethod() {
        /*
        如果通过反射来创建新的实例，可以调用Class提供的newInstance()方法：
        Person p = Person.class.newInstance();

        调用Class.newInstance()的局限是，它只能调用该类的public无参数构造方法。如果构造方法带有参数，或者不是public，
        就无法直接通过Class.newInstance()来调用。为了调用任意的构造方法，Java的反射API提供了Constructor对象，
        它包含一个构造方法的所有信息，可以创建一个实例。Constructor对象和Method非常类似，不同之处仅在于它是一个构造方法，并且，调用结果总是返回实例。

        通过Class实例获取Constructor的方法如下：
        1、getConstructor(Class...)：        获取某个public的Constructor；
        2、getDeclaredConstructor(Class...)：获取某个Constructor；
        3、getConstructors()：               获取所有public的Constructor；
        4、getDeclaredConstructors()：       获取所有Constructor。

        ⚠️注意Constructor总是当前类定义的构造方法，和父类无关，因此不存在多态的问题。
          调用非public的Constructor时，必须首先通过setAccessible(true)设置允许访问。setAccessible(true)可能会失败。
          如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。
          例如，某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。
         */
        try {
            // 获取构造方法Integer(int):
            Constructor cons1 = Integer.class.getConstructor(int.class);
            // 调用构造方法:
            Integer n1 = (Integer) cons1.newInstance(123);
            System.out.println(n1);

            // 获取构造方法Integer(String)
            Constructor cons2 = Integer.class.getConstructor(String.class);
            Integer n2 = (Integer) cons2.newInstance("456");
            System.out.println(n2);
        } catch (Exception e) {
            log.info("got exception", e);
        }
    }
}
