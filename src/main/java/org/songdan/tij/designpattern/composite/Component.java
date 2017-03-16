package org.songdan.tij.designpattern.composite;

/**
 * 组合模式，整体与部分之间的关系，透明性与安全性的平衡
 * Created by SongDan on 2017/3/16.
 */
public interface Component {

    void operation();

    void add();

    void remove();

    void list();

}
