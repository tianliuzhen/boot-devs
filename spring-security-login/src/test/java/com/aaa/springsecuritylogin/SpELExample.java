package com.aaa.springsecuritylogin;

/**
 * @author liuzhen.tian
 * @version 1.0 SpELExample.java  2025/2/9 13:50
 */

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELExample {
    public static void main(String[] args) {
        // 创建一个表达式解析器
        ExpressionParser parser = new SpelExpressionParser();

        // 创建一个简单的对象
        Person person = new Person("John", 30);

        // 创建 EvaluationContext 并设置根对象
        EvaluationContext context = new StandardEvaluationContext(person);

        // 解析并求值表达式
        /**
         * 底层是这样找的name，先找getName 再找isName
         * ReflectivePropertyAccessor#findGetterForProperty(java.lang.String, java.lang.Class, boolean)
         */
        Expression exp = parser.parseExpression("name"); // 获取 name 属性
        String name = (String) exp.getValue(context);
        System.out.println("Name: " + name); // 输出: Name: John

        // 解析并求值另一个表达式
        exp = parser.parseExpression("age > 25"); // 判断 age 是否大于 25
        boolean isAdult = (Boolean) exp.getValue(context);
        System.out.println("Is adult: " + isAdult); // 输出: Is adult: true
    }
}

// 一个简单的 Person 类
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
