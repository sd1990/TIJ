package org.songdan.tij.designpattern.composite;

/**
 * Created by SongDan on 2017/3/16.
 */
public class LeafComponent implements Component {

    @Override
    public void operation() {

    }

    @Override
    public void add() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();

    }

    @Override
    public void list() {
        throw new UnsupportedOperationException();
    }
}
