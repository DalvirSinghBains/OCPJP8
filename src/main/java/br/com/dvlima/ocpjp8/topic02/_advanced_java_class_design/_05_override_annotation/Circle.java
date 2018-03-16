package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._05_override_annotation;

//• Optionally, you can use the @Override annotation to indicate that a method is
//overriding a method from its base type(s). In this case, the roll method is overridden
//in the Circle class and makes use of the @Override annotation.
abstract class Shape {
	abstract double area();

	private Shape parentShape;

	public void setParentShape(Shape shape) {
		parentShape = shape;
	}

	public Shape getParentShape() {
		return parentShape;
	}
}

// Rollable interface can be implemented by circular shapes such as Circle
interface Rollable {
	void roll(float degree);
}

abstract class CircularShape extends Shape implements Rollable {
}

// Circle is a concrete class that is-a subtype of CircularShape;
// you can roll it and hence implements Rollable through CircularShape base
// class
public class Circle extends CircularShape {

	private int xPos, yPos, radius;

	public Circle(int x, int y, int r) {
		xPos = x;
		yPos = y;
		radius = r;
	}

	@Override
	public void roll(float degree) {
		System.out.printf("rolling circle by %f degrees", degree);
	}

	@Override
	double area() {
		return Math.PI * radius * radius;
	}

	public static void main(String[] s) {
		Circle circle = new Circle(10, 10, 20);
		circle.roll(45);
	}
}
