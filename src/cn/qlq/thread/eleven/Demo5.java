package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公平锁与非公平锁
 * 
 * @author Administrator
 *
 */
public class Demo5 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

    private Lock lock = new ReentrantLock(true);

    public void testMethod() {
        try {
            lock.lock();
            System.out.println("★ThreadName" + Thread.currentThread().getName() + "获得锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Demo5 demo5 = new Demo5();
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("☆线程" + Thread.currentThread().getName() + "运行了");
                demo5.testMethod();
            }
        };
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++)
            threads[i] = new Thread(runnable);
        for (int i = 0; i < 5; i++)
            threads[i].start();

    }
}