package com.lp.springframework.beans.factory.config;

/**
 * @author liupeng1
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
