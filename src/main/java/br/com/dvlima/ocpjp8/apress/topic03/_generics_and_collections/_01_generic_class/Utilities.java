package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public static <T> void fill(List<T> list, T val) {
		for(int i = 0; i < list.size(); i++)
            list.set(i, val);
	}
	
	public static void main(String[] args) {
		List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(20);
        System.out.println("The original list is: " + intList);
        Utilities.fill(intList, 100);
        System.out.println("The list after calling fill() is: " + intList);	
	}
}
