package by.epam.mentoring.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Starter {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        Thread serviceOneThread = new Thread(new FstService(latch));
        Thread serviceTwoThread = new Thread(new ScndService(latch));

        serviceOneThread.start();
        serviceTwoThread.start();
        try {
            latch.await();
            System.out.println("Starting main Thread!!!");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
