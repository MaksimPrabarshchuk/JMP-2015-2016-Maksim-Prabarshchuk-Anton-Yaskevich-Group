package by.epam.mentoring;

import by.epam.mentoring.utils.HeapMemoryLeak;
import by.epam.mentoring.utils.MetaspaceMemoryLeak;

import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("1. Heap memory leak. \n2. Metaspace memory leak. \n3. Exit.");
        Scanner val = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            switch (val.nextInt()) {
                case 1:
                    new HeapMemoryLeak().makeMamoryLeak();
                    break;
                case 2:
                    new MetaspaceMemoryLeak().makeMemoryLeak();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }
    }
}