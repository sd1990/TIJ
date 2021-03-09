package org.songdan.tij.test.frame.cases;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTest {

    private boolean comeIn(House house) {
        System.out.println("house is:" + house);
        return true;
    }

    @Test
    public void comeIn() {
        Door door = new Door(50, 100, 1);
        House house = new House(door);
        Assert.assertTrue(house.comeIn(50, 90));
    }
}