package com.liufan.learn.generics;

import com.liufan.learn.generics.learnextends.IntPair;
import com.liufan.learn.generics.learnextends.MyPair;
import com.liufan.learn.generics.learnextends.PairHelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型类，Java的泛型是采用擦拭法实现的
 */
public class Generics {
    /**
     * 泛型是一种类似”模板代码“的技术，编写模板代码来适应任意类型
     */
    public static void learn() {
        /*
        什么是泛型（Generics）？
        在讲解什么是泛型之前，我们先观察Java标准库提供的ArrayList，它可以看作“可变长度”的数组，因为用起来比数组更方便。
        实际上ArrayList内部就是一个Object[]数组，配合存储一个当前分配的长度，就可以充当“可变数组”：
            public class ArrayList {
                private Object[] array;
                private int size;
                public void add(Object e) {...}
                public void remove(int index) {...}
                public Object get(int index) {...}
            }
        如果用上述ArrayList存储String类型，会有这么几个缺点：
        1、需要强制转型；
        2、不方便，易出错。
            // 代码必须这么写
            ArrayList list = new ArrayList();
            list.add("Hello");
            // 获取到Object，必须强制转型为String:
            String first = (String) list.get(0);
        很容易出现ClassCastException，因为容易“误转型”：
            list.add(new Integer(123));
            // ERROR: ClassCastException:
            String second = (String) list.get(1);

        要解决上述问题，我们可以为String单独编写一种ArrayList：
            public class StringArrayList {
                private String[] array;
                private int size;
                public void add(String e) {...}
                public void remove(int index) {...}
                public String get(int index) {...}
            }
        这样一来，存入的必须是String，取出的也一定是String，不需要强制转型，因为编译器会强制检查放入的类型：
            StringArrayList list = new StringArrayList();
            list.add("Hello");
            String first = list.get(0);
            // 编译错误: 不允许放入非String类型:
            list.add(new Integer(123));
        然而，新的问题是，如果要存储Integer，还需要为Integer单独编写一种ArrayList，实际上，还需要为其他所有class单独编写一种ArrayList：
        1、LongArrayList
        2、DoubleArrayList
        3、PersonArrayList
        ...

        为了解决新的问题，我们必须把ArrayList变成一种模板：ArrayList<T>，代码如下：
            public class ArrayList<T> {
                private T[] array;
                private int size;
                public void add(T e) {...}
                public void remove(int index) {...}
                public T get(int index) {...}
            }
        T可以是任何class。这样一来，我们就实现了：编写一次模版，可以创建任意类型的ArrayList：
            // 创建可以存储String的ArrayList:
            ArrayList<String> strList = new ArrayList<String>();
            // 创建可以存储Float的ArrayList:
            ArrayList<Float> floatList = new ArrayList<Float>();
            // 创建可以存储Person的ArrayList:
            ArrayList<Person> personList = new ArrayList<Person>();

        因此，泛型就是定义一种模板，例如ArrayList<T>，然后在代码中为用到的类创建对应的ArrayList<类型>：
            ArrayList<String> strList = new ArrayList<String>();
        由编译器针对类型作检查：
            strList.add("hello"); // OK
            String s = strList.get(0); // OK
            strList.add(new Integer(123)); // compile error!
            Integer n = strList.get(0); // compile error!
        这样一来，既实现了编写一次，万能匹配，又通过编译器保证了类型安全：这就是泛型。
         */
        System.out.println("泛型是一种类似”模板代码“的技术，编写模板代码来适应任意类型");
    }

