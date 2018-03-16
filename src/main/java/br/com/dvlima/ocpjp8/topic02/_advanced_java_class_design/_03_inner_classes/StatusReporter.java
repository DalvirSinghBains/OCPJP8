package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._03_inner_classes;

/** Local Inner Classes */
//• You can create a non-static local class inside a body of code. Interfaces cannot have
//local classes, and you cannot create local interfaces.

//• Local classes are accessible only from the body of the code in which the class is
//defined. The local classes are completely inaccessible outside the body of the code in
//which the class is defined.

//• You can extend a class or implement interfaces while defining a local class.

//• A local class can access all the variables available in the body of the code in which it
//is defined. Variables accessed by local inner classes are considered effectively final.
class StatusReporter {

	static Shape.Color getDescriptiveColor(final Shape.Color color) {
		// local class DescriptiveColor that extends Shape.Color class
		class DescriptiveColor extends Shape.Color {
			public String toString() {
				return "You selected a color with RGB values" + color;
			}
		}
		return new DescriptiveColor();
	}

	public static void main(String[] args) {
		Shape.Color color = StatusReporter.getDescriptiveColor(new Shape.Color(0, 0, 0));
		System.out.println(color);
	}
}
