package br.com.dvlima.ocpjp8.apress.topic06._exceptions_and_assertions._03_autoclose_resources_with_trywithresources;

/**
 * Created by danilo.fernandes on 26/03/2018.
 */
public class ClassAutoCloseable implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("ClassAutoCloseable::close");
    }
}
