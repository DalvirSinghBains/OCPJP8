package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class._05_using_wildcards;

public class Skate extends PersonalGift {

	private String name;

	public Skate(String name) {
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
		return "Skate [name=" + name + "]";
	}
}
