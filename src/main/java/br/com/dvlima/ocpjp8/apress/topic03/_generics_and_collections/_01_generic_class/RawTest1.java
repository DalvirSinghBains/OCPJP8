package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RawTest1 {
	public static void main(String[] args) {
		List list = new LinkedList();
		list.add("First");
		list.add("Second");
		List<String> strList = list;
		//strList.add(10);        // #1: generates compiler error
		for(Iterator<String> itemItr = strList.iterator(); itemItr.hasNext();)
			System.out.println("Item: " + itemItr.next());
		
		List<String> strList2 = new LinkedList<>();
        strList2.add("First");
        strList2.add("Second");
        List list2 = strList2; //#2
        for(Iterator<String> itemItr = list2.iterator(); itemItr.hasNext();)
            System.out.println("Item: " + itemItr.next());
	}
}
