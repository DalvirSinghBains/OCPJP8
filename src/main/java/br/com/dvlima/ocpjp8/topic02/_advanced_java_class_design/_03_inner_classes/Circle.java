package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._03_inner_classes;

/**Inner Classes*/
//• The accessibility (public, protected, etc.) of the inner class is defined by the
//outer class.

//• Just like top-level classes, an inner class can extend a class or can implement
//interfaces. Similarly, other classes can extend an inner class, and other classes or
//interfaces can extend or implement an inner interface.

//• An inner class can be declared final or abstract.

//• Inner classes can have inner classes, but you’ll have a hard time reading or
//understanding such complex nesting of classes. (Meaning: Avoid them!)
class Circle {

	class Point {
		private int xPos;
		private int yPos;

		// you can provide constructor for an inner class like this
		public Point(int x, int y) {
			xPos = x;
			yPos = y;
		}

		// the inner class is like any other class - you can override methods here
		@Override
		public String toString() {
			return "(" + xPos + "," + yPos + ")";
		}
	}
	
	private Point center;
	private int radius;
	
	public Circle(int x, int y, int r) {
		//note how to make use of the inner class to instantiate it
		center = this.new Point(x, y);
		radius = r;
	}

	@Override
	public String toString() {
		return "mid point = " + center + " and radius = " + radius;
	}
	
	public static void main(String[] args) {
		System.out.println(new Circle(10, 10, 20));
	}
}

