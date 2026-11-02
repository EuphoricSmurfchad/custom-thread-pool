package com;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(3, 10);
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            try {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " task number " + taskNumber);

                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Thread.sleep(5000);
        pool.shutdown();
    }

}
