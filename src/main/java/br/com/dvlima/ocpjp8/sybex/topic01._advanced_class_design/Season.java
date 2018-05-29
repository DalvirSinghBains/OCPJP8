package br.com.dvlima.ocpjp8.sybex.topic01._advanced_class_design;

public enum Season {
    WINTER, SUMMER, SPRING {
        public void printHours() {
            System.out.println("short hours");
        }
    }, FALL
}

enum OnlyOne {
    ONCE(true) {
        public void printHours() {
            System.out.println("9am-3pm");
        }
    };

    OnlyOne(boolean b) {
        System.out.println("constructing");
    }

    public abstract void printHours();//force enum to override

    public void printTime() {
        System.out.println("Time");
    }
}

class TestEnum {
    public static void main(String[] args) {
        for (Season season : Season.values()) {
            System.out.println("name = " + season.name() + " ordinal = " + season.ordinal());
        }

        //create an enum from a String
        //Season s2 = Season.valueOf("summer"); // exception
        Season summer = Season.valueOf("SUMMER"); // SUMMER

        switch (summer) {
            case WINTER:
                System.out.println("Get out the sled!");
                break;
            case SUMMER:
                System.out.println("Time for the pool!");
                break;
            default:
                System.out.println("Is it summer yet?");
        }

        OnlyOne firstCall = OnlyOne.ONCE;   //prints : constructing
        OnlyOne secondCall = OnlyOne.ONCE;  //doesn`t print anything
    }
}