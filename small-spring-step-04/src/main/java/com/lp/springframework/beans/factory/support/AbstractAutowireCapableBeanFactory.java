package com.lp.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.lp.springframework.beans.PropertyValue;
import com.lp.springframework.beans.PropertyValues;
import com.lp.springframework.beans.factory.config.BeanDefinition;
import com.lp.springframework.beans.factory.config.BeanReference;
import com.lp.springframework.beans.factory.exception.BeansException;

import java.lang.reflect.Constructor;

/**
 * @author liupeng1
 */
public class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        return null;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException {
        Constructor constructorToUse = null;
        final Class<?> beanClass = beanDefinition.getBeanClass();
        final Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if(null != args && declaredConstructor.getParameterTypes().length == args.length) {
                constructorToUse = declaredConstructor;
                break;
            }
        }
        return  getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }


    public InstantiationStrategy getInstantiationStrategy(){
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    protected  void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                final String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference) {
                    // A依赖B ，获取B的实例化
                    BeanReference beanReference = (BeanReference) value;

                    // 重新给value赋值，把BeanReference转化为对应类型的属性值
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);

            }
        } catch (Exception e){
            throw new BeansException("Error setting property values:" + beanName);
        }
    }


}
