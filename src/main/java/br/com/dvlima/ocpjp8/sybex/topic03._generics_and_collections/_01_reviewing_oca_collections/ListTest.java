package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._01_reviewing_oca_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        String[] numbersArray = {"One", "Two"};
        List<String> numbersList = Arrays.asList(numbersArray);

        // print(numbersArray, numbersList);// [One, Two] One Two

        numbersList.set(1, "Zero");
        numbersArray[0] = "Four";

       // print(numbersArray, numbersList);// [Four, Zero] Four Zero

        int[] numbersA = {6, 9, 1, 8};
        Arrays.sort(numbersA); // [1,6,8,9]
        System.out.println(Arrays.binarySearch(numbersA, 6)); // 1
        System.out.println(Arrays.binarySearch(numbersA, 3)); // -2

        List<Integer> list = Arrays.asList(9, 7, 5, 3);
        Collections.sort(list); // [3, 5, 7, 9]
        System.out.println(Collections.binarySearch(list, 3)); // 0
        System.out.println(Collections.binarySearch(list, 2)); // -1

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(new Integer(3));
        numbers.add(new Integer(5));
        numbers.remove(1);//index
        numbers.remove(new Integer(5));//object
        System.out.println(numbers);// [1]

        // Java also converts the wrapper classes to primitives via unboxing:
        int num = numbers.get(0);
    }

    private static void print(String[] numbersArray, List<String> numbersList) {
        System.out.println(Arrays.toString(numbersArray));
        System.out.println(numbersList);
    }

}
