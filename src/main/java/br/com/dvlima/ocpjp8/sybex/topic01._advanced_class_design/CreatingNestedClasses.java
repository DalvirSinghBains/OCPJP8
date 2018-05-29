package br.com.dvlima.ocpjp8.sybex.topic01._advanced_class_design;

public class CreatingNestedClasses {
}

//four types of nested classes.

//1 - Member Inner Classes
//Can be declared public, private, or protected or use default access Can extend any class and implement interfaces
//Can be abstract or final
//Cannot declare static fields or methods
//Can access members of the outer class including private members

class Outer {
    private String greeting = "Hi";

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.callInner();

        Inner inner = outer.new Inner();
        inner.go();
    }

    public void callInner() {
        Inner inner = new Inner();
        inner.go();
    }

    protected class Inner {
        public int repeat = 3;

        public void go() {
            for (int i = 0; i < repeat; i++)
                System.out.println(greeting);
        }
    }
}

//Inner classes can have the same variable names as outer classes.
class A {
    private int x = 10;

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
        c.allTheX();
        //30 30 20 10
    }

    class B {
        private int x = 20;

        class C {
            private int x = 30;

            public void allTheX() {
                System.out.println(x);
                System.out.println(this.x);
                System.out.println(B.this.x);
                System.out.println(A.this.x);
            }
        }
    }
}

//This following code looks weird but is legal:
class CaseOfThePrivateInterface {
    private interface Secret {
        void shh();
    }

    class DontTell implements Secret {
        public void shh() {
        }
    }
}

//2 - Local Inner Classes

//They do not have an access specifier.
//They cannot be declared static and cannot declare static fields or methods.
//They have access to all fields and methods of the enclosing class.
//They do not have access to local variables of a method unless those variables are final or effectively final.

class OuterTwo {

    int length = 5;

    public static void main(String[] args) {
        OuterTwo outer = new OuterTwo();
        outer.calculate();
    }

    public void calculate() {
        final int width = 20;

        class Inner {
            public void multiply() {
                System.out.println(length * width);
            }
        }

        Inner inner = new Inner();
        inner.multiply();
    }
}

//3 - Anonymous Inner Classes

class AnonInner {
    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 0;
            }
        };//NOTE include ;
        return basePrice - sale.dollarsOff();
    }

    public int admission() {
        SaleTomorrowOnly tomorrow = new SaleTomorrowOnly() {
            @Override
            public int dollarsOff() {
                return 0;
            }
        };
        return 0;
    }

    public int admission(int basePrice, SaleTomorrowOnly sale) {
        return basePrice - sale.dollarsOff();
    }

    public int pay() {
        return admission(5, new SaleTomorrowOnly() {
            @Override
            public int dollarsOff() {
                return 3;
            }
        });
    }

    interface SaleTomorrowOnly {
        int dollarsOff();
    }

    abstract class SaleTodayOnly {
        abstract int dollarsOff();
    }
}

// 4 - Static Nested Classes
//The nesting creates a namespace because the enclosing class name must be used to refer to it.
//It can be made private or use one of the other access modifiers to encapsulate it.
//The enclosing class can refer to the fields and methods of the static nested class.

class Enclosing {
    public static void main(String[] args) {
        Nested nested = new Nested();
        System.out.println(nested.price);
    }

    static class Nested {
        private int price = 6;
    }
}