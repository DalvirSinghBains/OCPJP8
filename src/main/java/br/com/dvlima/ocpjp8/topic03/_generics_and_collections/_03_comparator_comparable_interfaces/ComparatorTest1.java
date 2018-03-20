package br.com.dvlima.ocpjp8.topic03._generics_and_collections._03_comparator_comparable_interfaces;

import java.util.Arrays;

public class ComparatorTest1 {

	public static void main(String[] args) {
		Student[] students = { //
				new Student("cs011", "Lennon  ", 3.1), //
				new Student("cs021", "McCartney", 3.4), //
				new Student("cs012", "Harrison ", 2.7), //
				new Student("cs022", "Starr ", 3.7) };//

		System.out.println("Before sorting by student ID");
		System.out.println("Student-ID \t  Name \t  CGPA (for 4.0) ");
		System.out.println(Arrays.toString(students));

		// When you call the sort() method, it calls the compareTo() method to compare
		// Student objects by their IDs. Since Student IDs are unique, it is a natural
		// comparison order that works well.
		Arrays.sort(students);
		// Collections.sort(Arrays.asList(students));

		System.out.println("After sorting by student ID");
		System.out.println("Student-ID \t  Name \t  CGPA (for 4.0) ");
		System.out.println(Arrays.toString(students));
	}

}

class Student implements Comparable<Student> {
	String id;
	String name;
	Double cgpa; // cumulative grade point average

	public Student(String studentId, String studentName, double studentCGPA) {
		id = studentId;
		name = studentName;
		cgpa = studentCGPA;
	}

	@Override
	public String toString() {
		return "\n " + id + "\t " + name + "\t " + cgpa;
	}

	@Override
	public int compareTo(Student that) {
		return this.id.compareTo(that.id);
	}
}
