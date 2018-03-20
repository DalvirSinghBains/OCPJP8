package br.com.dvlima.ocpjp8.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque;

import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapTest {

	public static void main(String[] args) {
		NavigableMap<Double, String> examScores = new TreeMap<Double, String>();

		examScores.put(90.3, "Sophia");
		examScores.put(20.4, "Isabella");
		examScores.put(10.6, "Emma");
		examScores.put(50.0, "Olivea");

		System.out.println("The data in the map is: " + examScores);
		System.out.println("The data descending order is: " + examScores.descendingMap());
		System.out.println("Details of those who passed the exam: " + examScores.tailMap(20.5));
		System.out.println("The lowest mark is: " + examScores.firstEntry());

		// The data in the map is: {10.6=Emma, 20.4=Isabella, 50.0=Olivea, 90.3=Sophia}
		// The data descending order is:
		// {90.3=Sophia, 50.0=Olivea, 20.4=Isabella, 10.6=Emma}
		// Details of those who passed the exam: {50.0=Olivea, 90.3=Sophia}
		// The lowest mark is: 10.6=Emma
	}

}
