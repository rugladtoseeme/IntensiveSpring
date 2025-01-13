package classes;

/**
 * Простой класс, не помеченный аннотацией @IntensiveComponent. ☕
 */
public class NotAnnotatedClass implements SomeInterface {

    /**
     Метод выводит на консоль текст.
     */
    public void someMethod()
    {
        System.out.println("i'm a not annotated class!");
    }

    @Override
    public String toString() {
        return "its not an annotated class!";
    }
}
