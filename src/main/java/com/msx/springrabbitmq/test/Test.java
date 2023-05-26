//package com.msx.springrabbitmq.test;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Program: spring-rabbitmq
// * @description:
// * @author: mengshixuan
// * @create: 2023-05-22 07:30:21
// **/
//public class Test {
//    //创建 interrupt-1 线程
//
//    Thread thread = new Thread(() -> {
//        while (true) {
//            //判断当前线程是否中断，
//            if (Thread.currentThread().isInterrupted()) {
//                System.out.println("线程1 接收到中断信息，中断线程...中断标记：" + Thread.currentThread().isInterrupted());
//                Thread.interrupted(); // //对线程进行复位，由 true 变成 false
//                System.out.println("经过 Thread.interrupted() 复位后，中断标记：" + Thread.currentThread().isInterrupted());
//
//                //再次判断是否中断，如果是则退出线程
//                if (Thread.currentThread().isInterrupted()) {
//                    break;
//                }
//                break;
//            }
//            System.out.println(Thread.currentThread().getName() + "线程正在执行...");
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }, "interrupt-1");
//    //启动线程 1
//        thread.start();
//
//
//}
