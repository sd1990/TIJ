package org.songdan.tij.http;

import org.songdan.tij.http.cache.CacheHttpServletRequest;
import org.songdan.tij.http.cache.CacheHttpServletResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Songdan
 * @create: 2018-09-11 21:09
 **/
public class RequestLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CacheHttpServletRequest cacheHttpServletRequest = new CacheHttpServletRequest((HttpServletRequest) request);
        CacheHttpServletResponse cacheHttpServletResponse = new CacheHttpServletResponse((HttpServletResponse) response);
        try {
            chain.doFilter(cacheHttpServletRequest, cacheHttpServletResponse);
        }finally {
            // log request & response
        }
    }

    @Override
    public void destroy() {

    }
}
