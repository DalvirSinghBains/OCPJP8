package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class;

import java.util.ArrayList;
import java.util.List;

public class WildCardUse {

	static void printList(List<?> list) {
		for (Object element : list)
			System.out.println("[" + element + "]");
	}

	public static void main(String []args) {
	        List<Integer> list = new ArrayList<>();
	        list.add(10);
	        list.add(100);
	        printList(list);
	        
	        List<String> strList = new ArrayList<>();
	        strList.add("10");
	        strList.add("100");
	        printList(strList);
	}
}
