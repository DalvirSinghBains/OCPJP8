package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class._05_using_wildcards;

import java.util.ArrayList;
import java.util.List;

public class WishListTest {

	/*
	 * wrapGift will accept list of any unknown type
	 */
	public static void wrapGift(List<?> list) {
		/*
		 * Type of variable item is Object, superclass of all objects
		 */
		for (Object item : list) {
			System.out.println(item);
		}
	}

	public static void wrapPersonalGift(List<? extends PersonalGift> listPersonalGifts) {
		/*
		 * the loop variable item can be of type Gift or its subtype, Object
		 */
		listPersonalGifts.forEach(item -> System.out.println(item));
	}

	// String class is final, but it's acceptable to define upper-bounded wildcard
	// extend class String
	public static void wrapGiftString(List<? extends String> gifts) {

	}

	public static void main(String[] args) {
		//UPPER-BOUNDED WILDCARDS
		
		List<Bike> bikes = new ArrayList<>();
		bikes.add(new Bike("Caloi"));
		bikes.add(new Bike("Schwinn"));

		List<String> stringList = new ArrayList<>();
		stringList.add("A");
		stringList.add("B");

		wrapGift(bikes);
		wrapGift(stringList);

		List<Skate> sk8 = new ArrayList<Skate>();
		sk8.add(new Skate("Longboard"));
		sk8.add(new Skate("Waveboard"));

		wrapPersonalGift(sk8);
		
		//LOWER-BOUNDED WILDCARDS
		
		//Though Gift isn't its own superclass, this assignment is valid.
		List<? super Gift> list1 = new ArrayList<Gift>();
		
		//Gift extends Object
		List<? super Gift> list2 = new ArrayList<Object>();
		
		//Won't compile; gift doesn't extend Phone
		//List<? super Gift> list3 = new ArrayList<Phone>();
		
		//Valid; Phone extends Gift
		List<? super Phone> list4 = new ArrayList<Gift>();
		
		List<? super Gift> list = new ArrayList<>();
		list.add(new Gift());
		list.add(new Book());
		list.add(new Phone());
		//list.add(new Object());  Won't compile
		
		for (Object obj : list) System.out.println(obj);
		
	}

}
