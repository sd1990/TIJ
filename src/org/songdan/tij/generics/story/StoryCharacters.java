package org.songdan.tij.generics.story;

/**
 * 故事人物特性类
 *
 * @author SONGDAN
 */
public class StoryCharacters {

    private static int counter = 0;

    private final int id = counter++;

    @Override
    public String toString() {
        return "StoryCharacters [id=" + id + "]";
    }

}
