package by.epam.mentoring.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomThread implements Runnable {
    public static final String MSG = "Resource {} was locked by {}.";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomThread.class);

    private String fstObj;
    private String scndObj;

    public CustomThread(String fstObj, String scndObj) {
        this.fstObj = fstObj;
        this.scndObj = scndObj;
    }

    @Override
    public void run() {
        synchronized (fstObj) {
            String name = Thread.currentThread().getName();
            LOGGER.info(MSG, fstObj, name);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            synchronized (scndObj) {
                LOGGER.info(MSG, scndObj, name);
            }
        }
    }
}
