package org.songdan.tij.optimize.localcache;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Songdan
 * @create: 2019-06-29 09:09
 **/
public abstract class LocalCache<T> {

    public T execute() {
        //ensure start
        before();
        doExecute();
        after();
        //ensure release
        return null;
    }

    public abstract T doExecute();

    public static ThreadLocal<Context> context = ThreadLocal.withInitial(() -> new Context());

    public Context getContext() {
        return context.get();
    }

    public void before() {
        context.get().executeStack.incrementAndGet();
    }

    public void after() {
        if (context.get().executeStack.decrementAndGet() == 0) {
            context.get().clear();
        }

    }

    public static class Context {

        private HashMap<String, Object> chm = new HashMap<>();

        private AtomicInteger executeStack = new AtomicInteger(0);

        public void clear() {
            chm.clear();
            executeStack.set(0);
        }
    }

    public static void main(String[] args) {
        new LocalCache<Integer>() {
            @Override
            public Integer doExecute() {
                System.out.println(getContext().executeStack.get());
                Integer result = new LocalCache<Integer>() {
                    @Override
                    public Integer doExecute() {
                        System.out.println(getContext().executeStack.get());
                        return null;
                    }
                }.execute();
                return result;
            }
        }.execute();
    }

}
