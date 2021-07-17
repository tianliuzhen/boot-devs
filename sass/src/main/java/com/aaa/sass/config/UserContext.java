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

    public static void  setUser(User user){
        System.out.println( "当前线程set：" + Thread.currentThread().getName() );
        local.set(user);
    }

    public static User  getUser(){
        System.out.println( "当前线程get：" + Thread.currentThread().getName() );
        return local.get();
    }

    public static void remove(){
        System.out.println( "当前线程remove：" + Thread.currentThread().getName() );
        local.remove();
    }
}
