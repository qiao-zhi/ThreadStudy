package cn.qlq.thread.thirteen;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2 {
    private static int num ;
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);
    
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> strings = new ArrayBlockingQueue<>(1);//必须指定容量(指定容器最多为1)
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for  (int i=0;i<5;i++) {
                        String ele = "ele"+(++num);
                        strings.put(ele);
                        LOGGER.info("ThreadName ->{} put ele->{}",Thread.currentThread().getName(),ele);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"producer");
        producer.start();
        
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<5;i++) {
                        Thread.sleep(1*1000);
                        String take = strings.take();
                        LOGGER.info("ThreadName ->{} take ele->{}",Thread.currentThread().getName(),take);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer");
        consumer.start();
    }
}