    /**
     * 向上转型
     */
    public static void upcasting() {
        /*
        在Java标准库中的ArrayList<T>实现了List<T>接口，它可以向上转型为List<T>：
            public class ArrayList<T> implements List<T> {
                ...
            }
            List<String> list = new ArrayList<String>(); // 类型ArrayList<T>可以向上转型为List<T>

        ⚠️但是要特别注意：不能把ArrayList<Integer>向上转型为ArrayList<Number>或List<Number>。
          这是为什么呢？假设ArrayList<Integer>可以向上转型为ArrayList<Number>，观察一下代码：
            // 创建ArrayList<Integer>类型：
            ArrayList<Integer> integerList = new ArrayList<Integer>();
            // 添加一个Integer：
            integerList.add(new Integer(123));
            // “向上转型”为ArrayList<Number>：
            ArrayList<Number> numberList = integerList;
            // 添加一个Float，因为Float也是Number：
            numberList.add(new Float(12.34));
            // 从ArrayList<Integer>获取索引为1的元素（即添加的Float）：
            Integer n = integerList.get(1); // ClassCastException!
          我们把一个ArrayList<Integer>转型为ArrayList<Number>类型后，这个ArrayList<Number>就可以接受Float类型，
          因为Float是Number的子类。但是，ArrayList<Number>实际上和ArrayList<Integer>是同一个对象，
          也就是ArrayList<Integer>类型，它不可能接受Float类型， 所以在获取Integer的时候将产生ClassCastException。
          实际上，编译器为了避免这种错误，根本就不允许把ArrayList<Integer>转型为ArrayList<Number>。
          （ArrayList<Integer>和ArrayList<Number>两者完全没有继承关系。）

        用一个图来表示泛型的继承关系，就是T不变时，可以向上转型，T本身不能向上转型：
          List<Integer>     ArrayList<Number>
            ▲                            ▲
            │                            │
            │                            X
            │                            │
        ArrayList<Integer>  ArrayList<Integer>
         */
        System.out.println("注意泛型的继承关系：可以把ArrayList<Integer>向上转型为List<Integer>（T不能变！），" +
                "但不能把ArrayList<Integer>向上转型为ArrayList<Number>（T不能变成父类）");
    }

    /**
     * 使用泛型
     */
    public static void use() {
        // 如果不定义泛型类型时，泛型类型实际上就是Object，把<T>当作Object使用，没有发挥泛型的优势。
        List a = new ArrayList(); // 编译器警告
        a.add("Hello");
        a.add("World");
        String first = (String) a.get(0); // 需要强制转型
        String second = (String) a.get(1);
        System.out.println(first + ", " + second);

        // 当我们定义泛型类型<String>后，List<T>的泛型接口变为强类型List<String>
        List<String> list = new ArrayList<String>(); // 无编译器警告
        list.add("Hello");
        list.add("World");
        String first2 = list.get(0); // 不需要强制转型
        String second2 = list.get(1);
        System.out.println(first2 + ", " + second2);

        // 编译器如果能自动推断出泛型类型，就可以省略后面的泛型类型
        List<Number> n = new ArrayList<>();
        n.add(Integer.valueOf(123));
        n.add(Double.valueOf(12.34));
        Number first3 = n.get(0);
        Number second3 = n.get(1);
        System.out.println(first3 + ", " + second3);
    }

