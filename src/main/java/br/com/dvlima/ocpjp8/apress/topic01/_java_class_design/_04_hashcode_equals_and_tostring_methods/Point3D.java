package br.com.dvlima.ocpjp8.apress.topic01._java_class_design._04_hashcode_equals_and_tostring_methods;

public class Point3D extends Point2D {

	private int zPos;

	public Point3D(int x, int y, int z) {
		super(x, y);
		this.zPos = z;
	}

	public static void main(String[] args) {
		System.out.println(new Point3D(10, 20, 30));
	}

	@Override
	public String toString() {
		return super.toString() + ", z = " + this.zPos;//x = 10, y = 20, z = 30
	}
}
