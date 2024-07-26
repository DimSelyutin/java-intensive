package com.homework.collection;

import java.util.AbstractList;
import java.util.Collection;
import java.util.stream.Collectors;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Arrays;

/**
 * CustomArrayList
 * 
 * Реализация собственного списка на основе массива с интерфейсами List,
 * Cloneable и Serializable
 */
public class CustomArrayList<T> extends AbstractList<T> {
    // Массив для хранения элементов списка
    private T[] list;
    // Значение по умолчанию для емкости массива
    private static final int DEFAULT_CAPACITY = 10;

    // Текущий размер списка (количество элементов)
    private int size;

    @SuppressWarnings("unchecked")
    public CustomArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong capacity!");
        }
        // Создаем массив с заданной емкостью
        list = (T[]) new Object[capacity];
    }

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean add(T element) {
        if (size >= list.length) {
            resize();
        }
        list[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Wrong Index");
        }
        if (size >= list.length) {
            resize();
        }
        // Сдвигаем элементы вправо начиная с позиции index
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = element;// Вставляем элемент на позицию index
        size++;
    }

    @Override
    public void clear() {
        Arrays.fill(list, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong Index");
        }
        T obj = list[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(list, index + 1, list, index, numMoved);
        }
        list[--size] = null; // Уменьшаем размер и обнуляем последний элемент
        return obj;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(list[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }

        boolean modified = false;

        for (Iterator<?> iter = iterator(); iter.hasNext();) {
            if (c.contains(iter.next())) {
                iter.remove();
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong Index");
        }

        return list[index];
    }

    @Override
    public void sort(Comparator<? super T> c) {
        if (size == 0 || c == null) {
            return;
        }
        T[] result = Arrays.copyOf(list, size);
        mergeSort(result, c);
        System.arraycopy(result, 0, list, 0, size); // Копирование отсортированных данных назад в основной массив

    }

    private void mergeSort(T[] resArray, Comparator<? super T> c) {
        if (resArray.length < 2) { // Базовый случай для рекурсии
            return;
        }

        int mid = resArray.length / 2;

        T[] leftArray = Arrays.copyOfRange(resArray, 0, mid);
        T[] rightArray = Arrays.copyOfRange(resArray, mid, resArray.length);

        mergeSort(leftArray, c);
        mergeSort(rightArray, c);
        merge(resArray, leftArray, rightArray, c);
    }

    private void merge(T[] resArray, T[] leftArray, T[] rightArray,
            Comparator<? super T> c) {
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;

        int lIndex = 0;
        int rIndex = 0;
        int mainIndex = 0;

        while (lIndex < leftSize && rIndex < rightSize) {
            if (c.compare(leftArray[lIndex], rightArray[rIndex]) <= 0) {
                resArray[mainIndex++] = leftArray[lIndex++];
            } else {
                resArray[mainIndex++] = rightArray[rIndex++];
            }
        }

        while (lIndex < leftSize) {
            resArray[mainIndex++] = leftArray[lIndex++];
        }

        while (rIndex < rightSize) {
            resArray[mainIndex++] = rightArray[rIndex++];
        }
    }

    // Helpers
    private void resize() {
        // Создадим новый массив с увеличенным размером (например, вдвое больше)
        T[] newList = (T[]) new Object[list.length * 2];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }

    @Override
    public String toString() {
        return Arrays.stream(list, 0, size) // ограничиваем поток текущим размером списка
                .map(item -> item == null ? "null" : item.toString()) // обрабатываем null-элементы
                .collect(Collectors.joining(", ", "[", "]")); // объединяем элементы в строку с форматированием
    }

}