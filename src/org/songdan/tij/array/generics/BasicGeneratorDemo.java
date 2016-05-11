package org.songdan.tij.array.generics;

import org.songdan.tij.array.generics.story.StoryCharacters;

public class BasicGeneratorDemo {
    public static void main(String[] args) {
        BasicGenerator<StoryCharacters> gen = BasicGenerator.create(StoryCharacters.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        BasicGenerator<StoryCharacters> gen2=new BasicGenerator<>(StoryCharacters.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }
}
