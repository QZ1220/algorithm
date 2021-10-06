package com.audi.shame.procon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerPattern {

    public static void main(String args[]) throws InterruptedException {

        // 实例化一个有界的阻塞队列
        BlockingQueue sharedQueue = new LinkedBlockingQueue(10);

        // 初始化生产者 传入有界队列的大小  方便做拥塞控制
        Thread prodThread = new Thread(new Producer(sharedQueue, 10));

        // 初始化两个消费者
        Thread consThread1 = new Thread(new Consumer(sharedQueue));
        Thread consThread2 = new Thread(new Consumer(sharedQueue));

        // 启动生产者
        prodThread.start();

        // 这里消费者 延迟启动，以测试生产者是否可以持续进行生产
        Thread.sleep(200);
        consThread1.start();
        consThread2.start();
    }
}
