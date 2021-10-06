package com.audi.shame.procon;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者  使用take方法进行出队  可以实例化多个
 *
 * @author: WangQuanzhou
 * @date: 2021-10-06 3:20 PM
 */
public class Consumer implements Runnable {

    private final BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            if (blockingQueue.size() > 0) {
                try {
                    // take方法内部自己做了并发控制
                    System.out.println(Thread.currentThread().getName() + " Consumed: " + blockingQueue.take());
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}