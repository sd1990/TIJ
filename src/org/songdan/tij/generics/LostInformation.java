package org.songdan.tij.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Frob {

}

class Fnorkle {

}

class Quark<Q> {

}

class Particle<POSITION, MOMENTUM> {

}

public class LostInformation {

    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
    }
}
