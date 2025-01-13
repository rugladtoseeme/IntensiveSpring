package org.example;

import classes.*;

import java.nio.file.NoSuchFileException;

public class Main {
    public static void main(String[] args) {

        IntensiveContext ic = new IntensiveContext("classes");
        try {
            SomeInterface o = (AnnotatedClassWithAnnotatedField1) ic.getObject(AnnotatedClassWithAnnotatedField1.class);
            o.someMethod();
            System.out.println(o);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}