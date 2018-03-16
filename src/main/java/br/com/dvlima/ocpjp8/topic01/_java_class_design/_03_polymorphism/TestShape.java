package br.com.dvlima.ocpjp8.topic01._java_class_design._03_polymorphism;

class Shape {
	//Default implementation
	public double area() {return 0;}
}

class Circle extends Shape {
	private int radius;
	public Circle(int r) {radius = r;}
	public double area() {return Math.PI * radius * radius;}
}

class Square extends Shape {
	private int side;
	public Square(int a) {side = a;}
	public double area() {return side * side;}
}

public class TestShape {
	public static void main(String[] args) {
		//Method area() is called based on the dynamic type of Shape.
		Shape shape1 = new Circle(10);
		System.out.println(shape1.area());	//314.1592653589793
		Shape shape2 = new Square(10);
		System.out.println(shape2.area());	//100.0
	}
}
