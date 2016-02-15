package by.epam.mentoring.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingThread implements Runnable {
    public static final String MSG = "Resource {} was locked by {}.";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomThread.class);

    private AtomicReference<String> fstObj = new AtomicReference<>();
    private AtomicReference<String> scndObj = new AtomicReference<>();

    public NonBlockingThread(String fstObj, String scndObj) {
        this.fstObj.set(fstObj);
        this.scndObj.set(scndObj);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        LOGGER.info(MSG, fstObj, name);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        LOGGER.info(MSG, scndObj, name);
    }
}

