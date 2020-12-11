package com.aaa.schedule.quartz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author liuzhen.tian
 * @version 1.0 TimerDemo.java  2020/12/11 17:13
 */
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask1 run" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        },1000,5000);//延时1s，之后每隔5s运行一次
    }
}