    /**
     * 泛型接口
     */
    public static void tInterface() {
        /*
        除了ArrayList<T>使用了泛型，还可以在接口中使用泛型。例如，Arrays.sort(Object[])可以对任意数组进行排序，
        但待排序的元素必须实现Comparable<T>这个泛型接口：
        public interface Comparable<T> {
            public int compareTo(T o); // 返回负数: 当前实例比参数o小；返回0: 当前实例与参数o相等；返回正数: 当前实例比参数o大
        }
         */
        String[] ss = new String[] { "Orange", "Apple", "Pear" };
        Arrays.sort(ss); // 因为String本身已经实现了Comparable<String>接口
        System.out.println(Arrays.toString(ss));

        Person[] ps = new Person[] {
                new Person("Bob", 61),
                new Person("Alice", 88),
                new Person("Lily", 75),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }

    /**
     * 编写泛型类
     */
    public static void custom() {
        // 编写泛型类比普通类要复杂。通常来说，泛型类一般用在集合类中，例如ArrayList<T>，我们很少需要编写泛型类。
        // 如果我们确实需要编写一个泛型类，那么，应该如何编写它？
        Pair<String> p = new Pair<>("你好", "世界");
        System.out.println(p.getFirst() + ", " + p.getLast());
    }

    /**
     * 静态方法
     */
    public static void staticMethod() {
        Pair<String> p = Pair.create("你好", "世界");
        System.out.println(p.getFirst() + ", " + p.getLast());
    }

    /**
     * 多个泛型类型
     * <p>
     * Java标准库的Map<K, V>就是使用两种泛型类型的例子。它对Key使用一种类型，对Value使用另一种类型。
     */
    public static void customs() {
        NewPair<String, Integer> p = new NewPair<>("你好", 123);
        System.out.println(p.getFirst() + ", " + p.getLast());
    }

    /**
     * 擦拭法（Type Erasure）
     * <p>
     * Java语言的泛型实现方式是擦拭法（Type Erasure）。
     * 所谓擦拭法是指，虚拟机对泛型其实一无所知，所有的工作都是编译器做的。
     */
    public static void typeErasure() {
        /*
        例如，我们编写了一个泛型类Pair<T>，这是编译器看到的代码：
        public class Pair<T> {
            private T first;
            private T last;
            public Pair(T first, T last) {
                this.first = first;
                this.last = last;
            }
            public T getFirst() {
                return first;
            }
            public T getLast() {
                return last;
            }
        }
        而虚拟机根本不知道泛型。这是虚拟机执行的代码：
        public class Pair {
            private Object first;
            private Object last;
            public Pair(Object first, Object last) {
                this.first = first;
                this.last = last;
            }
            public Object getFirst() {
                return first;
            }
            public Object getLast() {
                return last;
            }
        }

        因此，Java使用擦拭法实现泛型，导致了：
        1、编译器把类型<T>视为Object；
        2、编译器根据<T>实现安全的强制转型。

        使用泛型的时候，我们编写的代码也是编译器看到的代码：
        Pair<String> p = new Pair<>("Hello", "world");
        String first = p.getFirst();
        String last = p.getLast();
        而虚拟机执行的代码并没有泛型：
        Pair p = new Pair("Hello", "world");
        String first = (String) p.getFirst();
        String last = (String) p.getLast();
        所以，Java的泛型是由编译器在编译时实行的，编译器内部永远把所有类型<T>视为Object处理，但是，在需要转型的时候，
        编译器会根据<T>的类型自动为我们实行安全地强制转型。

        了解了Java泛型的实现方式——擦拭法，我们就知道了Java泛型的局限：
        1、<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型；
           Pair<int> p = new Pair<>(1, 2); // compile error!
        2、无法取得带泛型的Class：
         */
        Pair<String> p1 = new Pair<>("Hello", "world");
        Pair<Integer> p2 = new Pair<>(1, 2);
        Class c1 = p1.getClass();
        Class c2 = p2.getClass();
        System.out.println(c1 == c2);         // true
        System.out.println(c1 == Pair.class); // true
        /*
           因为<T>是Object，我们对Pair<String>和Pair<Integer>类型获取Class时，获取到的是同一个Class，也就是Pair类的Class。
           换句话说，所有泛型实例，无论<T>的类型是什么，getClass()返回同一个Class实例，因为编译后它们全部都是Pair<Object>。
        3、无法判断带泛型的类型：
           if (p2 instanceof Pair<String>) {} // Compile error
           原因和前面一样，并不存在Pair<String>.class，而是只有唯一的Pair.class
        4、不能实例化<T>类型：
           public class Pair<T> {
               private T first;
               private T last;
               public Pair() {
                   first = new T(); // Compile error，擦拭后实际上变成了：first = new Object();
                   last = new T();  // Compile error，擦拭后实际上变成了：last = new Object();
               }
           }
           这样一来，创建new Pair<String>()和创建new Pair<Integer>()就全部成了Object，显然编译器要阻止这种类型不对的代码。
           要实例化<T>类型，我们必须借助额外的Class<T>参数：
         */
        Pair<String> p3 = new Pair<>(String.class);
        System.out.println(p3.toString());
    }

    /**
     * 不恰当的覆写方法
     */
    public static void errorOverride() {
        /*
        有些时候，一个看似正确定义的方法会无法通过编译。例如：
            public class Pair<T> {
                public boolean equals(T t) {
                    return this == t;
                }
            }

        这是因为，定义的equals(T t)方法实际上会被擦拭成equals(Object t)，而这个方法是继承自Object的，编译器会阻止一个实际上会变成覆写的泛型方法定义。
        换个方法名，避开与Object.equals(Object)的冲突就可以成功编译：
            public class Pair<T> {
                public boolean same(T t) {
                    return this == t;
                }
            }
         */
        System.out.println("擦拭法的又一局限性");
    }

    /**
     * 泛型继承
     */
    public static void extendsPractice() {
        // 使用的时候，因为子类IntPair并没有泛型类型，所以，正常使用即可
        IntPair ip = new IntPair(1, 2);
        System.out.println(ip.toString());

        /*
        前面讲了，我们无法获取Pair<T>的T类型，即给定一个变量Pair<Integer> p，无法从p中获取到Integer类型。
        但是，在父类是泛型类型的情况下，编译器就必须把类型T（对IntPair来说，也就是Integer类型）保存到子类的class文件中，
        不然编译器就不知道IntPair只能存取Integer这种类型。
        在继承了泛型类型的情况下，子类可以获取父类的泛型类型。例如：IntPair可以获取到父类的泛型类型Integer。获取父类的泛型类型代码比较复杂：
         */
        Class<IntPair> clazz = IntPair.class;
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType pt) {
            Type[] types = pt.getActualTypeArguments(); // 可能有多个泛型类型
            Type firstType = types[0]; // 取第一个泛型类型
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass); // Integer
        }
        /*
        因为Java引入了泛型，所以，只用Class来标识类型已经不够了。实际上，Java的类型系统结构如下：
                              ┌────┐
                              │Type│
                              └────┘
                                 ▲
                                 │
           ┌────────────┬────────┴─────────┬───────────────┐
           │            │                  │               │
        ┌─────┐┌─────────────────┐┌────────────────┐┌────────────┐
        │Class││ParameterizedType││GenericArrayType││WildcardType│
        └─────┘└─────────────────┘└────────────────┘└────────────┘
         */
    }

    /**
     * extends通配符
     * <p>
     * 这种使用<code>&lt;? extends Number&gt;</code>的泛型定义称之为上界通配符（Upper Bounds Wildcards），
     * 即把泛型类型<code>&lt;T&gt;</code>的上界限定在Number了。
     * @see PairHelper#add(MyPair)
     */
    public static void upperBoundsWildcards() {
        // 我们前面已经讲到了泛型的继承关系：MyPair<Integer>MyPair<Number>的子类。
        // 我们又写了一个静态方法，它接收的参数类型是MyPair<Number>
        int sum = PairHelper.add(new MyPair<Number>(1, 2));
        System.out.println(sum);
        /*
        传入的类型是MyPair<Number>，实际参数类型是(Integer, Integer)
        既然实际参数是Integer类型，试试传入MyPair<Integer>：
            int sum1 = PairHelper.add(new MyPair<Integer>(1, 2)); // compile error!
        原因很明显，因为MyPair<Integer>不是MyPair<Number>的子类，因此，add(MyPair<Number>)不接受参数类型MyPair<Integer>。
        但是从add()方法的代码可知，传入MyPair<Integer>是完全符合内部代码的类型规范，因为语句：
            Number first = p.getFirst();
            Number last = p.getLast();
        实际类型是Integer，引用类型是Number，没有问题。问题在于方法参数类型定死了只能传入MyPair<Number>。

        有没有办法使得方法参数接受MyPair<Integer>？办法是有的，这就是使用MyPair<? extends Number>使得方法接收所有泛型类型为Number或Number子类的MyPair类型。
        这样一来，给方法传入MyPair<Integer>类型时，它符合参数MyPair<? extends Number>类型。
        这种使用<? extends Number>的泛型定义称之为上界通配符（Upper Bounds Wildcards），即把泛型类型<T>的上界限定在Number了。
        除了可以传入MyPair<Integer>类型，我们还可以传入MyPair<Double>类型，MyPair<BigDecimal>类型等等，因为Double和BigDecimal都是Number的子类。
         */
        MyPair<Integer> p = new MyPair<>(1, 2);
        int sum1 = PairHelper.add(p);
        System.out.println(sum1);
    }
}
