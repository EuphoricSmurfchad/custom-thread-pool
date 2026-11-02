package com;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueue<Runnable> {
    private List<Runnable> tasks;
    private final int capacity;
    private int count;
    private boolean isShutdown = false;

    BlockingQueue(int capacity) {
        this.capacity = capacity;
        tasks = new ArrayList<>();
        count = 0;
    }

    synchronized void put(Runnable task) {
        while (count >= capacity && !isShutdown) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tasks.add(task);
        count++;
        this.notifyAll();
    }

    synchronized Runnable take() {
        while (count == 0 && !isShutdown) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (tasks.size() == 0)
            return null;
        Runnable task = tasks.remove(0);
        count--;
        this.notifyAll();
        return task;
    }

    synchronized void shutdown() {
        isShutdown = true;
        this.notifyAll();
    }

}
