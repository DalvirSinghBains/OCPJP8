package br.com.dvlima.ocpjp8.sybex.topic01._advanced_class_design;

public class ReviewAdvancedClass {

}

class Bobcat {
    public void findDen() throws RuntimeException {
    }
}

class BobcatKitten extends Bobcat {
    public final void findDen() {//Override same signature
    }

    public void findDen(boolean b) {
    }

    public int findden() throws Exception {
        return 0;
    }
}

class HeavyAnimal { }
class Hippo extends HeavyAnimal { }
class Elephant extends HeavyAnimal { }
//HeavyAnimal hippo = new Hippo();
//boolean b1 = hippo instanceof Hippo;          true
//boolean b2 = hippo instanceof HeavyAnimal;    true
//boolean b3 = hippo instanceof Elephant;       false