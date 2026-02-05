package com.liufan.learn.generics.learnextends;

import java.lang.reflect.Array;
import java.util.List;

public class MyPairHelper<T> {
    public static int add(MyPair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }
    public static int extendsAdd(MyPair<? extends Number> p) {
        /*
        考察MyPair<? extends Number>的getFirst()方法，实际的方法签名变成了：
            <? extends Number> getFirst() { ... }
        即返回值是Number或Number的子类，因此，可以安全赋值给Number类型的变量：
         */
        Number first = p.getFirst();
        Number last = p.getLast();
        /*
        但是，我们不可预测实际类型，例如，下面的代码是无法通过编译的：
            Integer x = p.getFirst(); // compile error!
        这是因为实际的返回类型可能是Integer，也可能是Double或者其他类型，编译器只能确定类型一定是Number的子类（包括Number类型本身），但具体类型无法确定。

        我们再来考察一下MyPair<T>的set方法：
            p.setFirst(new Integer(first.intValue() + 100)); // compile error!
            p.setLast(new Integer(last.intValue() + 100));   // compile error!
        编译错误发生在p.setFirst()传入的参数是Integer类型。有些童鞋会问了，既然p的定义是Pair<? extends Number>，
        那么setFirst(? extends Number)为什么不能传入Integer？原因还在于擦拭法。如果我们传入的p是MyPair<Double>，
        显然它满足参数定义MyPair<? extends Number>，然而，MyPair<Double>的setFirst()显然无法接受Integer类型。

        ⚠️这就是<? extends Number>通配符的一个重要限制：方法参数签名setFirst(? extends Number)无法传递任何Number的子类型给setFirst(? extends Number)。
        这里唯一的例外是可以给方法参数传入null：
            p.setFirst(null);        // ok, 但是后面会抛出NullPointerException
            p.getFirst().intValue(); // NullPointerException
         */
        return first.intValue() + last.intValue();
    }

    public static int sumOfList(List<? extends Integer> list) {
        /*
        如果我们考察Java标准库的java.util.List<T>接口，它实现的是一个类似“可变数组”的列表，主要功能包括：
            public interface List<T> {
                int size();       // 获取个数
                T get(int index); // 根据索引获取指定元素
                void add(T t);    // 添加一个新元素
                void remove(T t); // 删除一个已有元素
            }

        现在，我们定义一个方法来处理列表的每个元素。
        为什么我们定义的方法参数类型是List<? extends Integer>而不是List<Integer>？
        从方法内部代码看，传入List<? extends Integer>或者List<Integer>是完全一样的，但是，注意到List<? extends Integer>的限制：
        1、允许调用get()方法获取Integer的引用；
        2、不允许调用set(? extends Integer)方法并传入任何Integer的引用（null除外）。

        因此，方法参数类型List<? extends Integer>表明了该方法内部只会读取List的元素，
        不会修改List的元素（因为无法调用add(? extends Integer)、remove(? extends Integer)这些方法。换句话说，
        这是一个对参数List<? extends Integer>进行只读的方法（恶意调用set(null)除外）。
         */
        int sum = 0;
        for (int i=0; i<list.size(); i++) {
            Integer n = list.get(i);
            // list.add(new Integer(23)); // compile error!
            sum = sum + n;
        }
        return sum;
    }

    public static void set(MyPair<Integer> p, Integer first, Integer last) {
        p.setFirst(first);
        p.setLast(last);
    }

    public static void superSet(MyPair<? super Integer> p, Integer first, Integer last) {
        // MyPair<? super Integer>表示，方法参数接受所有泛型类型为Integer或Integer父类的MyPair类型。
        p.setFirst(first);
        p.setLast(last);
        /*
        考察MyPair<? super Integer>的setFirst()方法，它的方法签名实际上是：
            void setFirst(<? super Integer>) { ... }
        可以安全地传入Integer和Integer的父类类型。

        再考察MyPair<? super Integer>的getFirst()方法，实际的方法签名变成了：
            <? super Integer> getFirst() { ... }
        我们无法使用Integer类型来接收getFirst()的返回值，即下面的语句将无法通过编译：
            Integer i = p.getFirst();
        因为如果传入的实际类型是MyPair<Number>，编译器无法将Number类型转型为Integer。
        ⚠️注意：虽然Number是一个抽象类，我们无法直接实例化它。但是，即便Number不是抽象类，这里仍然无法通过编译，
          比如，传入MyPair<Object>类型时，编译器也无法将Object类型转型为Integer。
          唯一可以接收getFirst()方法返回值的是Object类型：
            Object o = p.getFirst();

        因此，使用<? super Integer>通配符表示：
        1、允许调用set(<? super Integer>)方法传入Integer的引用；
        2、不允许调用get()方法获得Integer的引用。唯一例外是可以获取Object的引用：Object o = p.getFirst()。
        换句话说，使用<? super Integer>通配符作为方法参数，表示方法内部代码对于参数只能写，不能读。
         */
    }

    public static boolean isNull(MyPair<?> p) {
        return p.getFirst() == null || p.getLast() == null;
    }

    public static <E> boolean isNewNull(MyPair<E> p) {
        return p.getFirst() == null || p.getLast() == null;
    }

//    T[] createArr() {
//        return new T[5]; // compile error!
//    }
    public T[] createArray(Class<T> cls, int length) {
        return (T[]) Array.newInstance(cls, length);
    }

    @SafeVarargs
    public static <E> E[] asArray(E... objs) {
        /*
        ⚠️如果在方法内部创建了泛型数组，最好不要将它返回给外部使用。
          如果仔细观察，可以发现编译器对所有可变泛型参数都会发出警告，除非确认完全没有问题，才可以用@SafeVarargs消除警告。
          更详细的解释请参考《Effective Java》“Item 32: Combine generics and varargs judiciously”。
         */
        return objs;
    }

    public static <K> K[] pickTwo(K k1, K k2, K k3) {
        /*
        直接调用asArray(T...)似乎没有问题，但是在另一个方法中，我们返回一个泛型数组就会产生ClassCastException，
        原因还是因为擦拭法，在pickTwo()方法内部，编译器无法检测K[]的正确类型，因此返回了Object[]。

        ⚠️如果在方法内部创建了泛型数组，最好不要将它返回给外部使用。
          如果仔细观察，可以发现编译器对所有可变泛型参数都会发出警告 除非确认完全没有问题，才可以用@SafeVarargs消除警告。
          更详细的解释请参考《Effective Java》“Item 32: Combine generics and varargs judiciously”。
         */
        return asArray(k1, k2, k3);
    }
}
