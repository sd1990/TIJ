package org.songdan.tij.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	public static final String POST_METHOD = "POST";
	public static final String GET_METHOD = "GET";
	public static HttpsURLConnection prepareHttpsConnection(String url,
			String method) throws IOException {
		System.setProperty ("jsse.enableSNIExtension", "false");
		URL requestUrl = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) requestUrl
				.openConnection();
		conn.setRequestMethod(method);
		// 设置访问超时时间及读取网页流的超市时间,毫秒值
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setHostnameVerifier(new HostnameVerifier() {  
            @Override  
            public boolean verify(String hostname, SSLSession session) {  
            	System.out.println(hostname);
                return true;  
            }  
        });  
		return conn;
	}

	public static HttpURLConnection prepareHttpConnection(String url,
			String method) throws IOException {
		System.setProperty ("jsse.enableSNIExtension", "false");
		URL requestUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) requestUrl
				.openConnection();
		conn.setRequestMethod(method);
		// 设置访问超时时间及读取网页流的超市时间,毫秒值
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		return conn;
	}
}
