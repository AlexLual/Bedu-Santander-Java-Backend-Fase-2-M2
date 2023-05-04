package org.bedu.postwork.javase2project.tools;

import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class MergeSortAlgorithm {

    public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList) {
        return sort(unsortedList, Comparator.naturalOrder());
    }

    public static <T> List<T> sort(List<T> unsortedList, Comparator<T> comparator) {
        if (unsortedList.size() <= 1) {
            return unsortedList;
        }

        int middleElement = unsortedList.size() / 2;

        List<T> leftList = sort(unsortedList.subList(0, middleElement), comparator);
        List<T> rightList = sort(unsortedList.subList(middleElement, unsortedList.size()), comparator);

        return merge(leftList, rightList, comparator);
    }

    private static <T> List<T> merge(List<T> leftList, List<T> rightList, Comparator<T> comparator) {
        List<T> merged = new ArrayList<>(leftList.size() + rightList.size());

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (comparator.compare(leftList.get(leftIndex), rightList.get(rightIndex)) < 0) {
                merged.add(leftList.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(rightList.get(rightIndex));
                rightIndex++;
            }
        }

        merged.addAll(leftList.subList(leftIndex, leftList.size()));
        merged.addAll(rightList.subList(rightIndex, rightList.size()));

        return merged;
    }
}
/*
1. Se reemplazaron los objetos Deque con objetos List y utilizo un índice para recorrer las listas en lugar de utilizar pop().
    Esto mejora la legibilidad y evita la necesidad de copiar datos a nuevas estructuras de datos.
2. Se cambiaron la condición en la función sort de unsortedList.size() == 1 a unsortedList.size() <= 1.
    Esto maneja el caso cuando se pasa una lista vacía y evita la creación de nuevas listas innecesarias.
3. Se agregaron el tamaño inicial de la lista merged en la función merge para evitar redimensionamientos innecesarios.
 */