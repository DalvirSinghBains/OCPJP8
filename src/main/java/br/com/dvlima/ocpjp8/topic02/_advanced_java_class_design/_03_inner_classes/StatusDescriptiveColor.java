package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._03_inner_classes;

/** Anonymous Inner Classes */
//• Anonymous classes are defined in the new expression itself.

//• You cannot explicitly extend a class or explicitly implement interfaces when defining
//an anonymous class.
public class StatusDescriptiveColor {

	static Shape.Color getDescriptiveColor(final Shape.Color color) {
		// note the use of anonymous inner classes here
		// -- specifically, there is no name for the class and we construct
		// and use the class "on the fly" in the return statement!
		return new Shape.Color() {
			public String toString() {
				return "You selected a color with RGB values" + color;
			}
		};
	}

	public static void main(String[] args) {
		Shape.Color descriptiveColor = StatusReporter.getDescriptiveColor(new Shape.Color(0, 0, 0));
		System.out.println(descriptiveColor);
	}

}
