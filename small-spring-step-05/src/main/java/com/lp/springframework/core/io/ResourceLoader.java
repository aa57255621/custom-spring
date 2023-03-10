package com.lp.springframework.core.io;

/**
 * @author liupeng1
 */
public interface ResourceLoader {

    /**
     * url prefix for loading form the  class path : "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
