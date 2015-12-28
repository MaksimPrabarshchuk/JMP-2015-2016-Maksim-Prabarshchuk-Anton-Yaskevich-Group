package by.epam.mentoring;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

class Main {
    public static final String USER_DIR = "user.dir";
    public static final String BASE_PATH = System.getProperty(USER_DIR) + "/jmp-05-classloaders/jar/";
    public static final String JAR_EXTENSION = ".jar";
    public static final String FIRST_CLASS = "First";
    public static final String SECOND_CLASS = "Second";
    public static final String DEFAULT_PACKAGE = "by.epam.mentoring.";


    private static Class getClass(String path, String className) throws IOException, ClassNotFoundException {
        try (URLClassLoader loader = new URLClassLoader(new URL[]{
                new URL(new File(path)
                        .toURI()
                        .toURL()
                        .toString())
        })) {
            return Class.forName(
                    className,
                    true,
                    loader
            );

        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("1. Load first class. \n2. Load second class. \n3. Exit.");
        Scanner val = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            switch (val.nextInt()) {
                case 1:
                    System.out.println(((Sample) getClass(BASE_PATH + FIRST_CLASS + JAR_EXTENSION, DEFAULT_PACKAGE + FIRST_CLASS)
                            .newInstance())
                            .getInfo());
                    break;
                case 2:
                    System.out.println(((Sample) getClass(BASE_PATH + SECOND_CLASS + JAR_EXTENSION, DEFAULT_PACKAGE + SECOND_CLASS)
                            .newInstance())
                            .getInfo());
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }

    }
}