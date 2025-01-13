package classes;

import org.example.IntensiveComponent;

/**
 * Простой класс, помеченный аннотацией @IntensiveComponent. ☕
 */
@IntensiveComponent
public class AnnotatedClass implements SomeInterface{


    /**
     Метод выводит на консоль текст.
     */
    public void someMethod()
    {
        System.out.println("i'm an annotated class!");
    }

    @Override
    public String toString() {
        return "its an annotated class";
    }
}
