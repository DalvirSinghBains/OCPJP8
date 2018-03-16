package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._03_inner_classes;

class ColorTest {

	public static void main(String[] args) {
		// since Color is a static nested class,
		// we access it using the name of the outer class, as in Shape.Color
		// note that we do not (and cannot) instantiate Shape class for using Color classy
		
		Shape.Color white = new Shape.Color(255, 255, 255);
		System.out.println("White color has values:" + white);
	}

}
