package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_ocpjp7_generic_class._02_generic_interfaces;

/**
 * Created by danilolima on 14/03/18.
 */
public interface MyMap<K, V> { //two type parameters

    void put(K key, V value);
    V get(K key);

}
