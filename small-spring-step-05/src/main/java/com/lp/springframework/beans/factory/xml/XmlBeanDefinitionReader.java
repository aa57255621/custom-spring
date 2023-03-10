package com.lp.springframework.beans.factory.xml;

import com.lp.springframework.beans.factory.exception.BeansException;
import com.lp.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.lp.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.lp.springframework.core.io.Resource;
import com.lp.springframework.core.io.ResourceLoader;

/**
 * @author liupeng1
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {

    }

    @Override
    public void loadBeanDefinitions(Resource... resource) throws BeansException {

    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {

    }
}
