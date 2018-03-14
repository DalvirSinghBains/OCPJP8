package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._04_ocpjp7_enumerated_types;

import java.util.List;

/**
 * Created by danilolima on 08/03/18.
 */
public interface MyInterface {

    //TIP: enum as a member of an interface
    enum MyIceCreamTopLevelEnum {BEGINNER, INTERMEDIATE, EXPERT}

    List<?> getItens();

}
