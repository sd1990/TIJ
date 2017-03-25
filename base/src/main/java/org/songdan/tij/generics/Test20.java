package org.songdan.tij.generics;

interface Inter {

    public void play();

    public void eat();
}

class C1 implements Inter {

    @Override
    public void play() {

    }

    @Override
    public void eat() {

    }

}

class C2 {

    public <T extends Inter> void invoke(T t) {
        t.play();
        t.eat();
    }
}

public class Test20 {

}
