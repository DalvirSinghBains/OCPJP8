package br.com.dvlima.ocpjp8.topic01._java_class_design._04_hashcode_equals_and_tostring_methods;

class Point {
	private int xPos;
	private int yPos;

	public Point(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public static void main(String[] args) {
		// invokes the toString method
		System.out.println(new Point(10, 20));// x = 10, y = 20

		Object obj = new Point(10, 20);
		System.out.println(obj);// x = 10, y = 20

		Point p1 = new Point(10, 20);
		Point p2 = new Point(50, 100);
		Point p3 = new Point(10, 20);
		System.out.println("p1 equals p2 is " + p1.equals(p2));// false
		System.out.println("p1 equals p3 is " + p1.equals(p3));// true

		Object p4 = new Point(10, 20);
		Object p5 = new Point(50, 100);
		Object p6 = new Point(10, 20);
		System.out.println("p1 equals p2 is " + p4.equals(p5));
		System.out.println("p1 equals p3 is " + p4.equals(p6));
	}

	
	public String toString() {
		return "x = " + xPos + ", y = " + yPos;
	}

	// Override the equals method to perform
	// "deep" comparison of two Point objects
	@Override
	public boolean equals(Object o) {
		if(o instanceof Point) {
			Point other = (Point) o; 

			// two points are equal only if their x and y positions are equal
			if (xPos == other.xPos && yPos == other.yPos) {
				return true;
			} else {
				return false;
			}	
		}
		
		return false;
		
	}

}
