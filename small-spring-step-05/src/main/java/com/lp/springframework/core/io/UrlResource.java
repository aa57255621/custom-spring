package com.lp.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liupeng1
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "url cannot be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        final URLConnection con = url.openConnection();
        try {
            return con.getInputStream();
        }catch (IOException e) {
            if(con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw e;
        }
    }
}
