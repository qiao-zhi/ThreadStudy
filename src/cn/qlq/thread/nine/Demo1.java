package cn.qlq.thread.nine;


/**
 * 线程类join()使用方法
 * 
 * @author Administrator
 *
 */
public class Demo1 extends Thread {
    /**
     * 更改线程名字
     * 
     * @param threadName
     */
    public Demo1(String threadName) {
        this.setName(threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1 * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-----" + i);
        }
    }

    public static void main(String[] args) {
        Demo1 t1 = new Demo1("t1");
        Demo1 t2 = new Demo1("t2");
        Demo1 t3 = new Demo1("t3");
        t1.start();
        /**
         * join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         * 程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         * 所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         */
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (t2.isAlive()) {
            System.out.println("t2 is alive");
        } else {
            System.out.println("t2 is not alive");
        }
        t2.start();
        t3.start();
    }
}