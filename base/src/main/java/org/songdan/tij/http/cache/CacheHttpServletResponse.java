package org.songdan.tij.http.cache;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: Songdan
 * @create: 2018-09-11 22:03
 **/
public class CacheHttpServletResponse extends HttpServletResponseWrapper {

    private CacheHttpServletOutputStream cacheHttpServletOutputStream;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public CacheHttpServletResponse(HttpServletResponse response) throws IOException {
        super(response);
        cacheHttpServletOutputStream = new CacheHttpServletOutputStream(response.getOutputStream(), new ByteArrayOutputStream(1024));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return cacheHttpServletOutputStream;
    }

    public byte[] getCache() {
        return cacheHttpServletOutputStream.getCache();
    }

    private class CacheHttpServletOutputStream extends ServletOutputStream {

        private ByteArrayOutputStream byteArrayOutputStream;

        private ServletOutputStream outputStream;

        public CacheHttpServletOutputStream(ServletOutputStream outputStream, ByteArrayOutputStream byteArrayOutputStream) {
            this.outputStream = outputStream;
            this.byteArrayOutputStream = byteArrayOutputStream;
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
            byteArrayOutputStream.write(b);
        }

        public byte[] getCache() {
            return byteArrayOutputStream.toByteArray();
        }
    }

}
