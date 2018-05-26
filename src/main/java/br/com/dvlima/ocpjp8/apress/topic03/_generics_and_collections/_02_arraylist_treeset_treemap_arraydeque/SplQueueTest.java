package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque;

import java.util.ArrayDeque;
import java.util.Deque;

public class SplQueueTest {

	public static void main(String[] args) {
		SplQueue splQ = new SplQueue();
		splQ.addInQueue("Harrison");
		splQ.addInQueue("McCartney");
		splQ.addInQueue("Starr");
		splQ.addInQueue("Lennon");
		splQ.printQueue();
		splQ.removeFront();
		splQ.removeBack();
		splQ.printQueue();

		// Special queue contains: [Harrison, McCartney, Starr, Lennon]
		// Special queue contains: [McCartney, Starr]
	}
}

class SplQueue {
	private Deque<String> splQ = new ArrayDeque<>();

	void addInQueue(String customer) {
		splQ.addLast(customer);
	}

	void removeFront() {
		splQ.removeFirst();
	}

	void removeBack() {
		splQ.removeLast();
	}

	void printQueue() {
		System.out.println("Special queue contains: " + splQ);
	}
}