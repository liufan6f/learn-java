package com.liufan.learn.coreclass;

/**
 * 枚举类（enum）
 * <p>
 * 使用 enum 定义的枚举类是一种引用类型
 */
public class LearnEnum extends com.liufan.learn.classtype.LearnClass {
    public static void practice() {
        /*
        在 Java 中，我们可以通过 static final 来定义常量，如：
        public class Weekday {
            public static final int SUN = 0;
            public static final int MON = 1;
            public static final int TUE = 2;
            public static final int WED = 3;
            public static final int THU = 4;
            public static final int FRI = 5;
            public static final int SAT = 6;
        }

        使用常量的时候，可以这么引用：
        if (day == Weekday.SAT || day == Weekday.SUN) {
            // enjoy your time
        }

        但是无论是 int 常量还是 String 常量，使用这些常量来表示一组枚举值的时候，有一个严重的问题就是，
        编译器无法检查每个值的合理性。例如：
        if (day == 6 || day == 7) {
            if (tasks == Weekday.MON) {
                // work
            }
        }

        上述代码编译和运行均不会报错，但存在两个问题：
        1、注意到 Weekday 定义的常量范围是 0~6，并不包含 7，编译器无法检查不在枚举中的 int 值；
        2、定义的常量仍可与其他变量比较，但其用途并非是枚举星期值。

        因此，为了让编译器能自动检查某个值在枚举的集合内，并且，不同用途的枚举需要不同的类型来标记，不能混用，
        我们可以使用 enum 来定义枚举类：
         */
        Weekday day = Weekday.SUN;
        // 枚举类可以应用在 switch 语句中。因为枚举类天生具有类型信息和有限个枚举常量，所以比 int、String 类型更适合用在 switch 语句中
        switch (day) {
            case SAT, SUN -> System.out.println("enjoy your time");
            case MON, TUE, WED, THU, FRI -> System.out.println("work");
            // 加上 default 语句，可以在漏写某个枚举常量时自动报错，从而及时发现错误。
            default -> throw new RuntimeException("cannot process " + day);
        }
        /*
        与定义的常量相比，使用 enum 定义枚举有如下好处：
        1、enum 常量本身带有类型信息，即 Weekday.SUN 类型是 Weekday，编译器会自动检查出类型错误；
            int day = 1;
            if (day == Weekday.SUN) { // Compile error
            }
        2、不可能引用到非枚举的值，因为无法通过编译；
        3、不同类型的枚举不能互相比较或者赋值，因为类型不符。
         */
    }

    enum Weekday {
        SUN, MON, TUE, WED, THU, FRI, SAT
    }

    /**
     * enum 比较
     * @see LearnClass#equals()
     */
    public static void equals() {
        /*
        使用 enum 定义的枚举类是一种引用类型，前面我们讲到，引用类型比较，要使用 equals() 方法，如果使用 == 比较，
        它比较的是两个引用类型的变量是否是同一个对象。因此，引用类型比较，要始终使用 equals() 方法，但 enum 类型可以例外。

        这是因为 enum 类型的每个常量在 JVM 中只有一个唯一实例，所以可以直接用 == 比较：
         */
        Weekday day = Weekday.SUN;
        if (day == Weekday.SAT || day == Weekday.SUN) {
            System.out.println("enjoy your time");
        } else {
            System.out.println("work");
        }

        /*
        那么通过 enum 定义的枚举类，和其他的 class 还有什么区别？答案是没有任何区别，只不过它有以下几个特点：
        1、定义的 enum 类型总是继承自 java.lang.Enum，且无法被继承；
        2、只能定义出 enum 的实例，而无法通过 new 操作符创建 enum 的实例；
        3、定义的每个实例都是引用类型的唯一实例；
        4、可以将 enum 类型用于 switch 语句。

        例如，我们定义的 Color 枚举类：
        public enum Color {
            RED, GREEN, BLUE;
        }

        编译器编译出的 class 大概就像这样：
        public final class Color extends Enum { // 继承自Enum，标记为 final class
            // 每个实例均为全局唯一:
            public static final Color RED = new Color();
            public static final Color GREEN = new Color();
            public static final Color BLUE = new Color();
            // private 构造方法，确保外部无法调用 new 操作符:
            private Color() {}
        }

        所以，编译后的 enum 类和普通 class 并没有任何区别。但是我们自己无法按定义普通 class 那样来定义 enum，
        必须使用 enum 关键字，这是 Java 语法规定的。
         */
    }

    /**
     * enum 的常用方法
     */
    public static void method() {
        System.out.println(Weekday.SUN.name());    // 返回常量名
        System.out.println(Weekday.MON.ordinal()); // 返回定义的常量的顺序，从 0 开始计数

        /*
        改变枚举常量定义的顺序就会导致 ordinal() 返回值发生变化。比如改为：
        public enum Weekday {
            MON, TUE, WED, THU, FRI, SAT, SUN;
        }
        如果在代码中编写了类似 if (x.ordinal()==1) 这样的语句，就要保证 enum 的枚举顺序不能变。新增的常量必须放在最后。

        但是，如果不小心修改了枚举的顺序，编译器是无法检查出这种逻辑错误的。要编写健壮的代码，就不要依靠 ordinal() 的返回值。
        因为 enum 本身是 class，所以我们可以定义 private 的构造方法，并且，给每个枚举常量添加字段：
         */
        NewWeekday day = NewWeekday.SUN;
        if (day.dayValue == 6 || day.dayValue == 5) {
            System.out.println("enjoy your time");
        } else {
            System.out.println("work");
        }
        // 这样就无需担心顺序的变化，新增枚举常量时，也需要指定一个 int 值。

        // 默认情况下，对枚举常量调用 toString() 会返回和 name() 一样的字符串。
        // 但是，toString() 可以被覆写，而 name() 则不行。
        System.out.println(day.toString());
    }

    enum NewWeekday {
        MON(0, "星期一"),
        TUE(1, "星期二"),
        WED(2, "星期三"),
        THU(3, "星期四"),
        FRI(4, "星期五"),
        SAT(5, "星期六"),
        SUN(6, "星期日");
        private final int dayValue; // 枚举类的字段也可以是非 final 类型，即可以在运行期修改，但是不推荐这样做！
        private final String chinese;
        private NewWeekday(int dayValue, String chinese) {
            this.dayValue = dayValue;
            this.chinese = chinese;
        }

        @Override
        public String toString() {
            return this.chinese;
        }
    }
}
