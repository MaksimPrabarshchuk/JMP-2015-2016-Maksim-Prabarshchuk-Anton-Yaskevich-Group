package by.epam.mentoring.deadlock;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Starter {
    private static final String fstResource = "First resource";
    private static final String scndResource = "Second resource";
    private static final String thdResource = "Third resource";

    public static void main(String[] args) throws Exception {
        System.out.println("1. Blocking operations. \n2. Non-blockign operations. \n3. Exit.");
        Scanner val = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            switch (val.nextInt()) {
                case 1:
                    getBlockingThreads().forEach(Thread::start);
                    exit = true;
                    break;
                case 2:
                    getNonBlockingThreads().forEach(Thread::start);
                    exit = true;
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }
    }

    private static List<Thread> getBlockingThreads() {
        return Arrays.asList(new Thread[]{
                new Thread(new CustomThread(fstResource, scndResource), "Thread 1"),
                new Thread(new CustomThread(scndResource, thdResource), "Thread 2"),
                new Thread(new CustomThread(thdResource, fstResource), "Thread 3")
        });
    }

    private static List<Thread> getNonBlockingThreads() {
        return Arrays.asList(new Thread[]{
                new Thread(new NonBlockingThread(fstResource, scndResource), "Thread 1"),
                new Thread(new NonBlockingThread(scndResource, thdResource), "Thread 2"),
                new Thread(new NonBlockingThread(thdResource, fstResource), "Thread 3")
        });
    }
}
