package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._02_generic_interfaces;

/**
 * Created by danilolima on 14/03/18.
 */
//TIP: NONGENERIC CLASS IMPLEMENTING A GENERIC INTERFACE
public class MapLegendNonGeneric  implements MyMap<String, Integer>{
    @Override
    public void put(String key, Integer value) {

    }

    @Override
    public Integer get(String key) {
        return null;
    }
}
