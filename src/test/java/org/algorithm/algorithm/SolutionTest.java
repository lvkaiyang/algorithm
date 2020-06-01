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
    }
}