package org.algorithm.algorithm.utils;

import org.algorithm.algorithm.structures.basic.ListNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListNodeUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void serialize() {
        String data = ListNodeUtil.serialize(ListNodeUtil.deserialize("9->4->6->null"));

        Assert.assertEquals("9->4->6->null", data);

        data = ListNodeUtil.serialize(ListNodeUtil.deserialize("8->4->null"));

        Assert.assertEquals("8->4->null", data);

        data = ListNodeUtil.serialize(ListNodeUtil.deserialize("3->2->1->null"));

        Assert.assertEquals("3->2->1->null", data);
    }

    @Test
    public void deserialize() {
        ListNode head = ListNodeUtil.deserialize("9->4->6->null");

        Assert.assertEquals("9->4->6->null", ListNodeUtil.serialize(head));

        head = ListNodeUtil.deserialize("8->4->null");

        Assert.assertEquals("8->4->null", ListNodeUtil.serialize(head));

        head = ListNodeUtil.deserialize("3->2->1->null");

        Assert.assertEquals("3->2->1->null", ListNodeUtil.serialize(head));
    }
}