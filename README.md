# Java 学习记录

学习之前，先来认（膜）识（拜）一下 ：Java 之父，[詹姆斯·高斯林](https://en.wikipedia.org/wiki/James_Gosling)，俗称高司令。

![Java 之父，詹姆斯·高斯林，俗称高司令](James_Gosling_2008.jpg)

## Java 编程语言

编程语言通常分为 2 种，编译型和解释型：
- 编译型语言如 C、C++，代码是直接编译成机器码执行，但是不同的平台（x86、ARM 等）CPU的指令集不同，因此需要编译出每一种平台的对应机器码。
- 解释型语言如 Python、Ruby 没有这个问题，可以由解释器直接加载源码然后运行，代价是运行效率太低。

而 Java 是将代码编译成一种“字节码”，它类似于抽象的 CPU 指令，然后针对不同平台编写虚拟机（JVM），不同平台的虚拟机负责加载字节码并执行，这样就实现了“一次编写，到处运行”的效果。当然，这是针对 Java 开发者而言。对于虚拟机，需要为每个平台分别开发。

```ascii
┌───────────────────────────┐
│Java EE                    │
│    ┌────────────────────┐ │
│    │Java SE             │ │
│    │    ┌─────────────┐ │ │
│    │    │   Java ME   │ │ │
│    │    └─────────────┘ │ │
│    └────────────────────┘ │
└───────────────────────────┘
```

Java 现在主要有 3 个版本：
- Java EE：Enterprise Edition，企业版
- Java SE：Standard Edition，标准版
- Java ME：Micro Edition

简单来说，Java SE 就是标准版，包含标准的 JVM 和标准库，而 Java EE 是企业版，它只是在 Java SE 的基础上加上了大量的 API 和库，以便方便开发 Web 应用、数据库、消息服务等，Java EE 使用的虚拟机和 Java SE 完全相同。Java ME 就和 Java SE 不同，它是一个针对嵌入式设备的“瘦身版”，Java SE 的标准库无法在 Java ME 上使用，Java ME 的虚拟机也是“瘦身版”。

毫无疑问，Java SE 是整个 Java 平台的核心，而 Java EE 是进一步学习 Web 应用所必须的。我们熟悉的 Spring 等框架都是 Java EE 开源生态系统的一部分。不幸的是，Java ME 从来没有真正流行起来，反而是 Android 开发成为了移动平台的标准之一，因此，没有特殊需求，不建议学习 Java ME。

因此推荐的 Java 学习路线图如下：

1. 首先要学习 Java SE，掌握 Java 语言本身、Java 核心开发技术以及 Java 标准库的使用；
2. 如果继续学习 Java EE，那么 Spring 框架、数据库开发、分布式架构就是需要学习的；
3. 如果要学习大数据开发，那么 Hadoop、Spark、Flink 这些大数据平台就是需要学习的，他们都基于 Java 或 Scala 开发；
4. 如果想要学习移动开发，那么就深入 Android 平台，掌握 Android App 开发。

无论怎么选择，Java SE 的核心技术是基础！

### 常用名词解释

```ascii
  ┌─    ┌──────────────────────────────────┐
  │     │     Compiler, debugger, etc.     │
  │     └──────────────────────────────────┘
 JDK ┌─ ┌──────────────────────────────────┐
  │  │  │                                  │
  │ JRE │      JVM + Runtime Library       │
  │  │  │                                  │
  └─ └─ └──────────────────────────────────┘
        ┌───────┐┌───────┐┌───────┐┌───────┐
        │Windows││ Linux ││ macOS ││others │
        └───────┘└───────┘└───────┘└───────┘
```

- JDK：Java Development Kit
- JRE：Java Runtime Environment
- JVM：Java Virtual Machine

简单地说，JRE 就是运行 Java 字节码的虚拟机。但是，如果只有 Java 源码，要编译成 Java 字节码，就需要 JDK，因为 JDK 除了包含 JRE，还提供了编译器、调试器等开发工具。

---

## 参考文献

1. 廖雪峰. Java 教程. https://liaoxuefeng.com/books/java/introduction/index.html