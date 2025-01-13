package classes;

import org.example.IntensiveComponent;

/**
 * Класс, аннотированный аннотацией @IntensiveComponent и имеющий поля: annotatedClassField, являющееся объектом класcа,
 * аннотированного той же аннотацией, чье поле также имеет эту аннотацию (), и notAnnotatedClassField, не помеченного
 * этой аннотацией.
 * @see
 */
@IntensiveComponent
public class AnnotatedClassWithAnnotatedField1 implements SomeInterface{

    private AnnotatedClassWithAnnotatedField field1;
    private NotAnnotatedClass field2;

    @Override
    public String toString() {
        return "field1: " + field1 + ", field2: " + field2;
    }

    /**
     Метод выводит на консоль текст.
     */
    public void someMethod() {
        System.out.println("i'm an annotated class with annotated field1 and not annotated field2");
    }
}
