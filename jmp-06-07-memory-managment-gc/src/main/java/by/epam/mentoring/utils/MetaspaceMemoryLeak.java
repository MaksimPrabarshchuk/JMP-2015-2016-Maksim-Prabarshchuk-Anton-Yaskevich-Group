package by.epam.mentoring.utils;

import javassist.CannotCompileException;

public class MetaspaceMemoryLeak {
    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public void makeMemoryLeak() throws CannotCompileException {
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("LeakClass" + i).toClass();
            System.out.println(c.getName());
        }
    }
}
