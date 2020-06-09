package org.algorithm.algorithm.utils;

import org.junit.Assert;
import org.junit.Test;

public class UndirectedGraphNodeUtilTest {

    @Test
    public void deserialize() {
        String data = "{1,2,4#2,1,4#3,5#4,1,2#5,3}";

        Assert.assertEquals(data, UndirectedGraphNodeUtil.serialize(UndirectedGraphNodeUtil.deserialize(data)));
    }
}