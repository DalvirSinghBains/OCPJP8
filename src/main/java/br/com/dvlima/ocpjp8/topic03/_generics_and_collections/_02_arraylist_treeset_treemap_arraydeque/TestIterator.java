package br.com.dvlima.ocpjp8.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque;

import java.util.ArrayList;
import java.util.Iterator;

public class TestIterator {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			list.add(i);

		System.out.println(list);
		// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

		Iterator<Integer> itr = list.iterator();

		// The problem is that you haven’t called next() before calling remove()
		// while (itr.hasNext()) {
		// itr.remove();
		// Exception in thread "main"
		// java.lang.IllegalStateException
		// at java.util.ArrayList$Itr.remove(ArrayList.java:870)
		// }

		// Let’s fix this program by calling next() before calling remove().
		// Here is the relevant part of the code:
		while (itr.hasNext()) {
			itr.next();
			itr.remove();
		}

		// remember that next() needs to be called before calling remove() in an
		// Iterator; otherwise, you’ll get an IllegalStateException. similarly, calling
		// remove() in subsequent statements without calling next() between these
		// statements will also result in this exception. in short, any modifications to
		// the underlying container while an iterator is traversing through the
		// container will result in this exception

		System.out.println("List after removing all elements " + list);
	}
}
