# learn-java

学习之前，先来认（膜）识（拜）一下 ：Java 之父，[詹姆斯·高斯林](https://en.wikipedia.org/wiki/James_Gosling)，俗称高司令。

![Java 之父，詹姆斯·高斯林，俗称高司令](readme/James_Gosling_2008.jpg)

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

### classpath

`classpath` 是 JVM 用到的一个环境变量，它用来指示 JVM 如何搜索 `class`。

因为 Java 是编译型语言，源码文件是 .java，而编译后的 .class 文件才是真正可以被 JVM 执行的字节码。因此，JVM 需要知道，
如果要加载一个 `abc.xyz.Hello` 的类，应该去哪搜索对应的 `Hello.class` 文件。

所以，`classpath` 就是一组目录的集合，它设置的搜索路径与操作系统相关。例如，在 Windows 系统上，用`;`分隔，带空格的目录用双引号 `""` 括起来，可能长这样：

```plain
C:\work\project1\bin;C:\shared;"D:\My Documents\project1\bin"
```

在 macOS/Linux 系统上，用`:`分隔，可能长这样：

```plain
/usr/shared:/usr/local/bin:/home/liufan/bin
```

现在我们假设 `classpath`是 `.;C:\work\project1\bin;C:\shared` 当 JVM 在加载 `abc.xyz.Hello` 这个类时，会依次查找：
- <当前目录>\abc\xyz\Hello.class
- C:\work\project1\bin\abc\xyz\Hello.class
- C:\shared\abc\xyz\Hello.class

注意到 `.` 代表当前目录。如果 JVM 在某个路径下找到了对应的 class 文件，就不再往后继续搜索。如果所有路径下都没有找到，就报错。

`classpath` 的设定方法有两种：
- 在系统环境变量中设置 `classpath` 环境变量，不推荐；
- 在启动 JVM 时设置 `classpath` 变量，推荐。

我们强烈不推荐在系统环境变量中设置 `classpath`，那样会污染整个系统环境。在启动 JVM 时设置 `classpath` 才是推荐的做法。实际上就是给 `java` 命令传入 `-classpath` 参数：

```plain
java -classpath .;C:\work\project1\bin;C:\shared abc.xyz.Hello
```

或者使用 `-cp` 的简写：

```plain
java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello
```

没有设置系统环境变量，也没有传入 `-cp` 参数，那么 JVM 默认的 `classpath` 为 `.`，即当前目录：

```plain
java abc.xyz.Hello
```

上述命令告诉 JVM 只在当前目录搜索 `Hello.class`。

在 IDE中 运行 Java 程序，IDE 自动传入的 `-cp` 参数是当前工程的 `bin` 目录和引入的 jar 包。

通常，我们在自己编写的 `class` 中，会引用 Java 核心库的 `class`，例如，`String`、`ArrayList` 等。这些 `class` 应该上哪去找？

有很多“如何设置 `classpath`”的文章会告诉你把 JVM 自带的 `rt.jar` 放入 `classpath`，但事实上，根本不需要告诉 JVM 如何去 Java 核心库查找 `class`，JVM 怎么可能笨到连自己的核心库在哪都不知道！
⚠️不要把任何 Java 核心库添加到 `classpath` 中！JVM 根本不依赖 `classpath` 加载核心库！

更好的做法是，不要设置 `classpath`！默认的当前目录`.`对于绝大多数情况都够用了。

假设我们有一个编译后的 `Hello.class`，它的包名是 `com.example`，当前目录是 `C:\work`，那么，目录结构必须如下：

```ascii
C:\work
└─ com
   └─ example
      └─ Hello.class
```

运行这个 `Hello.class` 必须在当前目录 `C:\work` 下使用如下命令：

```plain
C:\work> java -cp . com.example.Hello
```

JVM 根据 `classpath` 设置的 `.` 在当前目录下查找 `com.example.Hello`，即实际搜索文件必须位于 `com/example/Hello.class`。如果指定的 `.class` 文件不存在，或者目录结构和包名对不上，均会报错。

### jar 包

如果有很多 `.class` 文件，散落在各层目录中，肯定不便于管理。如果能把目录打一个包，变成一个文件，就方便多了。

jar 包就是用来干这个事的，它可以把 `package` 组织的目录层级，以及各个目录下的所有文件（包括 `.class` 文件和其他文件）都打成一个 jar 文件，这样一来，无论是备份，还是发给客户，就简单多了。

jar 包实际上就是一个 zip 格式的压缩文件，而 jar 包相当于目录。如果我们要执行一个 jar 包的 `class`，就可以把 jar 包放到 `classpath` 中：

```plain
java -cp ./hello.jar abc.xyz.Hello
```

这样 JVM 会自动在 `hello.jar` 文件里去搜索某个类。

#### 创建 jar 包

因为 jar 包就是 zip 包，所以，直接在资源管理器中，找到正确的目录，点击右键，在弹出的快捷菜单中选择“发送到”，“压缩（zipped）文件夹”，就制作了一个 zip 文件。然后，把后缀从 `.zip 改为 `.jar`，一个 jar 包就创建成功。

假设编译输出的目录结构是这样：

```ascii
package_sample
└─ bin
   ├─ hong
   │  └─ Person.class
   │  ming
   │  └─ Person.class
   └─ mr
      └─ jun
         └─ Arrays.class
```

这里需要特别注意的是，jar 包里的第一层目录，不能是 `bin`，而应该是 `hong`、`ming`、`mr`。如果在 Windows 的资源管理器中看，应该长这样：

![hello.zip.ok](readme/good-jar.jpg)

原因是 `hong.Person` 必须按 `hong/Person.class` 存放，而不是 `bin/hong/Person.class`。

jar 包还可以包含一个特殊的 `/META-INF/MANIFEST.MF` 文件，`MANIFEST.MF` 是纯文本，可以指定 `Main-Class` 和其它信息。JVM 会自动读取这个 `MANIFEST.MF` 文件，如果存在 `Main-Class`，我们就不必在命令行指定启动的类名，而是用更方便的命令：

```plain
java -jar hello.jar
```

在大型项目中，不可能手动编写 `MANIFEST.MF` 文件，再手动创建 jar 包。Java 社区提供了大量的开源构建工具，例如 Maven，可以非常方便地创建 jar 包。

### class 版本

在 Java 开发中，许多童鞋经常被各种版本的 JDK 搞得晕头转向，本节我们就来详细讲解 Java 程序编译后的 class 文件版本问题。

我们通常说的 Java 8，Java 11，Java 17，是指 JDK 的版本，也就是 JVM 的版本，更确切地说，就是 `java.exe` 这个程序的版本：

```plain
$ java -version
java version "25.0.1" 2025-10-21 LTS
```

而每个版本的 JVM，它能执行的 class 文件版本也不同。例如，Java 11 对应的 class 文件版本是 55，而 Java 17 对应的 class 文件版本是 61。

如果用 Java 11 编译一个 Java 程序，输出的 class 文件版本默认就是 55，这个 class 既可以在 Java 11 上运行，也可以在 Java 17 上运行，因为 Java 17 支持的 class 文件版本是 61，表示“最多支持到版本 61”。

如果用 Java 17 编译一个 Java 程序，输出的 class 文件版本默认就是 61，它可以在 Java 17、Java 18 上运行，但不可能在 Java 11 上运行，因为 Java 11 支持的 class 版本最多到 55。如果使用低于 Java 17 的 JVM 运行，会得到一个 `UnsupportedClassVersionError`，错误信息类似：

```plain
java.lang.UnsupportedClassVersionError: Xxx has been compiled by a more recent version of the Java Runtime...
```

只要看到 `UnsupportedClassVersionError` 就表示当前要加载的 class 文件版本超过了 JVM 的能力，必须使用更高版本的 JVM 才能运行。

打个比方，用 Word 2013 保存一个 Word 文件，这个文件也可以在 Word 2016 上打开。但反过来，用 Word 2016 保存一个 Word 文件，就无法使用 Word 2013 打开。

当然，用 Word 2016 也可以保存一个格式为 Word 2013 的文件，这样保存的 Word 文件就可以用低版本的 Word 2013 打开，但前提是保存时必须明确指定文件格式兼容 Word 2013。

类似的，对应到 JVM 的 class 文件，我们也可以用 Java 17 编译一个 Java 程序，指定输出的 class 版本要兼容 Java 11（即 class 版本 55），这样编译生成的 class 文件就可以在 Java >=11 的环境中运行。

指定编译输出有两种方式，一种是在 `javac` 命令行中用参数 `--release` 设置：

```plain
$ javac --release 11 Main.java
```

参数 `--release 11` 表示源码兼容 Java 11，编译的 class 输出版本为 Java 11 兼容，即 class 版本 55。

第二种方式是用参数 `--source` 指定源码版本，用参数 `--target` 指定输出 class 版本：

```plain
$ javac --source 9 --target 11 Main.java
```

上述命令如果使用 Java 17 的 JDK 编译，它会把源码视为 Java 9 兼容版本，并输出 class 为 Java 11 兼容版本。注意 `--release` 参数和 `--source --target` 参数只能二选一，不能同时设置。

然而，指定版本如果低于当前的 JDK 版本，会有一些潜在的问题。例如，我们用 Java 17 编译 `Hello.java`，参数设置 `--source 9` 和 `--target 11`：

```java
public class Hello {
    public static void hello(String name) {
        System.out.println("hello".indent(4));
    }
}
```

用低于 Java 11 的 JVM 运行 `Hello` 会得到一个 `LinkageError`，因为无法加载 `Hello.class` 文件，而用 Java 11 运行 `Hello` 会得到一个 `NoSuchMethodError`，因为 `String.indent()` 方法是从 Java 12 才添加进来的，Java 11 的 `String` 版本根本没有 `indent()` 方法。

```alert type=notice title=注意
⚠️如果使用 `--release 11` 则会在编译时检查该方法是否在 Java 11 中存在。
```

因此，如果运行时的 JVM 版本是 Java 11，则编译时也最好使用 Java 11，而不是用高版本的 JDK 编译输出低版本的 class。

如果使用 `javac` 编译时不指定任何版本参数，那么相当于使用 `--release 当前版本` 编译，即源码版本和输出版本均为当前版本。

在开发阶段，多个版本的 JDK 可以同时安装，当前使用的 JDK 版本可由 `JAVA_HOME` 环境变量切换。

#### 源码版本

在编写源代码的时候，我们通常会预设一个源码的版本。在编译的时候，如果用 `--source` 或 `--release` 指定源码版本，则使用指定的源码版本检查语法。

例如，使用了 lambda 表达式的源码版本至少要为 8 才能编译，使用了 `var` 关键字的源码版本至少要为 10 才能编译，使用 `switch` 表达式的源码版本至少要为 12 才能编译，且 12 和 13 版本需要启用`--enable-preview`参数。

### 模块

从 Java 9 开始，JDK 又引入了模块（Module）。

什么是模块？这要从 Java 9 之前的版本说起。

我们知道，`.class` 文件是 JVM 看到的最小可执行文件，而一个大型程序需要编写很多 Class，并生成一堆 `.class` 文件，很不便于管理，所以，`jar` 文件就是 `class` 文件的容器。

在 Java 9 之前，一个大型 Java 程序会生成自己的 jar 文件，同时引用依赖的第三方 jar 文件，而 JVM 自带的 Java 标准库，实际上也是以 jar 文件形式存放的，这个文件叫 `rt.jar`，一共有 60 多 M。

如果是自己开发的程序，除了一个自己的 `app.jar` 以外，还需要一堆第三方的 jar 包，运行一个 Java 程序，一般来说，命令行写这样：

```plain
java -cp app.jar:a.jar:b.jar:c.jar com.liaoxuefeng.sample.Main
```

```alert type=warning title=注意
JVM 自带的标准库 rt.jar 不要写到 classpath 中，写了反而会干扰 JVM 的正常运行。
```

如果漏写了某个运行时需要用到的 jar，那么在运行期极有可能抛出 `ClassNotFoundException`。

所以，jar 只是用于存放 class 的容器，它并不关心 class 之间的依赖。

从 Java 9 开始引入的模块，主要是为了解决"依赖"这个问题。如果 `a.jar` 必须依赖另一个 `b.jar` 才能运行，那我们应该给 `a.jar` 加点说明啥的，让程序在编译和运行的时候能自动定位到 `b.jar`，这种自带"依赖关系"的 class 容器就是模块。

为了表明 Java 模块化的决心，从 Java 9 开始，原有的 Java 标准库已经由一个单一巨大的 `rt.jar` 分拆成了几十个模块，这些模块以 `.jmod` 扩展名标识，可以在 `$JAVA_HOME/jmods` 目录下找到它们：

- java.base.jmod
- java.compiler.jmod
- java.datatransfer.jmod
- java.desktop.jmod
- ...

这些 `.jmod` 文件每一个都是一个模块，模块名就是文件名。例如：模块 `java.base` 对应的文件就是 `java.base.jmod`。模块之间的依赖关系已经被写入到模块内的 `module-info.class` 文件了。所有的模块都直接或间接地依赖 `java.base` 模块，只有 `java.base` 模块不依赖任何模块，它可以被看作是"根模块"，好比所有的类都是从 `Object` 直接或间接继承而来。

把一堆 class 封装为 jar 仅仅是一个打包的过程，而把一堆 class 封装为模块则不但需要打包，还需要写入依赖关系，并且还可以包含二进制代码（通常是 JNI 扩展）。此外，模块支持多版本，即在同一个模块中可以为不同的 JVM 提供不同的版本。

#### 编写模块

那么，我们应该如何编写模块呢？还是以具体的例子来说。首先，创建模块和创建 Java 项目是完全一样的，以 `oop-module` 工程为例，它的目录结构如下：

```ascii
oop-module
├── bin
├── build.sh
└── src
    ├── com
    │   └── itranswarp
    │       └── sample
    │           ├── Greeting.java
    │           └── Main.java
    └── module-info.java
```    

其中，`bin` 目录存放编译后的 class 文件，`src` 目录存放源码，按包名的目录结构存放，仅仅在 `src` 目录下多了一个 `module-info.java` 这个文件，这就是模块的描述文件。在这个模块中，它长这样：

```java
module hello.world {
	requires java.base; // 可不写，任何模块都会自动引入 java.base
	requires java.xml;
}
```

其中，`module` 是关键字，后面的 `hello.world` 是模块的名称，它的命名规范与包一致。花括号的 `requires xxx;` 表示这个模块需要引用的其他模块名。除了 `java.base` 可以被自动引入外，这里我们引入了一个 `java.xml` 的模块。

当我们使用模块声明了依赖关系后，才能使用引入的模块。例如，`Main.java` 代码如下：

```java
package com.itranswarp.sample;

// 必须引入 java.xml 模块后才能使用其中的类:
import javax.xml.XMLConstants;

public class Main {
	public static void main(String[] args) {
		Greeting g = new Greeting();
		System.out.println(g.hello(XMLConstants.XML_NS_PREFIX));
	}
}
```

如果把 `requires java.xml;` 从 `module-info.java` 中去掉，编译将报错。可见，模块的重要作用就是声明依赖关系。

下面，我们用 JDK 提供的命令行工具来编译并创建模块。

首先，我们把工作目录切换到 `oop-module`，在当前目录下编译所有的 `.java` 文件，并存放到 `bin` 目录下，命令如下：

```plain
$ javac -d bin src/module-info.java src/com/itranswarp/sample/*.java
```

如果编译成功，现在项目结构如下：

```ascii
oop-module
├── bin
│   ├── com
│   │   └── itranswarp
│   │       └── sample
│   │           ├── Greeting.class
│   │           └── Main.class
│   └── module-info.class
└── src
    ├── com
    │   └── itranswarp
    │       └── sample
    │           ├── Greeting.java
    │           └── Main.java
    └── module-info.java
```

注意到 `src` 目录下的 `module-info.java` 被编译到 `bin` 目录下的 `module-info.class`。

下一步，我们需要把 bin 目录下的所有 class 文件先打包成 jar，在打包的时候，注意传入 `--main-class` 参数，让这个 jar 包能自己定位 `main` 方法所在的类：

```plain
$ jar --create --file hello.jar --main-class com.itranswarp.sample.Main -C bin .
```

现在我们就在当前目录下得到了 `hello.jar` 这个 jar 包，它和普通 jar 包并无区别，可以直接使用命令 `java -jar hello.jar` 来运行它。但是我们的目标是创建模块，所以，继续使用 JDK 自带的 `jmod` 命令把一个 jar 包转换成模块：

```plain
$ jmod create --class-path hello.jar hello.jmod
```

于是，在当前目录下我们又得到了 `hello.jmod` 这个模块文件，这就是最后打包出来的传说中的模块！

#### 运行模块

要运行一个 jar ，我们使用 `java -jar xxx.jar` 命令。要运行一个模块，我们只需要指定模块名。试试：

```plain
$ java --module-path hello.jmod --module hello.world
```

结果是一个错误：

```plain
Error occurred during initialization of boot layer
java.lang.module.FindException: JMOD format not supported at execution time: hello.jmod
```

原因是 `.jmod` 不能被放入 `--module-path` 中。换成 `.jar` 就没问题了：

```plain
$ java --module-path hello.jar --module hello.world
Hello, xml!
```

那我们辛辛苦苦创建的 `hello.jmod` 有什么用？答案是我们可以用它来打包 JRE 。

#### 打包 JRE

前面讲了，为了支持模块化，Java 9 首先带头把自己的一个巨大无比的 `rt.jar` 拆成了几十个 `.jmod` 模块，原因就是，运行 Java 程序的时候，实际上我们用到的 JDK 模块，并没有那么多。不需要的模块，完全可以删除。

过去发布一个 Java 应用程序，要运行它，必须下载一个完整的 JRE，再运行 jar 包。而完整的 JRE 块头很大，有 100 多 M。怎么给 JRE 瘦身呢？

现在，JRE 自身的标准库已经分拆成了模块，只需要带上程序用到的模块，其他的模块就可以被裁剪掉。怎么裁剪 JRE 呢？并不是说把系统安装的 JRE 给删掉部分模块，而是"复制"一份 JRE，但只带上用到的模块。为此，JDK 提供了 `jlink` 命令来干这件事。命令如下：

```plain
$ jlink --module-path hello.jmod --add-modules java.base,java.xml,hello.world --output jre/
```

我们在 `--module-path` 参数指定了我们自己的模块 `hello.jmod`，然后，在 `--add-modules` 参数中指定了我们用到的 3 个模块 `java.base`、`java.xml` 和 `hello.world`，用 `,` 分隔。最后，在 `--output` 参数指定输出目录。

现在，在当前目录下，我们可以找到 `jre` 目录，这是一个完整的并且带有我们自己 `hello.jmod` 模块的 JRE。试试直接运行这个 JRE：

```plain
$ jre/bin/java --module hello.world
Hello, xml!
```

要分发我们自己的 Java 应用程序，只需要把这个 `jre` 目录打个包给对方发过去，对方直接运行上述命令即可，既不用下载安装 JDK，也不用知道如何配置我们自己的模块，极大地方便了分发和部署。

#### 访问权限

前面我们讲过，Java 的 class 访问权限分为 public 、protected 、private 和默认的包访问权限。引入模块后，这些访问权限的规则就要稍微做些调整。

确切地说，class 的这些访问权限只在一个模块内有效，模块和模块之间，例如，a 模块要访问 b 模块的某个 class ，必要条件是 b 模块明确地导出了可以访问的包。

举个例子：我们编写的模块 `hello.world` 用到了模块 `java.xml` 的一个类 `javax.xml.XMLConstants` ，我们之所以能直接使用这个类，是因为模块 `java.xml` 的 `module-info.java` 中声明了若干导出：

```java
module java.xml {
    exports java.xml;
    exports javax.xml.catalog;
    exports javax.xml.datatype;
    //...
}
```

只有它声明的导出的包，外部代码才被允许访问。换句话说，如果外部代码想要访问我们的 `hello.world` 模块中的 `com.itranswarp.sample.Greeting` 类，我们必须将其导出：

```java
module hello.world {
    exports com.itranswarp.sample;

    requires java.base;
    requires java.xml;
}
```

因此，模块进一步隔离了代码的访问权限。

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