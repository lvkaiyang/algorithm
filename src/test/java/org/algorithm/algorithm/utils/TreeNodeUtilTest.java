package org.algorithm.algorithm.utils;

import org.algorithm.algorithm.structures.basic.TreeNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TreeNodeUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void serialize() {
        String data = TreeNodeUtil.serialize(TreeNodeUtil.deserialize("{3,9,20,#,#,15,7}"));

        Assert.assertEquals("{3,9,20,#,#,15,7}", data);


        data = TreeNodeUtil.serialize(TreeNodeUtil.deserialize("{1,2,3}"));

        Assert.assertEquals("{1,2,3}", data);
    }

    @Test
    public void deserialize() {
        TreeNode root = TreeNodeUtil.deserialize("{3,9,20,#,#,15,7}");

        Assert.assertEquals("{3,9,20,#,#,15,7}", TreeNodeUtil.serialize(root));

        root = TreeNodeUtil.deserialize("{1,2,3}");

        Assert.assertEquals("{1,2,3}", TreeNodeUtil.serialize(root));
    }
}