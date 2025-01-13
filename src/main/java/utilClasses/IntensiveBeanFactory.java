package utilClasses;

import org.example.IntensiveComponent;
import exceptions.MoreThenOneImplementationOfInterfaceException;
import exceptions.NoImplementationOfAnInterfaceInPackageException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Предоставляет функционал для создания объектов классов, помеченных аннотацией @IntensiveComponent. ☕
 */
public class IntensiveBeanFactory {

    /**
     * Создает объект переданного класса. ☕
     *
     * @param objClass Класс, объект которого необходимо создать
     * @param classesList Список классов, среди которых ищется наследник.
     * @return Созданный объект данного класса.
     */
    public static Object createBean(Class objClass, List<Class<?>> classesList) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MoreThenOneImplementationOfInterfaceException, NoImplementationOfAnInterfaceInPackageException {

        Class specificClass = objClass;

        if (objClass.isAnnotationPresent(IntensiveComponent.class) && classesList.contains(objClass)) {
            if (objClass.isInterface()) {
                Class oneImpl = oneImplementation(objClass, classesList);
                specificClass = oneImpl;
            }

            Object createdObject = createObjectOfClass(specificClass);
            Field[] fields = specificClass.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(createdObject, createBean(field.getType(), classesList));
                }
            }
            return createdObject;

        }

        else return null;
    }

    private static Object createObjectOfClass(Class objClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return Class.forName(objClass.getName()).newInstance();
    }

    /**
     * Получает класс, реализующий заданный интерфейс. Если классов, реализующих интерфейс, нет, или же его реализуют более одного класса, выбрасывает исключение. ☕
     *
     * @param currentInterface Интерфейс, чьего наследника небоходимо получить.
     * @param allClasses Список классов, среди которых ищется наследник.
     * @return Класс, который реализует заданный интерфейс.
     */
    private static Class oneImplementation(Class currentInterface, List<Class<?>> allClasses) throws MoreThenOneImplementationOfInterfaceException, NoImplementationOfAnInterfaceInPackageException {
        int counter = 0;
        Class resultClass = null;

        for (Class curClass: allClasses)
        {
            Annotation an = curClass.getAnnotation(IntensiveComponent.class);
            if ((currentInterface.isAssignableFrom(curClass)) && !curClass.equals(currentInterface) && an!=null) {
                counter++;
                if (counter > 1)
                    throw new MoreThenOneImplementationOfInterfaceException("There is more then one implementation of " + currentInterface + " interface!");
                resultClass = curClass;

            }
        }
        if (resultClass != null) return resultClass;
        throw new NoImplementationOfAnInterfaceInPackageException("There is no implementation of " + currentInterface + " interface!");
    }
}
