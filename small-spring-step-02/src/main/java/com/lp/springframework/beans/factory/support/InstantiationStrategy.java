package com.lp.springframework.beans.factory.support;

import com.lp.springframework.beans.factory.config.BeanDefinition;
import com.lp.springframework.beans.factory.exception.BeansException;

import java.lang.reflect.Constructor;

/**
 * @author liupeng1
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
