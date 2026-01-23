package com.liufan.learn.oop.abstractclassinterface;

// ==================== 接口：定义能力（能做什么） ====================

// 可支付接口
interface Payable {
    double calculatePayment();
}

// 可报告接口  
interface Reportable {
    String generateReport();
}

// ==================== 抽象类：通用实现，定义共性（是什么） ====================

// 抽象员工类
abstract class Employee {
    protected String name;
    protected double baseSalary;
    
    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }
    
    // 抽象方法：具体员工必须实现
    public abstract String getRole();
    
    // 具体方法：所有员工共享
    public void printInfo() {
        System.out.println("员工: " + name + ", 职位: " + getRole());
    }
}

// ==================== 具体类 ====================

// 全职员工：继承抽象类 + 实现接口
class FullTimeEmployee extends Employee implements Payable {
    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }
    
    @Override
    public String getRole() {
        return "全职员工";
    }
    
    @Override
    public double calculatePayment() {
        return baseSalary; // 全职员工拿固定工资
    }
}

// 兼职员工：继承抽象类 + 实现多个接口
class PartTimeEmployee extends Employee implements Payable, Reportable {
    private int hoursWorked;
    
    public PartTimeEmployee(String name, double hourlyRate, int hours) {
        super(name, hourlyRate);
        this.hoursWorked = hours;
    }
    
    @Override
    public String getRole() {
        return "兼职员工";
    }
    
    @Override
    public double calculatePayment() {
        return baseSalary * hoursWorked; // 按小时计算
    }
    
    @Override
    public String generateReport() {
        return String.format("%s 工时报表: 工作%d小时，工资%.2f", 
                            name, hoursWorked, calculatePayment());
    }
}

// 经理：继承抽象类 + 实现多个接口
class Manager extends Employee implements Payable, Reportable {
    private double bonus;
    
    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }
    
    @Override
    public String getRole() {
        return "经理";
    }
    
    @Override
    public double calculatePayment() {
        return baseSalary + bonus; // 工资+奖金
    }
    
    @Override
    public String generateReport() {
        return String.format("%s 经理报表: 基本工资%.2f，奖金%.2f，总计%.2f",
                            name, baseSalary, bonus, calculatePayment());
    }
}

// ==================== 主程序 ====================

/**
 * 员工工资结算
 * <p>
 * 核心思想：
 * <ul>
 *     <li>抽象类：用于"是什么"的层次关系（Employee → Manager）</li>
 *     <li>接口：用于"能做什么"的能力扩展（Payable, Reportable）</li>
 *     <li>具体类：继承一个抽象类 + 实现多个接口 = 既有身份又有能力</li>
 * </ul>
 */
public class EmployeePayment {
    public static void run() {
        // 创建不同类型的员工
        Employee emp1 = new FullTimeEmployee("张三", 8000);
        Employee emp2 = new PartTimeEmployee("李四", 50, 100);
        Employee emp3 = new Manager("王五", 10000, 3000);
        
        // 多态：统一处理所有员工
        Employee[] employees = { emp1, emp2, emp3 };
        
        System.out.println("=== 员工信息 ===");
        for (Employee emp : employees) {
            emp.printInfo();
        }
        
        System.out.println("\n=== 工资计算 ===");
        for (Employee emp : employees) {
            if (emp instanceof Payable payable) {
                System.out.printf("%s 工资: %.2f%n", emp.name, payable.calculatePayment());
            }
        }
        
        System.out.println("\n=== 报表生成 ===");
        for (Employee emp : employees) {
            if (emp instanceof Reportable reportable) {
                System.out.println(reportable.generateReport());
            }
        }
    }
}