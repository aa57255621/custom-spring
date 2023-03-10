package com.lp.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liupeng1
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
