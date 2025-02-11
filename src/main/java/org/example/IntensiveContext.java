package org.example;

import classes.AnnotatedClass;
import utilClasses.IntensiveBeanFactory;
//import utilClasses.SearchService;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
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


    private static class SearchService {

        private static final char PKG_SEPARATOR = '.';

        private static final char DIR_SEPARATOR = '/';

        private static final String CLASS_FILE_SUFFIX = ".class";

        private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

        /**
         * Возвращает список классов в пакете
         */
        public static List<Class<?>> find(String scannedPackage) {
            String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
            URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
            if (scannedUrl == null) {
                throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
            }
            File scannedDir = new File(scannedUrl.getFile());
            List<Class<?>> classes = new ArrayList<>();
            for (File file : scannedDir.listFiles()) {
                classes.addAll(find(file, scannedPackage));
            }
            return classes;
        }

        private static List<Class<?>> find(File file, String scannedPackage) {
            List<Class<?>> classes = new ArrayList<>();
            String resource = scannedPackage + PKG_SEPARATOR + file.getName();
            if (file.isDirectory()) {
                for (File child : file.listFiles()) {
                    classes.addAll(find(child, resource));
                }
            } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
                int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
                String className = resource.substring(0, endIndex);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException ignore) {
                }
            }
            return classes;
        }
    }

}
