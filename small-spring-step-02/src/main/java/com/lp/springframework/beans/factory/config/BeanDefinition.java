package com.lp.springframework.beans.factory.config;

import java.util.Properties;

/**
 * @author liupeng1
 */
public class BeanDefinition {

    private Class<?> beanClass;


    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
