package org.algorithm.algorithm;

import org.algorithm.algorithm.structures.basic.TreeNode;
import org.algorithm.algorithm.utils.TreeNodeUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void knightDialer() {
        int res = solution.knightDialer(1);

        Assert.assertEquals(10, res);

        res = solution.knightDialer(2);

        Assert.assertEquals(20, res);

        res = solution.knightDialer(3);

        Assert.assertEquals(46, res);
    }

    @Test
    public void getModifiedArray() {
        int[] res = solution.getModifiedArray(5, new int[][]{
                {1, 3, 2},
                {2, 4, 3},
                {1, 2, -2}
        });

        Assert.assertArrayEquals(new int[]{0, 0, 3, 5, 3}, res);
    }

    @Test
    public void shortestPath() {
        int[][] targetMap = new int[][]{
                {0, 0, 0},
                {0, 0, 1},
                {0, 0, 2}
        };

        int res = solution.shortestPath(targetMap);

        Assert.assertEquals(4, res);

        targetMap = new int[][]{
                {0, 1},
                {0, 1},
                {0, 0},
                {0, 2}
        };

        res = solution.shortestPath(targetMap);

        Assert.assertEquals(4, res);
    }

    @Test
    public void minEatingSpeed() {
        int[] piles = new int[]{3, 6, 7, 11};
        int H = 8;

        int res = solution.minEatingSpeed(piles, H);

        Assert.assertEquals(4, res);

        piles = new int[]{30, 11, 23, 4, 20};
        H = 5;

        res = solution.minEatingSpeed(piles, H);

        Assert.assertEquals(30, res);

        piles = new int[]{
                332484035, 524908576, 855865114, 632922376, 222257295, 690155293,
                112677673, 679580077, 337406589, 290818316, 877337160, 901728858,
                679284947, 688210097, 692137887, 718203285, 629455728, 941802184
        };
        H = 823855818;

        res = solution.minEatingSpeed(piles, H);

        Assert.assertEquals(14, res);
    }

    @Test
    public void isBipartite() {
        int[][] graph = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};

        boolean res = solution.isBipartite(graph);

        Assert.assertTrue(res);

        graph = new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};

        res = solution.isBipartite(graph);

        Assert.assertFalse(res);

        graph = new int[][]{{}};

        res = solution.isBipartite(graph);

        Assert.assertTrue(res);

        graph = new int[][]{
                {}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9},
                {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}
        };

        res = solution.isBipartite(graph);

        Assert.assertFalse(res);

        graph = new int[][]{{}, {3}, {}, {1}, {}};

        res = solution.isBipartite(graph);

        Assert.assertTrue(res);
    }

    @Test
    public void findMode() {
        TreeNode root = TreeNodeUtil.deserialize("{1,#,2,2}");

        int[] res = solution.findMode(root);

        Assert.assertArrayEquals(new int[]{2}, res);

        root = TreeNodeUtil.deserialize("{-2,-2,-2}");

        res = solution.findMode(root);

        Assert.assertArrayEquals(new int[]{-2}, res);
    }

    @Test
    public void wiggleMaxLength() {
        int[] nums = new int[]{1, 7, 4, 9, 2, 5};

        int res = solution.wiggleMaxLength(nums);

        Assert.assertEquals(6, res);

        nums = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};

        res = solution.wiggleMaxLength(nums);

        Assert.assertEquals(7, res);
    }

    @Test
    public void largestValues() {
        TreeNode root = TreeNodeUtil.deserialize("{1,3,2,5,3,#,9}");

        List<Integer> res = solution.largestValues(root);

        Assert.assertEquals(Arrays.asList(1, 3, 9), res);

        root = TreeNodeUtil.deserialize("{1,2,3,4,5,6,#,#,7}");

        res = solution.largestValues(root);

        Assert.assertEquals(Arrays.asList(1, 3, 6, 7), res);
    }
}