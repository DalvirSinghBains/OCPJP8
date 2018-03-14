package br.com.dvlima.ocpjp8.topic02._04_ocpjp7_enumerated_types;

/**
 * Created by danilolima on 08/03/18.
 */
public class IceCreamTest {

    //TIP: enum as a member of other class
    enum MyIceCreamTopLevelEnum {BEGINNER, INTERMEDIATE, EXPERT}

    //TIP: The following code won't compile
    /*
    void aMethod (){
        enum MyMethodEnum {BEGINNER}
    }
     */
    public static void main(String[] args) {
        IceCream.VANILLA.setColor("black and white");
        System.out.println(IceCream.VANILLA.getColor());
        System.out.println(IceCream.VANILLA);

        for (IceCream iceCream : IceCream.values()) {
            System.out.println(iceCream);
        }
    }

}
