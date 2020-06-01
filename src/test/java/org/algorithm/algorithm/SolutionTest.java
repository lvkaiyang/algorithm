package org.algorithm.algorithm;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
    private static Solution solution;

    @Before
    public void setUp() throws Exception {
        if (solution == null) {
            synchronized (Solution.class) {
                solution = new Solution();
            }
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isRobotBounded() {
        boolean res = solution.isRobotBounded("GGLRRRGG");

        Assert.assertTrue(res);

        res = solution.isRobotBounded("GG");

        Assert.assertFalse(res);

        res = solution.isRobotBounded("GL");

        Assert.assertTrue(res);
    }

    @Test
    public void productExceptSelf() {
        int[] nums = new int[]{1, 2, 3, 4};
        int[] res = solution.productExceptSelf(nums);

        Assert.assertArrayEquals(new int[]{24, 12, 8, 6}, res);

        nums = new int[]{2, 3, 8};
        res = solution.productExceptSelf(nums);

        Assert.assertArrayEquals(new int[]{24, 16, 6}, res);
    }
}