package br.com.dvlima.ocpjp8.apress.topic02._advanced_java_class_design._04_ocpjp7_enumerated_types;

/**
 * Created by danilolima on 08/03/18.
 */
public enum IceCream {

    // TIP: this constant list must be the first in the enum definition
    // TIP: followed by semicolon.

    VANILLA("white"),
    STRAWBERRY("pink"),
    WALNUT("brown") {
        @Override
        public String toString() {
            int value = 0;
            return super.toString();
        }

        @Override
        public String getColor() {
            return super.getColor();
        }

        /*
        TIP: this method can't be executed
        public String flavor(){
            return "great!";
        }
        */
    },
    CHOCOLATE("dark brown");

    IceCream(String color){
        this.color = color;
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "color='" + color + '\'' +
                '}';
    }
}
