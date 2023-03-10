package com.lp.springframework.core.io;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liupeng1
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "path must not be null");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtil.getClassLoader();

    }

    @Override
    public InputStream getInputStream() throws IOException {

        final InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if(resourceAsStream == null) {
            throw new FileNotFoundException(this.path + " connot be opened because it does not exist");
        }

        return resourceAsStream;
    }
}
