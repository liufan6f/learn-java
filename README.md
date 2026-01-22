# learn-java

学习之前，先来认（膜）识（拜）一下 ：Java 之父，[詹姆斯·高斯林](https://en.wikipedia.org/wiki/James_Gosling)，俗称高司令。

![Java 之父，詹姆斯·高斯林，俗称高司令](James_Gosling_2008.jpg)

## Java 编程语言

编程语言通常分为 2 种，编译型和解释型：
- 编译型语言如 C、C++，代码是直接编译成机器码执行，但是不同的平台（x86、ARM 等）CPU的指令集不同，因此需要编译出每一种平台的对应机器码。
- 解释型语言如 Python、Ruby 没有这个问题，可以由解释器直接加载源码然后运行，代价是运行效率太低。

而 Java 介于编译型语言和解释型语言之间。它是将代码编译成一种“字节码”，类似于抽象的 CPU 指令，然后针对不同平台编写虚拟机，不同平台的虚拟机负责加载字节码并执行，这样就实现了“一次编写，到处运行”的效果。当然，这是对于 Java 开发者而言。对于虚拟机，需要为每个平台分别开发。

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
- Java EE：Enterprise Edition
- Java SE：Standard Edition
- Java ME：Micro Edition

简单来说，Java SE 就是标准版，包含标准的 JVM 和标准库；而 Java EE 是企业版，它只是在 Java SE 的基础上加上了大量的 API 和库，以便方便开发 Web 应用、数据库、消息服务等。Java EE 使用的虚拟机和 Java SE 完全相同。Java ME 就和 Java SE 不同，它是一个针对嵌入式设备的“瘦身版”，Java SE 的标准库无法在 Java ME 上使用，Java ME 的虚拟机也是“瘦身版”。

毫无疑问，Java SE 是整个 Java 平台的核心，而 Java EE 是进一步学习 Web 应用所必须的。我们熟悉的 Spring 等框架都是 Java EE 开源生态系统的一部分。不幸的是，Java ME 从来没有真正流行起来，反而是 Android 开发成为了移动平台的标准之一，因此，没有特殊需求，不建议学习 Java ME。

因此推荐的 Java 学习路线图如下：

1. 首先要学习 Java SE，掌握 Java 语言本身、Java 核心开发技术以及 Java 标准库的使用；
2. 如果继续学习 Java EE，那么 Spring 框架、数据库开发、分布式架构就是需要学习的；
3. 如果要学习大数据开发，那么 Hadoop、Spark、Flink 这些大数据平台就是需要学习的，他们都基于 Java 或 Scala 开发；
4. 如果想要学习移动开发，那么就深入 Android 平台，掌握 Android App 开发。

无论怎么选择，Java SE 的核心技术是基础！

## Java 开发环境
### 安装 JDK

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

Java 开发中，会经常与这些名词打交道。简单的说，JRE 就是运行 Java 字节码的虚拟机。但是，如果只有 Java 源码，要编译成 Java 字节码，就需要 JDK，因为 JDK 除了包含 JRE，还提供了编译器、调试器等开发工具。

因为 Java 程序必须运行在 JVM 之上，所以，我们第一件事情就是安装 JDK。从 [Oracle 官网](https://www.oracle.com/java/technologies/downloads/) 下载最新的稳定版JDK。选择合适的操作系统与安装包，找到 Java SE 25 的下载链接 Download，下载安装即可。Windows 优先选 x64 MSI Installer，Linux 和 macOS 要根据自己电脑的 CPU 是 ARM 还是 x86 来选择合适的安装包。

#### 设置环境变量

安装完 JDK 后，需要设置一个 `JAVA_HOME` 的环境变量，它指向 JDK 的安装目录。在 Windows 下，它是安装目录，类似：

```plain
C:\Program Files\Java\jdk-25
```

在 macOS 下，它在 `~/.bash_profile` 或 `~/.zprofile` 里，它是：

```plain
export JAVA_HOME=`/usr/libexec/java_home -v 25`
```

然后，把 `JAVA_HOME` 的 `bin` 目录附加到系统环境变量 `PATH` 上。在 Windows 下，它长这样：

```plain
Path=%JAVA_HOME%\bin;<现有的其他路径>
```

在 macOS 下，它在 `~/.bash_profile` 或 `~/.zprofile` 里，长这样：

```plain
export PATH=$JAVA_HOME/bin:$PATH
```

把 `JAVA_HOME` 的 `bin` 目录添加到 `PATH` 中是为了在任意文件夹下都可以运行 `java`。打开终端窗口，输入命令 `java -version`，如果一切正常，则代表设置成功。

在 `JAVA_HOME` 的 bin 目录下找到很多可执行文件：
- java：这个可执行程序其实就是 JVM，运行 Java 程序，就是启动 JVM，然后让 JVM 执行指定的编译后的代码；
- javac：这是 Java 的编译器，它用于把 Java 源码文件（以 `.java` 后缀结尾）编译为 Java 字节码文件（以 `.class` 后缀结尾）；
- jar：用于把一组 `.class` 文件打包成一个 `.jar` 文件，便于发布；
- javadoc：用于从 Java 源码中自动提取注释并生成文档；
- jdb：Java 调试器，用于开发阶段的运行调试。

### 运行 Java 程序

```ascii
┌──────────────────┐
│    Hello.java    │◀── source code
└──────────────────┘
          │————————————————————————┐
          │ $ javac Hello.java     │ compile
          │————————————————————————┘
          ▼
┌──────────────────┐
│   Hello.class    │◀── byte code
└──────────────────┘
          │————————————————————————┐
          │ $ java Hello           │ execute
          │————————————————————————┘
          ▼
┌──────────────────┐
│    Run on JVM    │
└──────────────────┘
```

Java 源码本质上是一个文本文件，我们需要先用 `javac` 把 `Hello.java` 编译成字节码文件 `Hello.class`，然后，用 `java` 命令执行这个字节码文件。

⚠️注意不是 `java Hello.class`，而是 `java Hello`。给虚拟机传递的参数 `Hello` 是我们定义的类名，虚拟机自动查找对应的 class 文件并执行。

有一些童鞋可能知道，直接运行 `java Hello.java` 也是可以的。这是从 Java 11 新增的一个功能，它可以直接运行一个单文件源码！需要注意的是，在实际项目中，单个不依赖第三方库的 Java 源码是非常罕见的，所以，绝大多数情况下，我们无法直接运行一个 Java 源码文件，原因是它需要依赖其他的库。

### 使用 IDE

IDE 是集成开发环境：Integrated Development Environment 的缩写。

使用 IDE 的好处在于，可以把编写代码、组织项目、编译、运行、调试等放到一个环境中运行，能极大地提高开发效率。

目前，流行的用于 Java 开发的 IDE 有：
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  
  由JetBrains公司开发的一个功能强大的IDE，分为免费版和商用付费版。

- [Eclipse](https://www.eclipse.org/downloads/packages/)

  由 IBM 开发并捐赠给开源社区的一个 IDE，也是目前应用最广泛的 IDE。Eclipse 的特点是它本身是 Java 开发的，并且基于插件结构，即使是对 Java 开发的支持也是通过插件 JDT 实现的。

  [Eclipse 设置参考](https://liaoxuefeng.com/books/java/quick-start/dev-env/ide/index.html)

- NetBeans
  
  最早由 SUN 公司开发的开源 IDE，由于使用人数较少，目前已不再流行。
 
## Java 规范
### 命名规范

Java 的命名规范：
- 包名：所有字母都小写，多个单词之间用 `.` 分隔，例如：`com.example.hello`；
- 类名：采用大驼峰命名法，每个单词的首字母大写，例如：`HelloWorld`；同时注意不要和 `java.lang` 包的类重名，即自己的类不要使用这些名字：`String、System、Runtime`等，也不要和 JDK 常用类重名：`java.util.List、java.text.Format、java.math.BigInteger` 等。
  - 好的类名：
    - Hello
    - NoteBook
    - VRPlayer
  - 不好的类名：
    - hello
    - Good123
    - Note_Book
    - _World
    - String，要避免与 `java.lang` 库类重名
- 变量名、方法名：采用小驼峰命名法，第一个单词的首字母小写，后续单词的首字母大写，例如：`helloWorld()`；
- 常量名：所有字母都大写，多个单词之间用下划线分隔，例如：`MAX_VALUE`。
- 项目名：Java 并没有对项目名做强制要求，但是通常根据团队习惯或 IDE 的约定来决定。
  1. 所有字母都小写，多个单词之间用中划线分隔，例如：`learn-java`。**（推荐，跨平台友好）**
  2. 有些团队会用 PascalCase（首字母大写）命名项目，例如：`LearnJava`。这种多见于桌面应用或需要与类名保持一致的情况。

现在开始，我们可以编写 Java 代码了。

### Javadoc 支持的 HTML 标签

在 Javadoc 中是可以使用部分 HTML 标签 的，这样可以让生成的 API 文档更美观、可读性更高。

| 标签 | 用途 | 示例 |
| :--- | :--- | :--- |
| `<p>` | 段落换行 | `<p>这是一个段落</p>` |
| `<br>` | 强制换行 | `第一行<br>第二行` |
| `<b>` / `<strong>` | 加粗 | `<b>重要</b>` |
| `<i>` / `<em>` | 斜体 | `<i>提示</i>` |
| `<code>` | 行内代码 | `<code>int x = 1;</code>` |
| `<pre>` | 保留格式的代码块 | `<pre>System.out.println("Hello");</pre>` |
| `<ul>` / `<ol>` / `<li>` | 列表 | `<ul><li>项1</li><li>项2</li></ul>` |
| `<a href="">` | 超链接 | `<a href="https://example.com">链接</a>` |
| `<table>` / `<tr>` / `<td>` | 表格 | `<table><tr><td>单元格</td></tr></table>` |

⚠️注意：HTML 标签必须闭合，否则生成的文档可能出现排版错误。

---

参考文献

1. 廖雪峰. Java 教程. https://liaoxuefeng.com/books/java/introduction/index.html