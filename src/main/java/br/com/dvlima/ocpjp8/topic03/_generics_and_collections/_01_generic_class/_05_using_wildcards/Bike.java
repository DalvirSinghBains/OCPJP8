package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._05_using_wildcards;

public class Bike {
	
	private String name;

	public Bike(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bike [name=" + name + "]";
	}
}
