package org.songdan.tij.collection;

import org.apache.commons.collections4.Transformer;

import java.util.*;

/**
 * 集合工具类
 * Created by SongDan on 2016/5/3.
 */
public class CollectionUtils {

    /**
     * 从collection中去除交集（不修改参数）
     * @param collection
     * @param remove
     * @return
     */
    public static List removeAll(List<? extends Object> collection,List<? extends Object> remove) {
        List list = new ArrayList<>(Math.min(collection.size(),remove.size()));
        for (Object e : collection) {
            if (!remove.contains(e)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * 从collection中按照自定义的相等行去除交集
     * @param collection
     * @param remove
     * @param equator
     * @param <E>
     * @return
     */
    public static <E> Collection<E> removeAll(Iterable<? extends E> collection, Iterable<? extends E> remove,
            final Equator equator) {

        //如何将Iterable<Object> 变为 Iterable<EquatorWrapper>
        final Transformer<E, EquatorWrapper<E>> transformer = new Transformer<E, EquatorWrapper<E>>() {
            public EquatorWrapper<E> transform(E input) {
                return new EquatorWrapper<E>(equator, input);
            }
        };

        final Set<EquatorWrapper<E>> removeSet =
                collect(remove, transformer, new HashSet<EquatorWrapper<E>>());
        List<E> list = new ArrayList();
        for (E e : collection) {
            if (!removeSet.contains(new EquatorWrapper<E>(equator,e))) {
                list.add(e);
            }
        }
        return list;
    }

    public static <I, O, R extends Collection<? super O>> R collect(final Iterable<? extends I> inputCollection,
            final Transformer<? super I, ? extends O> transformer, final R outputCollection) {
        if (inputCollection != null) {
            return collect(inputCollection.iterator(), transformer, outputCollection);
        }
        return outputCollection;
    }

    public static <I, O, R extends Collection<? super O>> R collect(final Iterator<? extends I> inputIterator,
            final Transformer<? super I, ? extends O> transformer, final R outputCollection) {
        if (inputIterator != null && transformer != null) {
            while (inputIterator.hasNext()) {
                final I item = inputIterator.next();
                final O value = transformer.transform(item);
                outputCollection.add(value);
            }
        }
        return outputCollection;
    }

    private static class EquatorWrapper<E>{

        private final Equator<? super E> equator;

        private final E object;

        public EquatorWrapper(Equator<? super E> equator,E object) {
            this.equator = equator;
            this.object = object;
        }

        public E getObject() {
            return object;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj==this)
                return true;
            if (!(obj instanceof EquatorWrapper)) {
                return false;
            }
            final EquatorWrapper<E> dest = (EquatorWrapper<E>) obj;
            return equator.equate(object, dest.getObject());
        }
    }


}
