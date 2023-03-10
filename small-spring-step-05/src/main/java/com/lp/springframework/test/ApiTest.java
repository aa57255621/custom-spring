package com.lp.springframework.test;

import com.lp.springframework.beans.PropertyValue;
import com.lp.springframework.beans.PropertyValues;
import com.lp.springframework.beans.factory.config.BeanDefinition;
import com.lp.springframework.beans.factory.config.BeanReference;
import com.lp.springframework.beans.factory.exception.BeansException;
import com.lp.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lp.springframework.test.bean.UserDao;
import com.lp.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author liupeng1
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() throws BeansException {
        // 初始化beanfactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        
        // 注册userDao
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // userService注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // userService获取bean
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_constructor()throws Exception{
        final Class<UserService> userServiceClass = UserService.class;
        final Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        final UserService userService = declaredConstructor.newInstance("寒风");
        System.out.println(userService);
    }

    @Test
    public void test_paramters()throws Exception{
        final Class<UserService> userServiceClass = UserService.class;
        final Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        final Constructor<?> constructor = declaredConstructors[0];
        final Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(constructor.getParameterTypes());
        final UserService userService = declaredConstructor.newInstance("寒风");
        System.out.println(userService);
    }

    @Test
    public void test_cglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        enhancer.create(new Class[]{String.class}, new Object[]{"寒风"});
        System.out.println(enhancer);
    }
}
