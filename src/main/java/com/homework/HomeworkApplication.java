package com.homework;


import com.homework.collection.CustomArrayList;
import com.homework.entity.Employee;
import java.util.Comparator;

public class HomeworkApplication {
    public final static String EXCEPTION_IN = "Error in";

    public static void main(String[] args) {
        testAddAndSize();
        testAddAtIndex();
        testRemoveByIndex();
        testRemoveByObject();
        testIsEmpty();
        testGet();
        testSort();

        System.out.println("All tests are complite!");

    }

    private static void testAddAndSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assert list.size() == 0 : (EXCEPTION_IN + " size()");

        list.add(1);
        list.add(2);
        list.add(3);

        assert list.size() == 3 : EXCEPTION_IN + " add() or size()";
        assert list.get(0) == 1 : EXCEPTION_IN + " get()";
        assert list.get(1) == 2 : EXCEPTION_IN + " get()";
        assert list.get(2) == 3 : EXCEPTION_IN + " get()";
    }

    private static void testAddAtIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(3);

        list.add(1, 2);

        assert list.size() == 3 : EXCEPTION_IN + " add(int index, T element)";
        assert list.get(0) == 1 : EXCEPTION_IN + " методе get()";
        assert list.get(1) == 2 : EXCEPTION_IN + " методе get()";
        assert list.get(2) == 3 : EXCEPTION_IN + " методе get()";
    }

    private static void testRemoveByIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int removedElement = list.remove(1);

        assert removedElement == 2 : EXCEPTION_IN + " методе remove(int index)";
        assert list.size() == 2 : EXCEPTION_IN + " методе remove(int index)";
        assert list.get(0) == 1 : EXCEPTION_IN + " get() после удаления элемента";
        assert list.get(1) == 3 : EXCEPTION_IN + " get() после удаления элемента";
    }

    private static void testRemoveByObject() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean result = list.remove((Integer) 2);

        assert result : EXCEPTION_IN + " методе remove(Object o)";
        assert list.size() == 2 : EXCEPTION_IN + " методе remove(Object o)";
        assert list.get(0) == 1 : EXCEPTION_IN + " методе get() после удаления элемента";
        assert list.get(1) == 3 : EXCEPTION_IN + " get() после удаления элемента";

        result = list.remove((Integer) 4);// элемент не существует
        assert !result : EXCEPTION_IN + " deliting empty element.";
    }

    private static void testIsEmpty() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assert list.isEmpty() : EXCEPTION_IN + " testIsEmpty";

        list.add(1);
        assert !list.isEmpty() : EXCEPTION_IN + " testIsEmpty";

    }

    private static void testGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        try {
            list.get(-1);
            assert false : "expected exception IndexOutOfBoundsException for negativenumber.";
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list.get(list.size());
            assert false : "expected exception IndexOutOfBoundsException list.size()";
        } catch (IndexOutOfBoundsException e) {

        }
        list.add(10);
        int element = list.get(0);
        assert element == 10 : EXCEPTION_IN + " getting element";
    }

    private static void testSort() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        
        list.add(3);
        list.add(15);
        list.add(2);

        Comparator<Integer> comparator = Comparator.naturalOrder();
        list.sort(comparator);// сортируем список

        assert list.size() == 3 : EXCEPTION_IN + " testSort array length";
        assert list.get(0) == 2 : EXCEPTION_IN + " testSort";
        assert list.get(1) == 3 : EXCEPTION_IN + " testSort";
        assert list.get(2) == 15 : EXCEPTION_IN + " testSort";
    }
}

/**
 * EmailComparator implements Comporator
 */
class IdComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}

class EmailComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getEmail().compareTo(o2.getEmail());
    }

}

class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }

}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.getSalary(), o2.getSalary());
    }

}
