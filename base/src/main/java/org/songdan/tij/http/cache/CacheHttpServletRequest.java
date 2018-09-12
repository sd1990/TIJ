package org.songdan.tij.http.cache;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2018-09-11 21:10
 **/
public class CacheHttpServletRequest extends HttpServletRequestWrapper {

    private byte[] body;

    private CacheServletInputStream cacheServletInputStream;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public CacheHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        ServletInputStream inputStream = request.getInputStream();
        cacheServletInputStream = new CacheServletInputStream(inputStream);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return cacheServletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public Map getParameterMap() {
        return super.getParameterMap();
    }

    public byte[] getCache() {
        return cacheServletInputStream.baos.toByteArray();
    }

    private class CacheServletInputStream extends ServletInputStream{

        private ByteArrayOutputStream baos;

        private ServletInputStream servletInputStream;

        public CacheServletInputStream(ServletInputStream servletInputStream) {
            this.servletInputStream = servletInputStream;
            this.baos = new ByteArrayOutputStream(1024);
        }

        @Override
        public int read() throws IOException {
            int read = servletInputStream.read();
            if (read != -1) {
                baos.write(read);
            }
            return read;
        }
    }
}
