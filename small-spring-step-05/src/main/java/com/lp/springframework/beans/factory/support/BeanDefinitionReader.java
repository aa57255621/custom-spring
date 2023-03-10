package com.lp.springframework.beans.factory.support;

import com.lp.springframework.beans.factory.exception.BeansException;
import com.lp.springframework.core.io.Resource;
import com.lp.springframework.core.io.ResourceLoader;

/**
 * @author liupeng1
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
