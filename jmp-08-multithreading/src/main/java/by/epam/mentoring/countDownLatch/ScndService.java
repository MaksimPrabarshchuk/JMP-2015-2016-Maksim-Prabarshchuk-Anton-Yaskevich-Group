package by.epam.mentoring.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class ScndService implements Runnable {

    private final CountDownLatch latch;

    public ScndService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started service One");
        latch.countDown();
    }

}
