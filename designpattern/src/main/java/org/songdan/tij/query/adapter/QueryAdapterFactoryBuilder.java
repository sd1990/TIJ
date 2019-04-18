package org.songdan.tij.query.adapter;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: Songdan
 * @create: 2019-04-18 11:34
 **/
public class QueryAdapterFactoryBuilder {

    private static volatile QueryAdapterFactory queryAdapterFactory;

    public static QueryAdapterFactory getFactory() {
        if (queryAdapterFactory == null) {
            synchronized (QueryAdapterFactoryBuilder.class) {
                if (queryAdapterFactory == null) {
                    ServiceLoader<QueryAdapterFactory> serviceLoader = ServiceLoader.load(QueryAdapterFactory.class);
                    Iterator<QueryAdapterFactory> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        queryAdapterFactory = iterator.next();
                    } else {
                        throw new IllegalStateException("QueryAdapterFactory can't find");
                    }
                }
            }
        }
        return queryAdapterFactory;
    }

}
