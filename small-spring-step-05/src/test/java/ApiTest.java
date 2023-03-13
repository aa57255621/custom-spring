import cn.hutool.core.io.IoUtil;
import com.lp.springframework.beans.factory.config.BeanDefinition;
import com.lp.springframework.beans.factory.exception.BeansException;
import com.lp.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lp.springframework.core.io.DefaultResourceLoader;
import com.lp.springframework.core.io.Resource;
import com.lp.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;

/**
 * @author liupeng1
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.read(inputStream, Charset.defaultCharset());
        System.out.println(content);
    }



    @Test
    public void test_BeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        final BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "寒风");
        userService.queryUserInfo();
    }

    @Test
    public void test_constructor() throws Exception {
        final Class<UserService> userServiceClass = UserService.class;
        final Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        final UserService userService = declaredConstructor.newInstance("寒风");
        System.out.println(userService);
    }

    @Test
    public void test_paramters() throws Exception {
        final Class<UserService> userServiceClass = UserService.class;
        final Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        final Constructor<?> constructor = declaredConstructors[0];
        final Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(constructor.getParameterTypes());
        final UserService userService = declaredConstructor.newInstance("寒风");
        System.out.println(userService);
    }

    @Test
    public void test_cglib() {
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
