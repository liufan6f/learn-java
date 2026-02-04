package com.liufan.learn.annotation;

/*
3、@Repeatable，定义Annotation是否可重复，这个注解应用不是特别广泛。经过@Repeatable修饰后，在某个类型声明处，就可以添加多个@Report注解。
4、@Inherited，使用@Inherited定义子类是否可继承父类定义的Annotation。@Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效，
   并且仅针对class的继承，对interface的继承无效。
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解
 * <p>
 * Java语言使用@interface语法来定义注解，注解的参数类似无参数方法，可以用default设定一个默认值（强烈推荐）。最常用的参数应当命名为value。
 * <p>
 * 有一些注解可以修饰其他注解，这些注解就称为元注解（meta annotation）。Java标准库已经定义了一些元注解，我们只需要使用元注解，通常不需要自己去编写元注解：
 * <ol>
 *     <li><code>@Target</code>，最常用的元注解，使用@Target可以定义Annotation能够被应用于源码的哪些位置
 *         <ul>
 *             <li><code>ElementType.TYPE</code>：类或接口</li>
 *             <li><code>ElementType.FIELD</code>：字段</li>
 *             <li><code>ElementType.METHOD</code>：方法</li>
 *             <li><code>ElementType.CONSTRUCTOR</code>：构造方法</li>
 *             <li><code>ElementType.PARAMETER</code>：方法参数</li>
 *         </ul>
 *     </li>
 *     <li><code>@Retention</code>，定义了Annotation的生命周期
 *         <ul>
 *             <li><code>RetentionPolicy.SOURCE</code>：仅编译期</li>
 *             <li><code>RetentionPolicy.CLASS</code>：仅class文件</li>
 *             <li><code>RetentionPolicy.RUNTIME</code>：运行期</li>
 *         </ul>
 *     </li>
 *     <li><code>@Repeatable</code>，定义Annotation是否可重复，这个注解应用不是特别广泛。经过@Repeatable修饰后，
 *     在某个类型声明处，就可以添加多个@Report注解。</li>
 *     <li>{@linkplain InheritedReport @Inherited}，使用@Inherited定义子类是否可继承父类定义的Annotation。@Inherited仅
 *     针对@Target(ElementType.TYPE)类型的annotation有效，并且仅针对class的继承，对interface的继承无效。</li>
 * </ol>
 * @see InheritedReport
 */
@Repeatable(Reports.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Report {
    int type() default 0;
    String level() default "info";
    String value() default "";
}

/*
实际上@Target定义的value是ElementType[]数组，只有一个元素时，可以省略数组的写法：
@Target({
    ElementType.METHOD,
    ElementType.FIELD
})
public @interface Report {
    ...
}
 */