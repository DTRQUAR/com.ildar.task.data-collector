package com.data.util;

import java.util.Collection;

public class CollectionsUtil {

    /**
     * Возвращает случайный элемент из коллекции
     *
     * @param collection коллекция
     * @return элемент коллекции
     */
    public static <T> T random(Collection<T> collection) {
        int num = (int) (Math.random() * collection.size());
        for (T t : collection) if (--num < 0) return t;
        throw new AssertionError();
    }

}
