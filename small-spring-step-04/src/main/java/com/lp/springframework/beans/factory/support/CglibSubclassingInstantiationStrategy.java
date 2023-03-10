package com.lp.springframework.beans.factory.support;

import com.lp.springframework.beans.factory.config.BeanDefinition;
import com.lp.springframework.beans.factory.exception.BeansException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author liupeng1
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(null == constructor) {
            enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
