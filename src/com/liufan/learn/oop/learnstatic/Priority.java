package com.liufan.learn.oop.learnstatic;

/**
 * 接口中的静态字段（接口常量）
 * <p>
 * 因为 interface 是一个纯抽象类，所以它不能定义实例字段。但是，interface 是可以有静态字段的，并且静态字段必须为 final 类型。
 * <p>
 * 其实这里的接口常量应用案例并不好，只是用来简单示例它的用法。
 * <p>
 * 著名的《Effective Java》一书就指出，让类实现一个仅包含常量的接口（称为“常量接口”），是一种不良实践，
 * 因为它会毫无必要地将实现细节泄漏到类的导出 API 中。因此，在现代 Java 开发中，通常更推荐以下方式：
 * <ul>
 *     <li>枚举 (Enum)：一组固定的、有逻辑关系的常量，尤其是需要类型安全和方法时。</li>
 *     <li>final 工具类：一组通用的、无状态的工具方法或配置常量。例如 Math.PI。</li>
 *     <li>接口常量：常量与接口功能紧密绑定，且预期有类需要实现该接口以使用这些常量。作为实现契约的一部分，方便实现类直接使用。
 *         例如 ObjectStreamConstants</li>
 * </ul>
 * 总结来说，ObjectStreamConstants 和 ObjectInputStream 是官方库中一个非常纯粹和经典的“接口常量”案例。
 * 它向我们展示了当常量与某个特定领域的实现协议强相关时，这种设计是合理的。但在你的日常开发中，对于更通用的常量，
 * 应优先考虑使用枚举或 final 工具类，这被认为是更现代、更健壮的做法。
 * @see com.liufan.learn.oop.learninterface.Person
 */
public interface Priority {
    // 编译器会自动加上 public static final
    int HIGH = 1; // 因为 interface 的字段只能是 public static final 类型，所以我们可以把这些修饰符都去掉
    int MEDIUM = 2;
    int LOW = 3;
}