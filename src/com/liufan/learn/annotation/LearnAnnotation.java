package com.liufan.learn.annotation;

import java.lang.reflect.Field;

/**
 * 注解（Annotation）
 */
public class LearnAnnotation {
    public static void learn() {
        /*
        什么是注解（Annotation）？注解是放在Java源码的类、方法、字段、参数前的一种特殊“注释”：
        @Override
        public String toString() {
            return "Hello";
        }
        注释会被编译器直接忽略，注解则可以被编译器打包进入class文件，因此，注解是一种用作标注的“元数据”。

        注解的作用
        从JVM的角度看，注解本身对代码逻辑没有任何影响，如何使用注解完全由工具决定。Java的注解可以分为三类：
        第一类、由编译器使用的注解：
               - @Override，让编译器检查该方法是否正确地实现了覆写；
               - @SuppressWarnings，告诉编译器忽略此处代码产生的警告。
        第二类、由工具处理.class文件使用的注解，比如有些工具会在加载class的时候，对class做动态修改，实现一些特殊的功能。
               这类注解会被编译进入.class文件，但加载结束后并不会存在于内存中。这类注解只被一些底层库使用，一般我们不必自己处理。
        第三类、在程序运行期能够读取的注解，它们在加载后一直存在于JVM中，这也是最常用的注解。例如，一个配置了@PostConstruct的方法
               会在调用构造方法后自动被调用（这是Java代码读取该注解实现的功能，JVM并不会识别该注解）。

        定义一个注解时，还可以定义配置参数。配置参数可以包括：
        1、所有基本类型；
        2、String；
        3、枚举类型；
        4、基本类型、String、Class以及枚举的数组。
        因为配置参数必须是常量，所以，上述限制保证了注解在定义时就已经确定了每个参数的值。注解的配置参数可以有默认值，
        缺少某个配置参数时将使用默认值。此外，大部分注解会有一个名为value的配置参数，对此参数赋值，可以只写常量，
        相当于省略了value参数。如果只写注解，相当于全部使用默认值：
        public class Hello {
            @Check(min=0, max=100, value=55)
            public int n;

            @Check(value=99)
            public int p;

            @Check(99) // @Check(value=99)
            public int x;

            @Check
            public int y;
        }
         */
        System.out.println("注解（Annotation）是Java语言用于工具处理的标注");
    }

    /**
     * 定义注解
     * @see InheritedReport
     */
    @Report()
    @Report(type = 1, level = "method", value = "custom")
    public static void custom() {
        Person p = new Student();
        System.out.println(p.toString());
        /*
        总结一下定义Annotation的步骤：
        第一步、用@interface定义注解
        第二步、添加参数、默认值。把最常用的参数定义为value()，推荐所有参数都尽量设置默认值。
        第三步、用元注解配置注解：其中，必须设置@Target和@Retention，@Retention一般设置为RUNTIME，因为我们自定义的注解通常要求在运行期读取。一般情况下，不必写@Inherited和@Repeatable。
         */
    }

    /**
     * 处理注解
     */
    public static void handle() {
        /*
        Java的注解本身对代码逻辑没有任何影响。根据@Retention的配置：
        1、SOURCE类型的注解在编译期就被丢掉了；
        2、CLASS类型的注解仅保存在class文件中，它们不会被加载进JVM；
        3、RUNTIME类型的注解会被加载进JVM，并且在运行期可以被程序读取。
        如何使用注解完全由工具决定：
        1、SOURCE类型的注解主要由编译器使用，因此我们一般只使用，不编写；
        2、CLASS类型的注解主要由底层工具库使用，涉及到class的加载，一般我们很少用到；
        3、只有RUNTIME类型的注解不但要使用，还经常需要编写。

        因此，我们只需要处理RUNTIME类型的注解。
        因为注解定义后也是一种class，所有的注解都继承自java.lang.annotation.Annotation，因此，读取注解，需要使用反射API。
        Java提供的使用反射API读取Annotation的方法包括：
        1、判断某个注解是否存在于Class、Field、Method或Constructor：
           - Class.isAnnotationPresent(Class)
           - Field.isAnnotationPresent(Class)
           - Method.isAnnotationPresent(Class)
           - Constructor.isAnnotationPresent(Class)
        2、使用反射API读取Annotation：
           - Class.getAnnotation(Class)
           - Field.getAnnotation(Class)
           - Method.getAnnotation(Class)
           - Constructor.getAnnotation(Class)
         */
        Class cls = Person.class;
//        // 判断@InheritedReport是否存在于Person类:
//        if (cls.isAnnotationPresent(InheritedReport.class)) {
//            // 获取Person定义的@InheritedReport
//            InheritedReport r = (InheritedReport) cls.getAnnotation(InheritedReport.class);
//            System.out.println("type: " + r.type() + ", level: " + r.level() + ", value: " + r.value());
//        }

        // 或者直接读取Annotation，如果Annotation不存在，将返回null
        InheritedReport report = (InheritedReport) cls.getAnnotation(InheritedReport.class);
        if (report != null) {
            System.out.println("type: " + report.type() + ", level: " + report.level() + ", value: " + report.value());
        }

        /*
        读取方法、字段和构造方法的Annotation和Class类似。但要读取方法参数的Annotation就比较麻烦一点，因为方法参数本身可以看成一个数组，
        而每个参数又可以定义多个注解，所以，一次获取方法参数的所有注解就必须用一个二维数组来表示。例如，对于以下方法定义的注解：
        public void hello(@NotNull @Range(max=5) String name, @NotNull String prefix) {}

        要读取方法参数的注解，我们先用反射获取Method实例，然后读取方法参数的所有注解：
        // 获取Method实例:
        Method m = ...
        // 获取所有参数的Annotation:
        Annotation[][] annos = m.getParameterAnnotations();
        // 第一个参数（索引为0）的所有Annotation:
        Annotation[] annosOfName = annos[0];
        for (Annotation anno : annosOfName) {
            if (anno instanceof Range r) { // @Range注解
                r.max();
            }
            if (anno instanceof NotNull n) { // @NotNull注解
                //
            }
        }
         */
    }

    /**
     * 使用注解
     */
    public static void use() {
        // 注解如何使用，完全由程序自己决定。例如，JUnit是一个测试框架，它会自动运行所有标记为@Test的方法。
        // 我们来看一个@Range注解，我们希望用它来定义一个String字段的规则：字段长度满足@Range的参数定义：
        Person p = new Person();
        p.name = "Tom";
//        p.name = ""; // 这里会抛出异常
        p.city = "Shenzhen";
        System.out.println(p.name + ", " + p.city);
        try {
            check(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义了注解，本身对程序逻辑没有任何影响。我们必须自己编写代码来使用注解。
     */
    private static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        // 遍历所有Field:
        for (Field field : person.getClass().getFields()) {
            // 获取Field定义的@Range:
            Range range = field.getAnnotation(Range.class);
            // 如果@Range存在:
            if (range != null) {
                // 获取Field的值:
                Object value = field.get(person);
                // 如果值是String:
                if (value instanceof String s) {
                    // 判断值是否满足@Range的min/max:
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }
            }
        }
    }
}