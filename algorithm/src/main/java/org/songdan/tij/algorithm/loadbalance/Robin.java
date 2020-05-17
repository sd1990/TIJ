package org.songdan.tij.algorithm.loadbalance;

/**
 * 负载均衡算法
 *
 * @author: Songdan
 * @create: 2020-05-07 10:51
 **/
public interface Robin<T> {

    T next();

}
