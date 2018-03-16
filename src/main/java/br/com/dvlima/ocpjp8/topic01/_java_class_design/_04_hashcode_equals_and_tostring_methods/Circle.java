package br.com.dvlima.ocpjp8.topic01._java_class_design._04_hashcode_equals_and_tostring_methods;

class Circle {

	private int xPos, yPos, radius;

	public Circle(int x, int y, int r) {
		xPos = x;
		yPos = y;
		radius = r;
	}

	@Override
	public int hashCode() {
		return (7 * xPos) ^ (11 * yPos) ^ (53 * yPos);
	}

	@Override
	public boolean equals(Object arg) {

		if (arg == null)
			return false;

		if (this == arg)
			return true;

		if (arg instanceof Circle) {
			Circle that = (Circle) arg;
			if ((this.xPos == that.xPos) && (this.yPos == that.yPos) && (this.radius == that.radius)) {
				return true;
			}
		}

		return false;
	}
}
