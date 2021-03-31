package org.algorithm.algorithm;

import org.algorithm.algorithm.structures.basic.ListNode;
import org.algorithm.algorithm.structures.basic.TreeNode;
import org.algorithm.algorithm.structures.basic.UndirectedGraphNode;
import org.algorithm.algorithm.utils.ListNodeUtil;
import org.algorithm.algorithm.utils.TreeNodeUtil;
import org.algorithm.algorithm.utils.UndirectedGraphNodeUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @Test
    public void findLUSlength() {
        String[] strs = new String[]{"aaa", "acb"};

        int res = solution.findLUSlength(strs);

        Assert.assertEquals(3, res);

        strs = new String[]{"aabbcc", "aabbcc", "cb"};

        res = solution.findLUSlength(strs);

        Assert.assertEquals(2, res);
    }

    @Test
    public void longestConsecutive2() {
        TreeNode root = TreeNodeUtil.deserialize("{1,2,0,3}");

        int res = solution.longestConsecutive2(root);

        Assert.assertEquals(4, res);

        root = TreeNodeUtil.deserialize("{3,2,2}");

        res = solution.longestConsecutive2(root);

        Assert.assertEquals(2, res);

        root = TreeNodeUtil.deserialize("{2,#,3,2,#,1}");

        res = solution.longestConsecutive2(root);

        Assert.assertEquals(3, res);
    }

    @Test
    public void findRepeatedDna() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        List<String> res = solution.findRepeatedDna(s);

        Assert.assertEquals(Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"), res);

        s = "GAGAGAGAGAGA";

        res = solution.findRepeatedDna(s);

        Assert.assertEquals(Collections.singletonList("GAGAGAGAGA"), res);

        s = "AAAAAAAAAAAA";

        res = solution.findRepeatedDna(s);

        Assert.assertEquals(Collections.singletonList("AAAAAAAAAA"), res);
    }

    @Test
    public void flipDigit() {
        int[] nums = new int[]{1, 0, 0, 1, 1, 1};

        int res = solution.flipDigit(nums);

        Assert.assertEquals(2, res);

        nums = new int[]{1, 0, 1, 0, 1, 0};

        res = solution.flipDigit(nums);

        Assert.assertEquals(2, res);

        nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 0};

        res = solution.flipDigit(nums);

        Assert.assertEquals(0, res);

        nums = new int[]{
                0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 1, 0, 0, 1, 1, 1,
                0, 1, 1, 1, 1, 0, 1, 0, 1, 1,
                1, 0, 1, 0, 1, 1, 0, 1, 0, 1,
                1, 1, 1, 0, 0, 0, 1, 0, 0, 0
        };

        res = solution.flipDigit(nums);

        Assert.assertEquals(18, res);
    }

    @Test
    public void threeSumMulti() {
        int[] A = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int target = 8;

        int res = solution.threeSumMulti(A, target);

        Assert.assertEquals(20, res);

        A = new int[]{1, 1, 2, 2, 2, 2};
        target = 5;

        res = solution.threeSumMulti(A, target);

        Assert.assertEquals(12, res);

        A = new int[]{0, 2, 0, 0};
        target = 2;

        res = solution.threeSumMulti(A, target);

        Assert.assertEquals(3, res);

        A = new int[3000];
        target = 0;

        res = solution.threeSumMulti(A, target);

        Assert.assertEquals(495500972, res);
    }

    @Test
    public void digitCounts() {
        int res = solution.digitCounts(1, 1);

        Assert.assertEquals(1, res);

        res = solution.digitCounts(1, 12);

        Assert.assertEquals(5, res);
    }

    @Test
    public void kthLargestElement() {
        int res = solution.kthLargestElement(1, new int[]{1, 3, 4, 2});

        Assert.assertEquals(4, res);

        res = solution.kthLargestElement(3, new int[]{9, 3, 2, 4, 8});

        Assert.assertEquals(4, res);

        res = solution.kthLargestElement(105, new int[]{
                595240, 373125, 463748, 417209, 209393, 747977, 864346, 419023, 925673, 307640,
                597868, 833339, 130763, 814627, 766415, 79576, 459038, 990103, 944521, 708820,
                473246, 499960, 742286, 758503, 270229, 991199, 770718, 529265, 498975, 721068,
                727348, 29619, 712557, 724373, 823743, 318203, 290432, 476213, 412181, 869308,
                496482, 793858, 676162, 165869, 160511, 260864, 502521, 611678, 786798, 356560,
                916620, 922168, 89350, 857183, 964051, 979979, 916565, 186532, 905289, 653307,
                351329, 195491, 866281, 183964, 650765, 675046, 661642, 578936, 78684, 50105,
                688326, 648786, 645823, 652329, 961553, 381367, 506439, 77735, 707959, 373271,
                316194, 185079, 686945, 342608, 980794, 78777, 687520, 27772, 711098, 661265,
                167824, 688245, 286419, 400823, 198119, 35400, 916784, 81169, 874377, 377128,
                922531, 866135, 319912, 867697, 10904
        });

        Assert.assertEquals(10904, res);
    }

    @Test
    public void searchRange() {
        TreeNode root = TreeNodeUtil.deserialize("{5}");

        List<Integer> res = solution.searchRange(root, 6, 10);

        Assert.assertEquals(Collections.emptyList(), res);

        root = TreeNodeUtil.deserialize("{20,8,22,4,12}");

        res = solution.searchRange(root, 10, 22);

        Assert.assertEquals(Arrays.asList(12, 20, 22), res);

        root = TreeNodeUtil.deserialize("{2,1}");

        res = solution.searchRange(root, 0, 4);

        Assert.assertEquals(Arrays.asList(1, 2), res);

        root = TreeNodeUtil.deserialize("{1,#,2,#,3,#,4,#,5}");

        res = solution.searchRange(root, 2, 4);

        Assert.assertEquals(Arrays.asList(2, 3, 4), res);

        root = TreeNodeUtil.deserialize("{31,11,51,8,#,41,61}");

        res = solution.searchRange(root, 9, 52);

        Assert.assertEquals(Arrays.asList(11, 31, 41, 51), res);

        root = TreeNodeUtil.deserialize("{101,92,#,81,#,75,#,41,#,#,65}");

        res = solution.searchRange(root, 41, 41);

        Assert.assertEquals(Collections.singletonList(41), res);
    }

    @Test
    public void IfIntersect() {

        double[] position = new double[]{0, 0, 2.5, 3, 2, 0.5, 0, 2};

        int res = solution.IfIntersect(position);

        Assert.assertEquals(1, res);

        position = new double[]{0, 0, 2, 5, 0, 1, 0, 2};

        res = solution.IfIntersect(position);

        Assert.assertEquals(-1, res);
    }

    @Test
    public void isInterleave() {
        boolean res = solution.isInterleave("aabcc", "dbbca", "aadbbcbcac");

        Assert.assertTrue(res);

        res = solution.isInterleave("", "", "1");

        Assert.assertFalse(res);

        res = solution.isInterleave("aabcc", "dbbca", "aadbbbaccc");

        Assert.assertFalse(res);

        res = solution.isInterleave("aba", "a", "aaba");

        Assert.assertTrue(res);
    }

    @Test
    public void solveNQueens() {
        List<List<String>> res = solution.solveNQueens(1);

        Assert.assertEquals(Collections.singletonList(Collections.singletonList("Q")), res);

        res = solution.solveNQueens(4);

        Assert.assertEquals(Arrays.asList(
                Arrays.asList(
                        ".Q..",
                        "...Q",
                        "Q...",
                        "..Q."
                ), Arrays.asList(
                        "..Q.",
                        "Q...",
                        "...Q",
                        ".Q.."
                )
        ), res);
    }

    @Test
    public void totalNQueens() {
        int res = solution.totalNQueens(1);

        Assert.assertEquals(1, res);

        res = solution.totalNQueens(4);

        Assert.assertEquals(2, res);
    }

    @Test
    public void reverseBetween() {
        ListNode head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        ListNode res = solution.reverseBetween(head, 2, 4);

        Assert.assertEquals("1->4->3->2->5->null", ListNodeUtil.serialize(res));

        head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        res = solution.reverseBetween(head, 2, 2);

        Assert.assertEquals("1->2->3->4->5->null", ListNodeUtil.serialize(res));

        head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        res = solution.reverseBetween(head, 2, 3);

        Assert.assertEquals("1->3->2->4->5->null", ListNodeUtil.serialize(res));

        head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        res = solution.reverseBetween(head, 1, 3);

        Assert.assertEquals("3->2->1->4->5->null", ListNodeUtil.serialize(res));

        head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        res = solution.reverseBetween(head, 1, 5);

        Assert.assertEquals("5->4->3->2->1->null", ListNodeUtil.serialize(res));

        head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        res = solution.reverseBetween(head, 5, 5);

        Assert.assertEquals("1->2->3->4->5->null", ListNodeUtil.serialize(res));

        head = ListNodeUtil.deserialize("1->2->3->4->5->null");
        res = solution.reverseBetween(head, 3, 5);

        Assert.assertEquals("1->2->5->4->3->null", ListNodeUtil.serialize(res));
    }

    @Test
    public void maxTwoSubArrays() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 3, -1, 2, -1, 2));

        Assert.assertEquals(7, solution.maxTwoSubArrays(nums));

        nums = new ArrayList<>(Arrays.asList(5, 4));

        Assert.assertEquals(9, solution.maxTwoSubArrays(nums));

        nums = new ArrayList<>(Arrays.asList(0, -1));

        Assert.assertEquals(-1, solution.maxTwoSubArrays(nums));
    }

    @Test
    public void maxDiffSubArrays() {
        int[] nums = new int[]{1, 2, -3, 1};

        Assert.assertEquals(6, solution.maxDiffSubArrays(nums));

        nums = new int[]{0, -1};

        Assert.assertEquals(1, solution.maxDiffSubArrays(nums));
    }

    @Test
    public void majorityNumberII() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(99, 2, 99, 2, 99, 3, 3));

        Assert.assertEquals(99, solution.majorityNumber(nums));

        nums = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1, 3, 3));

        Assert.assertEquals(1, solution.majorityNumber(nums));

        nums = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4));

        Assert.assertEquals(1, solution.majorityNumber(nums));

        nums = new ArrayList<>(Arrays.asList(2, 3, 4, 2, 3, 4, 1, 4, 1, 1, 4));

        Assert.assertEquals(4, solution.majorityNumber(nums));
    }

    @Test
    public void majorityNumberIII() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(3, 1, 2, 3, 2, 3, 3, 4, 4, 4));
        int k = 3;

        Assert.assertEquals(3, solution.majorityNumber(nums, k));

        nums = new ArrayList<>(Arrays.asList(1, 1, 2));
        k = 3;

        Assert.assertEquals(1, solution.majorityNumber(nums, k));
    }

    @Test
    public void quickSort() {
        int[] nums = new int[]{5, 3, 2, 1, 6, 2, 3};

        solution.quickSort(nums);

        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 3, 5, 6}, nums);
    }

    @Test
    public void mergeSort() {
        int[] nums = new int[]{5, 3, 2, 1, 6, 2, 3};

        solution.mergeSort(nums);

        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 3, 5, 6}, nums);
    }

    @Test
    public void sortLetters() {
        // write your code here
        char[] chars = "abAcD".toCharArray();

        solution.sortLetters(chars);

        Assert.assertArrayEquals("abcAD".toCharArray(), chars);

        chars = "ABC".toCharArray();

        solution.sortLetters(chars);

        Assert.assertArrayEquals("ABC".toCharArray(), chars);
    }

    @Test
    public void fourSum() {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 3;

        Assert.assertEquals(new ArrayList<List<Integer>>(), solution.fourSum(numbers, target));

        numbers = new int[]{1, 0, -1, 0, -2, 2};
        target = 0;

        Assert.assertEquals(
                new ArrayList<>(Arrays.asList(
                        Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1)
                )),
                solution.fourSum(numbers, target)
        );

        numbers = new int[]{-5, -3, -2, 1, 2, 2, 3, 4, 9};
        target = 1;

        Assert.assertEquals(
                new ArrayList<>(Arrays.asList(
                        Arrays.asList(-5, 1, 2, 3), Arrays.asList(-3, -2, 2, 4)
                )),
                solution.fourSum(numbers, target)
        );
    }

    @Test
    public void threeSumClosest() {
        // write your code here
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 3;

        Assert.assertEquals(20, solution.threeSumClosest(numbers, target));

        numbers = new int[]{-1, 2, 1, -4};
        target = 1;

        Assert.assertEquals(2, solution.threeSumClosest(numbers, target));
    }

    @Test
    public void search() {
        int[] A = new int[]{4, 5, 1, 2, 3};
        int target = 1;

        Assert.assertEquals(2, solution.search(A, target));

        A = new int[]{4, 5, 1, 2, 3};
        target = 0;

        Assert.assertEquals(-1, solution.search(A, target));
    }

    @Test
    public void levelOrderBottom() {
        // write your code here
        TreeNode root = TreeNodeUtil.deserialize("{1,2,3}");

        Assert.assertEquals(Arrays.asList(
                Arrays.asList(2, 3), Collections.singletonList(1)
        ), solution.levelOrderBottom(root));


        root = TreeNodeUtil.deserialize("{3,9,20,#,#,15,7}");

        Assert.assertEquals(Arrays.asList(
                Arrays.asList(15, 7), Arrays.asList(9, 20), Collections.singletonList(3)
        ), solution.levelOrderBottom(root));

    }

    @Test
    public void zigzagLevelOrder() {
        // write your code here
        TreeNode root = TreeNodeUtil.deserialize("{1,2,3}");

        Assert.assertEquals(Arrays.asList(
                Collections.singletonList(1), Arrays.asList(3, 2)
        ), solution.zigzagLevelOrder(root));

        root = TreeNodeUtil.deserialize("{3,9,20,#,#,15,7}");

        Assert.assertEquals(Arrays.asList(
                Collections.singletonList(3), Arrays.asList(20, 9), Arrays.asList(15, 7)
        ), solution.zigzagLevelOrder(root));

        root = TreeNodeUtil.deserialize("{1,2,3,4,#,#,5,#,#,6,7,#,#,#,8}");

        Assert.assertEquals(Arrays.asList(
                Collections.singletonList(1), Arrays.asList(3, 2), Arrays.asList(4, 5),
                Arrays.asList(7, 6), Collections.singletonList(8)
        ), solution.zigzagLevelOrder(root));
    }

    @Test
    public void buildTree() {
        int[] inorder = new int[]{};
        int[] postorder = new int[]{};

        Assert.assertNull(TreeNodeUtil.serialize(solution.buildTree(inorder, postorder)));

        inorder = new int[]{1, 2, 3};
        postorder = new int[]{1, 3, 2};

        Assert.assertEquals("{2,1,3}", TreeNodeUtil.serialize(solution.buildTree(inorder, postorder)));

        inorder = new int[]{1, 2, 3};
        postorder = new int[]{3, 2, 1};

        Assert.assertEquals("{1,#,2,#,3}", TreeNodeUtil.serialize(solution.buildTree(inorder, postorder)));

        inorder = new int[]{3, 2, 4, 1, 6, 5, 7};
        postorder = new int[]{3, 4, 2, 6, 7, 5, 1};

        Assert.assertEquals("{1,2,5,3,4,6,7}", TreeNodeUtil.serialize(solution.buildTree(inorder, postorder)));
    }

    @Test
    public void buildTree_1() {
        int[] preorder = new int[]{};
        int[] inorder = new int[]{};

        Assert.assertNull(TreeNodeUtil.serialize(solution.buildTree_1(preorder, inorder)));

        preorder = new int[]{2, 1, 3};
        inorder = new int[]{1, 2, 3};

        Assert.assertEquals("{2,1,3}", TreeNodeUtil.serialize(solution.buildTree_1(preorder, inorder)));
    }

    @Test
    public void longestCommonSubsequence() {
        String A = "ABCD";
        String B = "EDCA";

        Assert.assertEquals(1, solution.longestCommonSubsequence(A, B));

        A = "ABCD";
        B = "EACB";

        Assert.assertEquals(2, solution.longestCommonSubsequence(A, B));
    }

    @Test
    public void longestCommonSubstring() {
        String A = "ABCD";
        String B = "CBCE";

        Assert.assertEquals(2, solution.longestCommonSubstring(A, B));

        A = "ABCD";
        B = "EACB";

        Assert.assertEquals(1, solution.longestCommonSubstring(A, B));
    }

    @Test
    public void kSumII() {
        int[] A = new int[]{1, 2, 3, 4};
        int k = 2;
        int targer = 5;

        Assert.assertEquals(Arrays.asList(Arrays.asList(1, 4), Arrays.asList(2, 3)), solution.kSumII(A, k, targer));

        A = new int[]{1, 3, 4, 6};
        k = 3;
        targer = 8;

        Assert.assertEquals(Collections.singletonList(Arrays.asList(1, 3, 4)), solution.kSumII(A, k, targer));

        A = new int[]{1, 4, 3, 6};
        k = 1;
        targer = 3;

        Assert.assertEquals(Collections.singletonList(Collections.singletonList(3)), solution.kSumII(A, k, targer));

        A = new int[]{1, 2, 5, 8, 11, 13};
        k = 3;
        targer = 32;

        Assert.assertEquals(Collections.singletonList(Arrays.asList(8, 11, 13)), solution.kSumII(A, k, targer));
    }

    @Test
    public void MinAdjustmentCost() {
        List<Integer> A = Arrays.asList(1, 4, 2, 3);
        int target = 1;

        Assert.assertEquals(2, solution.MinAdjustmentCost(A, target));

        A = Arrays.asList(3, 5, 4, 7);
        target = 2;

        Assert.assertEquals(1, solution.MinAdjustmentCost(A, target));

        A = Arrays.asList(12, 3, 7, 4, 5, 13, 2, 8, 4, 7, 6, 5, 7);
        target = 2;

        Assert.assertEquals(19, solution.MinAdjustmentCost(A, target));

        A = Arrays.asList(11, 11, 3, 5, 11, 16, 12, 11, 15, 11, 16, 16, 16, 16, 16, 11, 16);
        target = 0;

        Assert.assertEquals(53, solution.MinAdjustmentCost(A, target));
    }

    @Test
    public void maxPathSum() {
        TreeNode root = TreeNodeUtil.deserialize("{2}");

        Assert.assertEquals(2, solution.maxPathSum(root));

        root = TreeNodeUtil.deserialize("{1,2,3}");

        Assert.assertEquals(6, solution.maxPathSum(root));

        root = TreeNodeUtil.deserialize("{-10,-20,#,#,-31,-24,-5,#,#,-6,-7,-8,-9}");

        Assert.assertEquals(-5, solution.maxPathSum(root));
    }
}