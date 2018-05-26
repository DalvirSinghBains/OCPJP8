package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class;

public class BoxPrinter<T> {

	private T clazz;

	public BoxPrinter(T arg) {
		this.clazz = arg;
	}

	@Override
	public String toString() {
		return "BoxPrinter [" + clazz + "]";
	}

	public static void main(String[] args) {
		BoxPrinter<Integer> value1 = new BoxPrinter<Integer>(new Integer(10));
		System.out.println(value1);

		BoxPrinter<String> value2 = new BoxPrinter<String>("Hello world");
		System.out.println(value2);
	}
}
