package org.example;

import classes.AnnotatedClass;
import utilClasses.IntensiveBeanFactory;
import utilClasses.SearchService;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Позволяет получать объекты классов, помеченных аннотацией @IntensiveComponent. ☕
 */
public class IntensiveContext {

    private List<Class<?>> classesList;

    IntensiveContext(String packageName)
    {
        classesList = SearchService.find(packageName);
    }

    /**
     * Получает объект из контекста, если это возможно. ☕
     *
     * @param type Класс, объект которого должен быть создан.
     * @return Созданный объект класса type.
     */
    public <T> Object getObject(Class<T> type) throws Exception {
        return IntensiveBeanFactory.createBean(type, classesList);
    }


}
