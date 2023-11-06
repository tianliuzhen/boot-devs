package com.aaa.sass.config;

import com.aaa.sass.domain.User;

/**
 * 用户上下文
 *
 * @author liuzhen.tian
 * @version 1.0 UserContext.java  2021/7/17 15:40
 */
public class UserContext {

    private static ThreadLocal<User> local = new ThreadLocal<User>();
    private static ThreadLocal<User> local2 = new ThreadLocal<User>();

    public static void setUser(User user) {
        System.out.println("当前线程set：" + Thread.currentThread().getName());
        local.set(user);
    }

    public static User getUser() {
        System.out.println("当前线程get：" + Thread.currentThread().getName());
        return local.get();
    }

    public static void remove() {
        System.out.println("当前线程remove：" + Thread.currentThread().getName());
        local.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        doAction();
    }

    private static void doAction() {
        ThreadLocal<User> local = new ThreadLocal<>();
        local.set(new User("100"));
        local.remove();
        local.set(new User("101"));

        // gc之前使用断点看一下当前线程的threadLocals属性
        Thread gcBeforeCurrentThread = Thread.currentThread();

        local = null; // 手动断开引用
        System.gc();

        // gc之后使用断点看一下当前线程的threadLocals属性
        // 此时的referent意思是null了
        Thread gcAfterCurrentThread = Thread.currentThread();
    }


    // public static void main(String[] args) throws InterruptedException {
    //     CountDownLatch countDownLatch = new CountDownLatch(1);
    //     new Thread(
    //             () -> {
    //                 // 创建一个ThreadLocal对象
    //                 ThreadLocal<String> threadLocal = new ThreadLocal<>();
    //                 threadLocal.set("hello world");
    //                 // 让ThreadLocal对象没有任何强引用
    //                 threadLocal = null;
    //                 System.gc();
    //                 // 获取当前线程
    //                 Thread currentThread = Thread.currentThread();
    //                 // 可以在这一行打断点，查看currentThread里面的threadLocals对象
    //                 countDownLatch.countDown();
    //             })
    //             .start();
    //     countDownLatch.await();
    // }
}
