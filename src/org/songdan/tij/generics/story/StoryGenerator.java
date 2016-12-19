package org.songdan.tij.generics.story;

import org.songdan.tij.generics.Generator;

import java.util.Iterator;
import java.util.Random;

public class StoryGenerator implements Generator<StoryCharacters>, Iterable<StoryCharacters> {

    private int size = 0;

    private Class[] types = { GoodGuys.class, BadGuys.class };

    @Override
    public StoryCharacters next() {
        try {
            return (StoryCharacters) types[new Random().nextInt(types.length)].newInstance();
        }
        catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    class StoryIterator implements Iterator<StoryCharacters> {

        private int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public StoryCharacters next() {
            count--;
            return StoryGenerator.this.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public Iterator<StoryCharacters> iterator() {
        return new StoryIterator();
    }

}
