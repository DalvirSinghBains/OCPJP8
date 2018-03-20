package br.com.dvlima.ocpjp8.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {

		String pangram = "the quick brown fox jumps over the lazy dog";
		Set<Character> aToZee = new TreeSet<Character>();

		for (char gram : pangram.toCharArray())
			aToZee.add(gram);

		System.out.println("The pangram is: " + pangram);
		System.out.print("Sorted pangram characters are: " + aToZee);
		// The pangram is: the quick brown fox jumps over the lazy dog
		// Sorted pangram characters are:
		// [ , a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x,
		// y, z]
	}

}
