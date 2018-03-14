package br.com.dvlima.ocpjp8.topic03.item01_generic_class;

/**
 * Created by danilolima on 14/03/18.
 */
public class UseGenericParcel {

    public static void main(String[] args) {
        Parcel<Book> parcel = new Parcel<>();
        parcel.set(new Book());
        Book myBook = parcel.get();
        //TIP: Won't compile if try cast Book to Phone
        //System.out.println((Phone)parcel.get());
        System.out.println(myBook);
    }
}
