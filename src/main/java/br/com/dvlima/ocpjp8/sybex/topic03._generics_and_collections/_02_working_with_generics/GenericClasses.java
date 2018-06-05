package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._02_working_with_generics;

public class GenericClasses {
    // E for an element
    // K for a map key
    // V for a map value
    // N for a number
    // T for a generic data type
    // S, U, V, and so forth for multiple generic types

    Object elephant = new Object();
    Integer numPounds = 15_000;
    SizeLimitedCrate<Object, Integer> c1 = new SizeLimitedCrate<>(elephant, numPounds);
}

class Crate<T> {
    private T contents;

    public T emptyCrate() {
        return contents;
    }

    public void packCrate(T contents) {
        this.contents = contents;
    }
}

class SizeLimitedCrate<T, U> {
    private T contents;
    private U sizeLimit;

    public SizeLimitedCrate(T contents, U sizeLimit) {
        this.contents = contents;
        this.sizeLimit = sizeLimit;
    }
}

//VARIABLE NAMES USED FOR TYPE PARAMETERS
class MyClass {
}

class Parcel<MyClass> {
    private MyClass t;

    public void set(MyClass t) {
        this.t = t;
    }
}
//Parcel<String> parcel = new Parcel<>();
//parcel.set("OCP");
//MyClass is a Type <T> not class

//GENERIC CLASS EXTENDING ANOTHER GENERIC CLASS

class Parcel2<T> {}
class GenericBookParcel2<T> extends Parcel2<T> {} //Generic extended class


class Parcel3<T> {}
//class GenericBookParcel3<X> extends Parcel3<T> {}//Won’t compile; no way to pass argument to T
class GenericBookParcel3<X, T> extends Parcel3<T> {}//Compiles successfully

//The keyword implements isn’t used to specify the bound as an interface.
// The following code won’t compile:
//class Parcel<T implements Serializable>{} Won’t compile

interface Wrappable{}
interface Exchangeable{}
class Gift{}
class Parcel5 <T extends Gift, Exchangeable, Wrappable>{}
//For a type parameter with multiple bounds, the type argument must be a subtype of all bounds.