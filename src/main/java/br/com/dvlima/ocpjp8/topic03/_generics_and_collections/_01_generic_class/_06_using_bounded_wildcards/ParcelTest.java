package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._06_using_bounded_wildcards;

public class ParcelTest {

	public static void main(String[] args) {
		//
		Parcel<String> parcel = new Parcel();
		//Won't compile
		//Parcel<> parcel = new Parcel<String>();
		
		Parcel<String> p1 = new Parcel<>();
		p1.<Integer>deliver(new Integer(10));
		
		//Won't compile. Can't use <> with generic method
		//p1.<>deliver(new Integer(10));
		
		p1.deliver("Hello");
	}
}
