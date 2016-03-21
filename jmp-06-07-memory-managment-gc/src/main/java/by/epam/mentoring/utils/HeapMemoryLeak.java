package by.epam.mentoring.utils;

import java.util.ArrayList;
import java.util.List;

public class HeapMemoryLeak {
    private List<Object> list = new ArrayList<Object>();

    public void makeMamoryLeak() {
        while (true) {
            list.add(new Object());
        }
    }
}
