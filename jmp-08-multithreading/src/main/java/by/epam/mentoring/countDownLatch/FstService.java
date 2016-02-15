package by.epam.mentoring.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class FstService implements Runnable {

    private final CountDownLatch latch;

    public FstService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started service One");
        latch.countDown();

    }
}
