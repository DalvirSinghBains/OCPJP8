package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class._04_bounded_type_parameters;

//DEFINING BOUNDED TYPE PARAMETERS
public class Tree<T extends Gift> {
//class Parcel<T implements Serializable>{} won't compile 'implementes'
	private T t;

	public void set(T t) {
		this.t = t;
	}

	public void shipParcel() {
		if (t.getWeight() > 10)
			System.out.println("Ship by courier ABC");
		else
			System.out.println("Ship by courier XYZ");
	}
}
