package com.lp.springframework.beans.factory.support;

import com.lp.springframework.beans.factory.BeanFactory;
import com.lp.springframework.beans.factory.config.BeanDefinition;
import com.lp.springframework.beans.factory.exception.BeansException;

/**
 * @author liupeng1
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements  BeanFactory {


    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        final Object singleton = getSingleton(beanName);
        if(singleton != null) {
            return singleton;
        }

        final BeanDefinition beanDefinition = getBeanDefinition(beanName);

        return createBean(beanName, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
