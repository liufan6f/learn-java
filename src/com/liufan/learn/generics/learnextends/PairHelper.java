package com.liufan.learn.generics.learnextends;

public class PairHelper {
    /*
    public static int add(MyPair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }
    */
    public static int add(MyPair<? extends Number> p) {
        /*
        如果我们考察对MyPair<? extends Number>类型调用getFirst()方法，实际的方法签名变成了：
            <? extends Number> getFirst() { ... }
        即返回值是Number或Number的子类，因此，可以安全赋值给Number类型的变量：
         */
        Number first = p.getFirst();
        Number last = p.getLast();
        /*
        然后，我们不可预测实际类型就是Integer，例如，下面的代码是无法通过编译的：
            Integer x = p.getFirst(); // compile error!
        这是因为实际的返回类型可能是Integer，也可能是Double或者其他类型，编译器只能确定类型一定是Number的子类（包括Number类型本身），但具体类型无法确定。
         */

        /*
        我们再来考察一下MyPair<T>的set方法：
            p.setFirst(new Integer(first.intValue() + 100)); // compile error!
            p.setLast(new Integer(last.intValue() + 100));   // compile error!
        译错误发生在p.setFirst()传入的参数是Integer类型。有些童鞋会问了，既然p的定义是Pair<? extends Number>，
        那么setFirst(? extends Number)为什么不能传入Integer？原因还在于擦拭法。如果我们传入的p是MyPair<Double>，
        显然它满足参数定义MyPair<? extends Number>，然而，MyPair<Double>的setFirst()显然无法接受Integer类型。

        ⚠️这就是<? extends Number>通配符的一个重要限制：方法参数签名setFirst(? extends Number)无法传递任何Number的子类型给setFirst(? extends Number)。
        这里唯一的例外是可以给方法参数传入null：
            p.setFirst(null); // ok, 但是后面会抛出NullPointerException
            p.getFirst().intValue(); // NullPointerException
         */
        // TODO extends通配符的作用
        return first.intValue() + last.intValue();
    }
}
