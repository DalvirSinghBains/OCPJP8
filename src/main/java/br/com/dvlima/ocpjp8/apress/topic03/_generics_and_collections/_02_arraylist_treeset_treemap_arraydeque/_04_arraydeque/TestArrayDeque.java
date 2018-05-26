package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque._04_arraydeque;

import java.util.*;

public class TestArrayDeque {
	public static void main(String[] args) {
		String strArray[] = {"A1","B2","C3"};
		
		ArrayDeque<String> arrDeque = 
				new ArrayDeque<String>(Arrays.asList(strArray));
		
		//push adds element at Deque beginning
		arrDeque.push("D4");
		
		//offer() adds element at Deque end
		arrDeque.offer("E5");
		
		//Can't add null to ArrayDeque throws java.lang.NullPointerException
		//arrDeque.push(null);
		
		
	}
}
