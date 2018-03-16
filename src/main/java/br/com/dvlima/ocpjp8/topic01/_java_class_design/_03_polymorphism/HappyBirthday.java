package br.com.dvlima.ocpjp8.topic01._java_class_design._03_polymorphism;

class HappyBirthday {
	//overloaded wish method with String as an argument
	public static void wish(String name) {
		System.out.println("Happy birthday "+ name +"!");
	}
	
	//Overloaded wish mthod with no arguments 
	//this method in tun invokes wish(String) method
	public static void wish() {
		wish("to you");
	}
	
	public static void main(String[] args) {
		wish();						//Happy birthday to you!
		wish("dear James Gosling");	//Happy birthday dear James Gosling!
	}
}
