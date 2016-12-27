package org.songdan.tij.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NodeListTest {

    private NodeList<String> nodeList;

    @Before
    public void before() {
        nodeList = new NodeList<>();
    }

    @Test
    public void testSize() throws Exception {
        nodeList.insert("songdan",0);
        nodeList.insert("zhaofeifei",0);
        nodeList.insert("liuyong",0);
        Assert.assertEquals(3,nodeList.size());
    }

    @Test
    public void testInsert() throws Exception {
        nodeList.insert("songdan",0);
        nodeList.insert("zhaofeifei",1);
        Assert.assertEquals("songdan", nodeList.get(0));
        Assert.assertEquals("zhaofeifei",nodeList.get(1));
    }

    @Test
    public void testRemove() throws Exception {
        nodeList.insert("songdan",0);
        nodeList.insert("zhaofeifei",0);
        nodeList.insert("liuyong",0);
        Assert.assertEquals(3,nodeList.size());
        nodeList.remove(2);
        Assert.assertEquals(2,nodeList.size());
        Assert.assertEquals("liuyong", nodeList.get(0));
        Assert.assertEquals("zhaofeifei",nodeList.get(1));
    }
}
