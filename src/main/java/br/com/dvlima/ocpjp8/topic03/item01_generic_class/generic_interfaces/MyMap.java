package br.com.dvlima.ocpjp8.topic03.item01_generic_class.generic_interfaces;

/**
 * Created by danilolima on 14/03/18.
 */
public interface MyMap<K, V> { //two type parameters

    void put(K key, V value);
    V get(K key);

}
