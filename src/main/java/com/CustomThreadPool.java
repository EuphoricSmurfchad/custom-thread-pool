package com;

public class CustomThreadPool {

    private BlockingQueue<Runnable> taskQueue;
    private Worker[] workers;
    private boolean isShutdown = false;

    private class Worker extends Thread {

        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task = taskQueue.take();
                if (task == null)
                    break;
                task.run();
            }
        }
    }

    public CustomThreadPool(int poolSize, int queueCapacity) {
        taskQueue = new BlockingQueue<>(queueCapacity);
        workers = new Worker[poolSize];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    public void execute(Runnable task) throws Exception {
        if (isShutdown)
            throw new Exception("Executor is shutdown!");
        taskQueue.put(task);
    }

    public void shutdown() {
        isShutdown = true;
        taskQueue.shutdown();
        for (int i = 0; i < workers.length; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
