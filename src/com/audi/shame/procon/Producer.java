package com.audi.shame.procon;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者 使用put方法进行生产入队操作ø
 *
 * @author: WangQuanzhou
 * @date: 2021-10-06 3:20 PM
 */
public class Producer implements Runnable {

    private final BlockingQueue sharedQueue;
    private final int size;

    public Producer(BlockingQueue sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.size = size;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            if (sharedQueue.size() < size) {
                try {
                    int i = random.nextInt(100000);
                    System.out.println("Produced: " + i);
                    // put方法内部自己做了并发控制
                    sharedQueue.put(i);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }

    }
}
