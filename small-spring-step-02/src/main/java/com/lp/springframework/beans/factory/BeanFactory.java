package com.lp.springframework.beans.factory;

import com.lp.springframework.beans.factory.exception.BeansException;

/**
 * @author liupeng1
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;
    Object getBean(String name, Object ... args) throws BeansException;



}
