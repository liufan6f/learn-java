package com.liufan.learn.processcontrol;

/**
 * switch case 条件判断（多重选择）
 * <p>
 * 使用 switch 语句时，只要保证有 break，case 的顺序不影响程序逻辑，但是仍然建议按照自然顺序排列，便于阅读。
 * @since Java 14 开始，switch 语句正式升级为表达式，不再需要 break，并且允许使用 yield 返回值。
 * @see LearnIf
 */
public class LearnSwitch {

    /**
     * switch case 条件判断练习
     * @param option 传入值为 1，演示效果最佳
     */
    public static void practice(int option, String fruit) {
        // 使用 switch 时，注意 case 语句并没有花括号 {}，而且，case 语句具有“穿透性”，漏写 break 会直接穿透到下一个 case 语句
        switch (option) {
            case 1:
                System.out.println("Selected 1");
//                 break; // 假设这里忘记写 break
            case 2:
                          // 会穿透到这个 case 语句，继续执行。因此，任何时候都不要忘记写 break。
                System.out.println("Selected 2");
                break;
            case 3:       // 如果有几个 case 语句执行的是同一组语句块，可以这么写
            case 4:
                System.out.println("Selected 3, 4");
                break;
            default:
                System.out.println("Selected other");
                break;
        }
        /*
        等于 if 条件判断的写法：

        if (option == 1) {
            System.out.println("Selected 1");
        } else if (option == 2) {
            System.out.println("Selected 2");
        } else if (option == 3 || option == 4) {
            System.out.println("Selected 3, 4");
        } else {
            System.out.println("Selected other");
        }

        对比 if ... else if 语句，对于多个 == 判断的情况，使用 switch 结构更加清晰
         */

        // switch 语句还可以匹配字符串。字符串匹配时，是比较“内容相等”。
        switch (fruit) {
            case "apple":
                System.out.println("Selected apple");
                break;
            case "pear":
                System.out.println("Selected pear");
                break;
            case "mango":
                System.out.println("Selected mango");
                break;
            default:
                System.out.println("No fruit selected");
                break;
        }
    }

    /**
     * switch case 条件判断（模式匹配）
     * <p>
     * 使用 switch 时，如果遗漏了 break，就会造成严重的逻辑错误，而且不易在源代码中发现错误。从 Java 12 开始，
     * switch 语句升级为更简洁的表达式语法，使用类似模式匹配（Pattern Matching）的方法，保证只有一种路径会被执行，
     * 并且不需要 break 语句。
     * @since java 12
     */
    public static void practiceJava12(String fruit) {
        switch (fruit) {
            case "apple" -> System.out.println("Selected apple");
            case "pear" -> System.out.println("Selected pear");
            case "mango" -> { // 有多条语句，需要用{}括起来
                System.out.println("Selected mango");
                System.out.println("Good choice!");
            }
            default -> System.out.println("No fruit selected");
        }
    }

    /**
     * 用 switch 语句给某个变量赋值
     * @since java 12
     */
    public static void practiceReturn(String fruit) {
        /*
        int opt;
        switch (fruit) {
            case "apple":
                opt = 1;
                break;
            case "pear":
            case "mango":
                opt = 2;
                break;
            default:
                opt = 0;
                break;
        }

        使用新的 switch 语法，不但不需要 break，还可以直接返回简单的值。
         */
        int opt = switch (fruit) {
            case "apple" -> 1;
            case "pear", "mango" -> 2;
            default -> {
                // 如果需要复杂的语句，我们也可以写很多语句，放到 {...} 里，然后，用 yield 返回一个值作为 switch 语句的返回值
                int c = fruit.hashCode();
                c += 1;
                yield c;
            }
        };
        System.out.println("opt = " + opt);
    }
}
