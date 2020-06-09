package org.algorithm.algorithm;

import org.algorithm.algorithm.structures.basic.TreeNode;
import org.algorithm.algorithm.structures.basic.UndirectedGraphNode;
import org.algorithm.algorithm.utils.TreeNodeUtil;
import org.algorithm.algorithm.utils.UndirectedGraphNodeUtil;
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

    @Test
    public void calcMaxValue() {
        String str = "01231";

        int res = solution.calcMaxValue(str);

        Assert.assertEquals(10, res);

        str = "891";

        res = solution.calcMaxValue(str);

        Assert.assertEquals(73, res);
    }

    @Test
    public void sortTransformedArray() {
        int[] nums = new int[]{-4, -2, 2, 4};
        int a = 1;
        int b = 3;
        int c = 5;

        int[] res = solution.sortTransformedArray(nums, a, b, c);

        Assert.assertArrayEquals(new int[]{3, 9, 15, 33}, res);

        nums = new int[]{-4, -2, 2, 4};
        a = -1;
        b = 3;
        c = 5;

        res = solution.sortTransformedArray(nums, a, b, c);

        Assert.assertArrayEquals(new int[]{-23, -5, 1, 7}, res);
    }

    @Test
    public void canAccept() {
        long res = solution.canAccept(30, 1);

        Assert.assertEquals(7, res);

        res = solution.canAccept(31, 2);

        Assert.assertEquals(5, res);
    }

    @Test
    public void canPartition() {
        int[] nums = new int[]{1, 5, 11, 5};

        boolean res = solution.canPartition(nums);

        Assert.assertTrue(res);

        nums = new int[]{1, 2, 3, 9};

        res = solution.canPartition(nums);

        Assert.assertFalse(res);
    }

    @Test
    public void shortestPath_1() {
        List<UndirectedGraphNode> graph = UndirectedGraphNodeUtil.deserialize("{1,2,4#2,1,4#3,5#4,1,2#5,3}");
        int label_a = 3, label_b = 5;
        UndirectedGraphNode A = null, B = null;
        for (UndirectedGraphNode node : graph) {
            if (node.label == label_a) A = node;
            if (node.label == label_b) B = node;
        }

        int res = solution.shortestPath(graph, A, B);

        Assert.assertEquals(1, res);

        graph = UndirectedGraphNodeUtil.deserialize("{1,2,3,4#2,1,3#3,1#4,1,5#5,4}");
        label_a = 1;
        label_b = 5;
        A = null;
        B = null;
        for (UndirectedGraphNode node : graph) {
            if (node.label == label_a) A = node;
            if (node.label == label_b) B = node;
        }

        res = solution.shortestPath(graph, A, B);

        Assert.assertEquals(2, res);
    }

    @Test
    public void numberOfArithmeticSlices() {
        int[] nums = new int[]{1, 2, 3, 4};

        int res = solution.numberOfArithmeticSlices(nums);

        Assert.assertEquals(3, res);

        nums = new int[]{1, 2, 3};

        res = solution.numberOfArithmeticSlices(nums);

        Assert.assertEquals(1, res);
    }

    @Test
    public void findMaxConsecutiveOnes() {
        int[] nums = new int[]{1, 0, 1, 1, 0};

        int res = solution.findMaxConsecutiveOnes(nums);

        Assert.assertEquals(4, res);

        nums = new int[]{1, 0, 1, 0, 1};

        res = solution.findMaxConsecutiveOnes(nums);

        Assert.assertEquals(3, res);
    }
}