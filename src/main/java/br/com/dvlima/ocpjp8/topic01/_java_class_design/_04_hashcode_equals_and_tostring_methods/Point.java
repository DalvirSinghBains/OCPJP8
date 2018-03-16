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
	}

	public String toString() {
		return "x = " + xPos + ", y = " + yPos;
	}

}
