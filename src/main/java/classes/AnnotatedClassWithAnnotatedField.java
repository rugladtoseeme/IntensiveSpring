package classes;

import org.example.IntensiveComponent;

/**
 * Класс, аннотированный аннотацией @IntensiveComponent и имеющий поля: annotatedClassField, являющееся объектом класcа,
 * аннотированного той же аннотацией и notAnnotatedClassField, не помеченного этой аннотацией.
 */
@IntensiveComponent
public class AnnotatedClassWithAnnotatedField implements SomeInterface{

    private AnnotatedClass annotatedClassField;
    private NotAnnotatedClass notAnnotatedClassField;

    @Override
    public String toString() {
        return "field1: " + annotatedClassField + ", field2: " + notAnnotatedClassField;
    }

    /**
     Метод выводит на консоль текст.
     */
    public void someMethod() {
        System.out.println("i'm an annotated class with annotated field1 and not annotated field2");
    }
}
