package org.songdan.tij.codewars.sku_6;

import org.junit.Test;

import static org.junit.Assert.*;

public class DRobotTest {

    @Test
    public void Tests() {
        assertEquals( "Nope!" , 7, DRoot.digital_root(16));
        assertEquals( "Nope!" , 6, DRoot.digital_root(456));
    }

}