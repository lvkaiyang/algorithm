package org.algorithm.algorithm;

import org.algorithm.algorithm.structures.basic.ListNode;
import org.algorithm.algorithm.structures.basic.TreeNode;
import org.algorithm.algorithm.structures.basic.UndirectedGraphNode;
import org.algorithm.algorithm.structures.custom.GetModifiedArrayInterval;
import org.algorithm.algorithm.structures.custom.LongestConsecutive2ResultType;
import org.algorithm.algorithm.structures.custom.ShortestPathPoint;

import java.util.*;

public class Solution {

    /*
    1345. 困于环中的机器人

    在无限平面上，机器人最初位于（0，0）并朝北。 机器人可以接收以下三个指令之一：

            “ G”：直线前进1个单位；
            “ L”：向左旋转90度；
            “ R”：向右转90度。
    机器人执行顺序给出的指令，一直重复执行。
    当且仅当平面中存在一个使机器人永远不会离开环时，才返回true。

    1. 1 <= instructions.length <= 100
    2. instructions[i] 属于 {'G', 'L', 'R'}
     */
    public boolean isRobotBounded(String instructions) {
        // write your code here
        if (instructions == null || "".equals(instructions)) return true;

        int up = 0, down = 0, right = 0, left = 0;

        int idx = 0, direction = 0; // direction: up = 0, right = 1, down = 2, left = 3;
        while (idx < instructions.length()) {
            char instruction = instructions.charAt(idx);

            if (instruction == 'G') {
                switch (direction) {
                    case 0:
                        up++;
                        break;
                    case 1:
                        right++;
                        break;
                    case 2:
                        down++;
                        break;
                    case 3:
                        left++;
                        break;
                }
            } else if (instruction == 'L') {
                direction = direction - 1 < 0 ? (direction + 3) % 4 : direction - 1;
            } else if (instruction == 'R') {
                direction = (direction + 1) % 4;
            }

            idx++;
        }

        return up == down && right == left || direction != 0; // 重复执行，只要最终不是初始方向，就还是会成环
    }

    /*
    1310. 数组除了自身的乘积

    给定n个整数的数组nums，其中n> 1，返回一个数组输出，使得output [i]等于nums的所有除了nums [i]的元素的乘积。

    在没有除和O(n)时间内解决
     */
    public int[] productExceptSelf(int[] nums) {
        // write your code here
        if (nums == null) return null;
        if (nums.length == 0) return nums;

        int[] left_multi = new int[nums.length], right_multi = new int[nums.length];

        left_multi[0] = nums[0];
        right_multi[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++)
            left_multi[i] = left_multi[i - 1] * nums[i];

        for (int i = nums.length - 2; i > -1; i--)
            right_multi[i] = right_multi[i + 1] * nums[i];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                nums[i] = right_multi[i + 1];
            } else if (i == nums.length - 1) {
                nums[i] = left_multi[i - 1];
            } else {
                nums[i] = left_multi[i - 1] * right_multi[i + 1];
            }
        }

        return nums;
    }

    /*
    1707. 骑士拨号器

    这一次，我们将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。每一步必须是从一个数字键跳到另一个数字键。

    每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N位数字。

    你能用这种方式拨出多少个不同的号码？

    因为答案可能很大，所以输出答案模 10^9 + 7。

    电话拨号盘

    1 2 3
    4 5 6
    7 8 9
      0
     */
    public int knightDialer(int N) {
        // write your code here
        int res = 0;
        if (N < 1) return res;

        int[][] dp = new int[2][10];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                switch (j) {
                    case 1:
                        dp[i % 2][j] = (dp[(i - 1) % 2][6] + dp[(i - 1) % 2][8]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 2:
                        dp[i % 2][j] = (dp[(i - 1) % 2][7] + dp[(i - 1) % 2][9]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 3:
                        dp[i % 2][j] = (dp[(i - 1) % 2][4] + dp[(i - 1) % 2][8]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 4:
                        int sub_sum = (dp[(i - 1) % 2][0] + dp[(i - 1) % 2][3]) % ((int) Math.pow(10, 9) + 7);
                        dp[i % 2][j] = (sub_sum + dp[(i - 1) % 2][9]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 5:
                        dp[i % 2][j] = 0;
                        break;
                    case 6:
                        sub_sum = (dp[(i - 1) % 2][0] + dp[(i - 1) % 2][1]) % ((int) Math.pow(10, 9) + 7);
                        dp[i % 2][j] = (sub_sum + dp[(i - 1) % 2][7]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 7:
                        dp[i % 2][j] = (dp[(i - 1) % 2][2] + dp[(i - 1) % 2][6]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 8:
                        dp[i % 2][j] = (dp[(i - 1) % 2][1] + dp[(i - 1) % 2][3]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 9:
                        dp[i % 2][j] = (dp[(i - 1) % 2][2] + dp[(i - 1) % 2][4]) % ((int) Math.pow(10, 9) + 7);
                        break;
                    case 0:
                        dp[i % 2][j] = (dp[(i - 1) % 2][4] + dp[(i - 1) % 2][6]) % ((int) Math.pow(10, 9) + 7);
                        break;
                }
            }
        }

        for (int sum : dp[(N - 1) % 2])
            res = (res + sum) % ((int) Math.pow(10, 9) + 7);

        return res;
    }

    /*
    903. 范围加法

    假设你有一个长度为n的数组，数组的所有元素初始化为0，并且给定k个更新操作。

    每个更新操作表示为一个三元组：[startIndex, endIndex, inc]。

    这个更新操作给子数组 A[startIndex ... endIndex]（包括startIndex和endIndex）中的每一个元素增加 inc。

    返回执行k个更新操作后的新数组。
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here
        if (length < 1) return null;

        int[] res = new int[length];

        if (updates == null) return res;
        if (updates.length == 0 || updates[0].length == 0) return res;

        List<GetModifiedArrayInterval> points = new ArrayList<>();

        for (int[] update : updates) {
            points.add(new GetModifiedArrayInterval(update[0], update[2]));
            points.add(new GetModifiedArrayInterval(update[1] + 1, -update[2]));
        }

        points.sort(Comparator.comparingInt(GetModifiedArrayInterval::getIdx));

        int sum = 0;
        int curr_idx = 0;
        for (int i = 0; i < points.size(); i++) {
            GetModifiedArrayInterval curr = points.get(i);
            Arrays.fill(res, curr_idx, curr.getIdx(), sum);

            sum += curr.getInc();

            while (i + 1 < points.size() && points.get(i + 1).getIdx() == curr.getIdx())
                sum += points.get(i++ + 1).getInc();

            curr_idx = curr.getIdx();
        }

        return res;
    }

    /*
    1563. 目的地的最短路径

    给定表示地图上坐标的2D数组，地图上只有值0,1,2.0表示可以通过，1表示不可通过，2表示目标位置。

    从坐标[0,0]开始，你只能上，下，左，右移动。找到可以到达目的地的最短路径，并返回路径的长度。

    1.地图一定存在且不为空，并且只存在一个目的地
    2.保证targetMap[0][0] = 0targetMap[0][0]=0
     */
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        // 不可达时假设为-1
        if (targetMap == null) return -1;
        if (targetMap.length == 0 || targetMap[0].length == 0) return -1;

        int m = targetMap.length, n = targetMap[0].length, step = 0;
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        boolean[][] visited = new boolean[m][n];

        Queue<ShortestPathPoint> queue = new LinkedList<>();
        queue.offer(new ShortestPathPoint(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                ShortestPathPoint curr = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int new_x = curr.getX() + dx[j];
                    int new_y = curr.getY() + dy[j];

                    boolean in_bound = new_x > -1 && new_x < n && new_y > -1 && new_y < m;
                    if (in_bound && !visited[new_y][new_x] && targetMap[new_y][new_x] != 1) {
                        queue.offer(new ShortestPathPoint(new_x, new_y, targetMap[new_y][new_x]));
                        visited[new_y][new_x] = true;
                        if (targetMap[new_y][new_x] == 2)
                            return step;
                    }
                }
            }
        }

        return -1;
    }

    /*
    1492. 爱吃香蕉的珂珂

    珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

    珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。

    如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。

    珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

    返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

    1. 1 <= piles.length <= 10^4
    2. piles.length <= H <= 10^9
    3. 1 <= piles[i] <= 10^9
     */
    public int minEatingSpeed(int[] piles, int H) {
        // Write your code here
        int K = -1;
        if (piles == null) return K;
        if (piles.length == 0 || piles.length > (int) Math.pow(10, 4)) return K;
        if (H < piles.length || H > (int) Math.pow(10, 9)) return K;

        int max_k = Integer.MIN_VALUE, min_k = Integer.MAX_VALUE;
        for (int pile : piles) {
            if (pile < 1 || pile > (int) Math.pow(10, 9)) return K;
            max_k = Math.max(max_k, pile);
            min_k = Math.min(min_k, pile);
        }

        int start = 1, end = max_k;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (minEatingSpeed_helper(mid, piles) <= H) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (minEatingSpeed_helper(end, piles) <= H) K = end;
        if (minEatingSpeed_helper(start, piles) <= H) K = start;

        return K;
    }

    private int minEatingSpeed_helper(int K, int[] piles) {
        int hours = 0;

        for (int pile : piles)
            hours += (int) Math.ceil((double) pile / K);

        return hours;
    }

    /*
    1031. 图可以被二分么？

    给定一个无向图 graph, 输出 true 当且仅当这个图是可以被二分的（也叫二部图）。

    如果一个图是二部图，则意味着我们可以将图里的点集分为两个独立的子集A和B，并且图中所有的边都是一个端点属于A，另一个端点属于B。

    关于图的表示：graph[i] 为一个列表，表示与节点i有边相连的节点。这个图中一共有 graph.length 个节点，为0到graph.length-1。

    图中没有自边或者重复的边存在，即: graph[i] 中不包含 i, 也不会包含某个点两次。

    1. graph 中包含的总节点数的范围为 [1, 100]。
    2. graph[i] 只包含范围为 [0, graph.length - 1].中的一些整数。
    3. graph[i] 不会包含 i 自己或是某个值两次。
    4. 图是无向的：如果点 j 存在于 graph[i]这个列表里，则 i 也会存在于 graph[j]这个列表里
     */
    public boolean isBipartite(int[][] graph) {
        // Write your code here
        if (graph == null) return true;
        if (graph.length < 2) return true;

        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();

        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) continue;
            if (!A.contains(i) && !B.contains(i)) {
                A.add(i);
                for (int next : graph[i])
                    B.add(next);
            } else if (A.contains(i) && B.contains(i)) {
                return false;
            } else if (A.contains(i)) {
                for (int next : graph[i]) {
                    if (A.contains(next)) return false;
                    B.add(next);
                }

            } else if (B.contains(i)) {
                for (int next : graph[i]) {
                    if (B.contains(next)) return false;
                    A.add(next);
                }
            }
        }

        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) continue;
            if (A.contains(i) && B.contains(i)) return false;
        }

        return true;
    }

    /*
    1203. 寻找BST的modes

    给定具有重复项的二叉搜索树（BST），找到给定BST中的所有modes（最频繁出现的元素）。

    在这里假设一个BST定义如下：

    节点的左子树仅包含键小于或等于父节点的节点。
    节点的右子树仅包含键大于或等于父节点的节点。
    左右子树也必须是二叉搜索树。

    1. 如果树有多个modes，您可以按任何顺序返回它们。
     */
    public int[] findMode(TreeNode root) {
        // write your code here
        if (root == null) return new int[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        findMode_helper(root, map);

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            max = Math.max(max, entry.getValue());

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            if (entry.getValue() == max)
                list.add(entry.getKey());

        int[] res = new int[list.size()];
        int idx = 0;
        for (int i : list)
            res[idx++] = i;

        return res;
    }

    private void findMode_helper(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) return;

        findMode_helper(root.left, map);
        findMode_helper(root.right, map);

        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
    }

    /*
    1164. 摆动序列

    如果连续数字之间的差严格地在正和负之间交替，则这样的数字序列称为摆动序列。

    第一个差值（如果存在）可以是正的也可以是负的。 少于两个元素的序列通常是摆动序列。

    例如，[1,7,4,9,2,5]是一个摆动序列，因为连续数字的差(6,-3,5,-7,3)交替为正和负。

    相反，[1,4,7,2,5]和[1,7,4,5,5]不是摆动序列，第一个是因为它的前两个连续数字的差是正的，而第二个是因为它的最后一个连续数字的差零。

    给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些元素（和0）来获得子序列，使剩余元素保持其原始顺序。
     */
    public int wiggleMaxLength(int[] nums) {
        // Write your code here
        if (nums.length < 2) return nums.length;

        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) up = down + 1;
            if (nums[i] < nums[i - 1]) down = up + 1;
        }

        return Math.max(up, down);
    }

    /*
    1195. 找出树中每行的最大值

    你需要找到在一棵二叉树中，每一行的最大值
     */
    public List<Integer> largestValues(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(), max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                max = Math.max(max, curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(max);
        }

        return res;
    }

    /*
    719. 计算最大值

    给一个字符串类型的数字, 写一个方法去找到最大值, 你可以在任意两个数字间加 + 或 *
     */
    public int calcMaxValue(String str) {
        // write your code here
        int res = 0;
        if (str == null || "".equals(str)) return res;

        res = calcMaxValue_helper(0, 0, str);

        return res;
    }

    private int calcMaxValue_helper(int idx, int res, String str) {
        if (idx == str.length()) return res;

        char c = str.charAt(idx);
        int num = c - '0';

        res = Math.max(res + num, res * num);
        return calcMaxValue_helper(idx + 1, res, str);
    }

    /*
    906. 对数组变换排序

    给定一个已排序的整数数组和整数a,b,c。

    对数组中的每个元素xx应用二次函数f(x)=ax^2+bx+c

    返回的数组必须是有序的。

    1. 期望时间复杂度：O(n)
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        // Write your code here
        if (nums == null) return null;
        int[] res = new int[nums.length];
        if (nums.length == 0) return res;
        if (a == 0) {
            if (b < 0) {
                for (int i = nums.length - 1; i > -1; i--)
                    res[nums.length - 1 - i] = b * nums[i] + c;
            } else {
                for (int i = 0; i < nums.length; i++)
                    res[i] = b * nums[i] + c;
            }
            return res;
        }

        double axis = -b / (double) (2 * a);

        int start_idx = 0, idx = 0;
        while (nums[start_idx] < axis)
            start_idx++;

        if (a > 0) {
            int left = start_idx - 1, right = start_idx;
            while (left > -1 && right < nums.length) {
                if (axis - nums[left] < nums[right] - axis) {
                    res[idx++] = nums[left--];
                } else {
                    res[idx++] = nums[right++];
                }
            }
            while (left > -1)
                res[idx++] = nums[left--];

            while (right < nums.length)
                res[idx++] = nums[right++];
        } else {
            int left = 0, right = nums.length - 1;
            while (left < start_idx && right >= start_idx) {
                if (axis - nums[left] < nums[right] - axis) {
                    res[idx++] = nums[right--];
                } else {
                    res[idx++] = nums[left++];
                }
            }
            while (left < start_idx)
                res[idx++] = nums[left++];

            while (right >= start_idx)
                res[idx++] = nums[right--];
        }

        for (int i = 0; i < res.length; i++)
            res[i] = a * res[i] * res[i] + b * res[i] + c;

        return res;
    }

    /*
    937. 可以完成的题目数量

    给定一个正整数n，表示一场比赛的时间，比赛中题目的难度是递增的，你每完成一个题目，就要花费k × i的时间，

    其中k是输入的一个系数，i表示题目的序号(从1开始)。根据这些信息，返回这场比赛中，你最多能完成几个题目。

    1. 1<=n<=2^61-1 1<=k<=50
     */
    public long canAccept(long n, int k) {
        // Write your code here
        if (n < 1 || n > Math.pow(2, 61) - 1 || k < 1 || k > 50) return -1;

        double target = n / (double) k;

        long left, right = 2;

        while (right * (1 + right) / 2d < target)
            right = right << 1;

        left = right >> 1;

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (mid * (1 + mid) / 2d < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right * (1 + right) / 2d <= target) return right;
        if (left * (1 + left) / 2d <= target) return left;

        return -1;
    }

    /*
    588. 划分和相等的子集

    给一 只含有正整数 的 非空 数组, 找到这个数组是否可以划分为 两个 元素和相等的子集。

    1. 所有数组元素不超过100.
    2. 数组大小不超过200.
     */
    public boolean canPartition(int[] nums) {
        // write your code here
        if (nums == null) return true;
        if (nums.length == 0) return true;

        Arrays.sort(nums);
        int[] sums = new int[nums.length + 1];

        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        if (sums[sums.length - 1] % 2 != 0) return false;

        int left = 0, right = 0, target = sums[sums.length - 1] / 2;
        while (right < sums.length) {
            if (sums[right] - sums[left] < target) {
                right++;
            } else if (sums[right] - sums[left] == target) {
                return true;
            } else {
                left++;
            }
        }

        return false;
    }

    /*
    814. 无向图中的最短路径

    给定一个无向图, 图中所有边的长度为1, 再选定图中的两个节点, 返回这两个节点之间最短的路径的长度.
     */
    public int shortestPath(List<UndirectedGraphNode> graph, UndirectedGraphNode A, UndirectedGraphNode B) {
        // Write your code here
        int res = 0;
        if (graph == null) return res;
        if (graph.size() == 0) return res;
        if (A == null || B == null) return res;

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> visited = new HashSet<>();

        queue.offer(A);
        visited.add(A);

        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode curr = queue.poll();
                for (UndirectedGraphNode neighbor : curr.neighbors) {
                    if (visited.add(neighbor)) {
                        if (neighbor == B) return res;
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return 0;
    }

    /*
    982. 等差切片

    如果一个数字序列由至少三个元素组成并且任何两个连续元素之间的差值相同，则称为等差数列。

    举个例子，这些是等差数列：

    1, 3, 5, 7, 9
    7, 7, 7, 7
    3, -1, -5, -9

    下面的序列不是等差数列：

    1, 1, 2, 5, 7

    给一个由 N 个数组成且下标从 0 开始的数组A。这个数组的一个切片是指任意一个整数对 (P, Q) 且满足 0 <= P < Q < N。

    如果 A 中的一个切片(P, Q) 是等差切片，则需要满足A[P], A[P + 1], ..., A[Q - 1], A[Q] 是等差的。还需要注意的是，这也意味着 P + 1 < Q。

    需要实现的函数应该返回数组 A 中等差切片的数量。
     */
    public int numberOfArithmeticSlices(int[] A) {
        // Write your code here
        int res = 0;
        if (A == null) return res;
        if (A.length < 3) return res;

        int left = 0, right = 2;
        while (right < A.length) {
            // 不再是等差数列
            if (A[right - 1] - A[right - 2] != A[right] - A[right - 1]) {
                // 开头就不是等差的忽略
                if (!(left == 0 && right == 2))
                    /*
                    数组  [1, 2, 3, 4, 6]         长度 4    结果 (2 + 1)
                    数组  [1, 2, 3, 4, 5, 6]      长度 5    结果 (3 + 2 + 1)
                    数组  [1, 2, 3, 4, 5, 6, 7]   长度 6    结果 (4 + 3 + 2 + 1)
                    (right - left - 1) 首项加末项 (right - left - 2) 项数
                     */
                    res += (right - left - 1) * (right - left - 2) / 2;
                left = right - 1;
            }
            right++;
        }

        // 末尾项是等差的情况
        if (right - left > 1)
            res += (right - left - 1) * (right - left - 2) / 2;

        return res;
    }

    /*
    883. 最长连续的1 II

    给出一个二进制数组，在最多翻转一位0的情况下，找到这个数组里最长的连续的1的个数。

    1. 输入数组只含有0 和 1。
    2. 数组的长度是一个正整数，并且长度不会超过10,000。
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        // write your code here
        int res = 0;
        if (nums == null) return res;
        if (nums.length == 0) return res;

        int left = 0, right = 0, prev_zero_idx = -1;
        while (right < nums.length) {
            if (nums[right] == 0) {
                if (prev_zero_idx != -1) {
                    res = Math.max(res, right - left);
                    left = prev_zero_idx + 1;
                }
                prev_zero_idx = right;
            }
            right++;
        }

        return res;
    }

    /*
    1191. 最长非公共子序列之2

    给定一组字符串，你需要找到这组字符串中最长的非公共子序列。

    最长的非公共子序列被定义为这些字符串之一的最长子序列，并且此子序列不应该是其他字符串的子序列。

    子序列是可以通过删除一些字符而不改变其余元素的顺序从一个序列导出的序列。可以说，任何字符串都是自身的子序列，空字符串是任何字符串的子序列。

    输入将是字符串列表，输出需要是最长的非公共子序列的长度。 如果最长的非公共子序列不存在，则返回-1。

    1. 所有给定的字符串长度不超过10。
    2. 给定列表的长度将在[2,50]的范围内。
     */
    public int findLUSlength(String[] strs) {
        // write your code here
        int res = -1;
        if (strs == null) return res;
        if (strs.length < 2) return res;

        Arrays.sort(strs, Comparator.comparing(String::length).reversed());

        for (int i = 0; i < strs.length; i++) {
            boolean is_seq = false;
            for (int j = 0; j < strs.length && strs[j].length() >= strs[i].length(); j++) {
                if (j == i) continue;
                int idx = 0;
                for (int k = 0; k < strs[j].length(); k++) {
                    if (strs[j].charAt(k) == strs[i].charAt(idx)) idx++;
                    if (idx == strs[i].length()) {
                        is_seq = true;
                        break;
                    }
                }
                if (is_seq) break;
            }
            if (!is_seq) return strs[i].length();
        }

        return res;
    }

    /*
    614. 二叉树的最长连续子序列 II

    给定一棵二叉树，找到最长连续序列(单调且相邻节点值相差为1)路径的长度(节点数)。

    路径起点跟终点可以为二叉树的任意节点。
     */
    public int longestConsecutive2(TreeNode root) {
        // write your code here
        if (root == null) return 0;

        return longestConsecutive2_helper(root).max;
    }

    private LongestConsecutive2ResultType longestConsecutive2_helper(TreeNode root) {
        if (root == null) return new LongestConsecutive2ResultType(0, 0, 0);

        LongestConsecutive2ResultType left = longestConsecutive2_helper(root.left);
        LongestConsecutive2ResultType right = longestConsecutive2_helper(root.right);

        int up = 1, down = 1, max = 0;
        if (root.left != null && root.right != null) {
            if (root.left.val - 1 == root.val) {
                up = Math.max(up, left.up + 1);
            }
            if (root.left.val + 1 == root.val) {
                down = Math.max(down, left.down + 1);
            }
            if (root.right.val - 1 == root.val) {
                up = Math.max(up, right.up + 1);
            }
            if (root.right.val + 1 == root.val) {
                down = Math.max(down, right.down + 1);
            }
            if (root.left.val - 1 == root.val && root.right.val + 1 == root.val) {
                max = Math.max(max, left.up + right.down + 1);
            }
            if (root.right.val - 1 == root.val && root.left.val + 1 == root.val) {
                max = Math.max(max, right.up + left.down + 1);
            }
            max = Math.max(max, left.max);
            max = Math.max(max, right.max);
        } else if (root.left != null) {
            if (root.left.val - 1 == root.val) {
                up = Math.max(up, left.up + 1);
            }
            if (root.left.val + 1 == root.val) {
                down = Math.max(down, left.down + 1);
            }
            max = Math.max(max, left.max);
        } else if (root.right != null) {
            if (root.right.val - 1 == root.val) {
                up = Math.max(up, right.up + 1);
            }
            if (root.right.val + 1 == root.val) {
                down = Math.max(up, right.down + 1);
            }
            max = Math.max(max, right.max);
        }

        max = Math.max(max, up);
        max = Math.max(max, down);

        return new LongestConsecutive2ResultType(up, down, max);
    }

    /*
    774. 重复

    所有的DNA由一系列缩写的核苷酸 A, C, G 和 T组成.

    比如: "ACGAATTCCG". 在研究 DNA 时, 有时候鉴别出 DNA 中的重复序列是很有用的.

    写一个函数来找到所有在 DNA 中出现超过一次且长度为 10个字母 的序列(子串).
     */
    public List<String> findRepeatedDna(String s) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (s == null || "".equals(s)) return res;
        if (s.length() < 10) return res;

        HashMap<String, Integer> dna_map = new HashMap<>();
        int left = 0, right = 0;

        while (right < s.length() + 1) {
            if (right - left == 10) {
                String substring = s.substring(left, right);
                if (dna_map.containsKey(substring) && dna_map.get(substring) == 1) {
                    dna_map.put(substring, dna_map.get(substring) + 1);
                    res.add(substring);
                } else {
                    dna_map.put(substring, dna_map.getOrDefault(substring, 0) + 1);
                }
                left++;
            }
            right++;
        }

        return res;
    }

    /*
    843. 数字翻转

    给定一个01构成的数组。你可以翻转1变成0或者反转0变成1。

    请问最少反转多少次可以使得数组满足以下规则：

    1的后面可以是1或者0，而0的后面必须是0。

    1. 数组长度 n <= 100000。
     */
    public int flipDigit(int[] nums) {
        // Write your code here
        int res = Integer.MAX_VALUE;
        if (nums == null) return res;
        if (nums.length == 0) return res;

        // 前i项改成 0 或者 1 的翻转数
        int[][] dp = new int[2][2];

        for (int i = 1; i < nums.length + 1; i++) {
            if (nums[i - 1] == 0) {
                // 0 可以是1或者0的后面数字，取翻转数最小的
                dp[0][i % 2] = Math.min(dp[0][(i - 1) % 2], dp[1][(i - 1) % 2]);
                dp[1][i % 2] = dp[1][(i - 1) % 2] + 1;
            } else if (nums[i - 1] == 1) {
                // 0 可以是1或者0的后面数字，由 1 改成 0 需要+1并把前面改0翻回1
                dp[0][i % 2] = Math.min(dp[0][(i - 1) % 2] + 1, dp[1][(i - 1) % 2]);
                dp[1][i % 2] = dp[1][(i - 1) % 2];
            }
        }

        return Math.min(dp[0][nums.length % 2], dp[1][nums.length % 2]);
    }

    /*
    1719. 三数之和的多种可能

    给定一个整数数组 A，以及一个整数 target 作为目标值，返回满足 i < j < k 且 A[i] + A[j] + A[k] == target 的元组 i, j, k 的数量。

    由于结果会非常大，请返回 结果除以 10^9 + 7 的余数。

    1. 3 <= A.length <= 3000
    2. 0 <= A[i] <= 100
    3. 0 <= target <= 300
     */
    public int threeSumMulti(int[] A, int target) {
        // Write your code here
        long res = 0;
        if (A == null) return (int) res;
        if (A.length < 3 || A.length > 3000) return (int) res;
        if (target < 0 || target > 300) return (int) res;

        TreeMap<Integer, Long> tm = new TreeMap<>();
        int MOD = (int) (Math.pow(10, 9) + 7);
        for (int a : A)
            tm.put(a, tm.getOrDefault(a, 0L) + 1);

        List<Integer> keys = new ArrayList<>(tm.keySet());

        for (int i = 0; i < keys.size(); i++) {
            int first = keys.get(i);
            int left = i;
            int right = keys.size() - 1;
            int t = target - keys.get(i);
            while (left <= right) {
                if (keys.get(left) + keys.get(right) == t) {
                    if (first == keys.get(left) && first == keys.get(right)) {
                        long n = tm.get(first);
                        res += (n * (n - 1) * (n - 2) / (3 * 2)) % MOD;
                    } else if (first == keys.get(left) && keys.get(left) < keys.get(right)) {
                        long n = tm.get(first);
                        res += (n * (n - 1) / 2 * tm.get(keys.get(right))) % MOD;
                    } else if (first < keys.get(left) && keys.get(left) < keys.get(right)) {
                        res += (tm.get(first) * tm.get(keys.get(left)) * tm.get(keys.get(right))) % MOD;
                    } else if (first < keys.get(left) && keys.get(left).equals(keys.get(right))) {
                        long n = tm.get(keys.get(left));
                        res += (tm.get(first) * n * (n - 1) / 2) % MOD;
                    }
                    res %= MOD;
                    left++;
                    right--;
                } else if (keys.get(left) + keys.get(right) < t) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return (int) res;
    }

    /*
    3. 统计数字

    计算数字 k 在 0 到 n 中的出现的次数，k 可能是 0~9 的一个值。
     */
    public int digitCounts(int k, int n) {
        // write your code here
        int res = 0;
        if (k < 0 || k > 9) return res;
        if (n < 10) return n < k ? 0 : 1;

        int[] map = new int[n + 1];
        map[k] = 1;
        res++;

        for (int i = 10; i < n + 1; i++) {
            map[i] = map[i / 10] + (i % 10 == k ? 1 : 0);
            res += map[i];
        }

        return res;
    }

    /*
    5. 第k大元素

    在数组中找到第 k 大的元素。

    1. 你可以交换数组中的元素的位置
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        if (n < 1 || nums == null) return -1;
        if (n > nums.length) return -1;

        return kthLargestElement_helper(0, nums.length - 1, nums, nums.length - n);
    }

    private int kthLargestElement_helper(int start, int end, int[] nums, int n) {
        if (start >= end) return nums[n];

        int left = start, right = end, pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot)
                left++;
            while (left <= right && nums[right] > pivot)
                right--;

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        if (n >= left) return kthLargestElement_helper(left, end, nums, n);
        if (n <= right) return kthLargestElement_helper(start, right, nums, n);

        return nums[n];
    }

    /*
    11. 二叉查找树中搜索区间

    给定一个二叉查找树和范围[k1, k2]。按照升序返回给定范围内的节点值。
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (root == null || k1 > k2) return res;

        searchRange_helper(root, k1, k2, res);

        return res;
    }

    private void searchRange_helper(TreeNode root, int k1, int k2, List<Integer> res) {

        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val >= k1 && root.val <= k2)
                res.add(root.val);
            return;
        }

        if (root.left != null && root.left.val >= k1) {
            searchRange_helper(root.left, k1, k2, res);
        } else if (root.left != null) {
            searchRange_helper(root.left.right, k1, k2, res);
        }

        if (root.val >= k1 && root.val <= k2)
            res.add(root.val);

        if (root.right != null && root.right.val <= k2) {
            searchRange_helper(root.right, k1, k2, res);
        } else if (root.right != null) {
            searchRange_helper(root.right.left, k1, k2, res);
        }
    }

    /*
    21. 移动的圆

    The problem will give the center coordinates (x, y) and radius r of the two circles A and B.

    Then the problem give you a point P ,and move the center of circle A to the point P along a straight line.

    Will the circle A intersect with the circle B during the move?

    (The moving process includes the starting point and an ending point)

    If it they intersect return 1, otherwise return -1.

    1. The radius of both circles does not exceed 10000.

    2. The absolute value of the abscissa and ordinate values does not exceed 10000.

    3. The list in input represent [X_A, Y_A, R_A, X_B, Y_B, R_B, X_P, Y_P].
     */
    public int IfIntersect(double[] position) {

        if (position == null || position.length != 8) return -1;

        double X_A = position[0], Y_A = position[1], R_A = position[2];
        double X_B = position[3], Y_B = position[4], R_B = position[5];
        double X_P = position[6], Y_P = position[7];

        /*
        思路是A半径和B半径之和为大圆半径，以B为圆心，X^2 + Y^2 = R^2，的解即为A圆心的运动轨迹，

        求此方程与直线方程AP的解，转化为求二次方程解，根据解个数判断是否相交（相离为-1，相交或相切为1）
         */

        return -1;
    }

    /*
    29. 交叉字符串

    给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        if (s1 == null && s2 == null) return s3 == null;
        if (s1 == null || s2 == null) return s3 == null;
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < s1.length() + 1; i++) {
            if (s3.charAt(i - 1) == s1.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i < s2.length() + 1; i++) {
            if (s3.charAt(i - 1) == s2.charAt(i - 1)) {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        dp[0][0] = true;

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                char c = s3.charAt(i + j - 1);
                if (s2.charAt(j - 1) == c) {
                    dp[i][j] = dp[i][j - 1];
                }
                if (s1.charAt(i - 1) == c) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j];
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    /*
    33. N皇后问题

    n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击(任意两个皇后不能位于同一行，同一列，同一斜线)。

    给定一个整数n，返回所有不同的n皇后问题的解决方案。

    每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
     */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> res = new ArrayList<>();

        if (n < 1) return res;

        solveNQueens_helper(n, 0, new boolean[n][n], new ArrayList<>(), res);

        return res;
    }

    private void solveNQueens_helper(
            int n, int level, boolean[][] conflicts, List<String> subList, List<List<String>> res
    ) {

        if (level == n) {
            res.add(new ArrayList<>(subList));
            return;
        }

        for (int i = 0; i < n; i++) {

            boolean conflict = false;

            for (int j = level - 1; j > -1; j--) {
                if (conflicts[j][i]) {
                    conflict = true;
                    break;
                }
            }

            for (int j = level - 1, k = i - 1; j > -1 && k > -1; j--, k--) {
                if (conflicts[j][k]) {
                    conflict = true;
                    break;
                }
            }

            for (int j = level - 1, k = i + 1; j > -1 && k < n; j--, k++) {
                if (conflicts[j][k]) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {

                conflicts[level][i] = true;

                // draw
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == i) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }

                subList.add(sb.toString());

                solveNQueens_helper(n, level + 1, conflicts, subList, res);

                subList.remove(subList.size() - 1);

                conflicts[level][i] = false;
            }
        }
    }

    /*
    34. N皇后问题 II

    根据n皇后问题，现在返回n皇后不同的解决方案的数量而不是具体的放置布局。
     */
    public int totalNQueens(int n) {
        // write your code here
        if (n < 1) return 0;

        return totalNQueens_helper(n, 0, new boolean[n][n]);
    }

    private int totalNQueens_helper(
            int n, int level, boolean[][] conflicts
    ) {

        int res = 0;

        if (level == n) {
            return 1;
        }

        for (int i = 0; i < n; i++) {

            boolean conflict = false;

            for (int j = level - 1; j > -1; j--) {
                if (conflicts[j][i]) {
                    conflict = true;
                    break;
                }
            }

            for (int j = level - 1, k = i - 1; j > -1 && k > -1; j--, k--) {
                if (conflicts[j][k]) {
                    conflict = true;
                    break;
                }
            }

            for (int j = level - 1, k = i + 1; j > -1 && k < n; j--, k++) {
                if (conflicts[j][k]) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {

                conflicts[level][i] = true;

                res += totalNQueens_helper(n, level + 1, conflicts);

                conflicts[level][i] = false;
            }
        }

        return res;
    }

    /*
    36. 翻转链表 II

    翻转链表中第m个节点到第n个节点的部分

    1. m，n满足1 ≤ m ≤ n ≤ 链表长度
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        if (head == null) return null;

        ListNode dummy = new ListNode(-1), prev = null, temp, target = dummy;
        dummy.next = head;

        while (head != null && m != 1) {
            target = head;
            m--;
            n--;
            head = head.next;
        }

        ListNode curr = head;

        while (head != null && n != 0) {
            temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
            n--;
        }

        curr.next = head;
        target.next = prev;

        return dummy.next;
    }

    /*
    42. 最大子数组 II

    给定一个整数数组，找出两个 不重叠 子数组使得它们的和最大。

    每个子数组的数字在数组中的位置应该是连续的。

    返回最大的和。

    1. 子数组最少包含一个数
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() < 1) return Integer.MIN_VALUE;

        int size = nums.size();
        int[] right = new int[size], left = new int[size];

        left[size - 1] = nums.get(size - 1);
        right[0] = nums.get(0);

        // 先找前i + 1个数中最大和（i为终点）以及后size - i - 2个数中最大和（i为起始点）
        // 分别为right和left
        for (int i = 0; i < size - 1; i++) {
            right[i + 1] = Math.max(right[i] + nums.get(i + 1), nums.get(i + 1));
            left[size - i - 2] = Math.max(left[size - i - 1] + nums.get(size - i - 2), nums.get(size - i - 2));
        }

        // 打擂台，找到前i个数中最大和以及后size - i - 1个数中最大和（动态规划）
        for (int i = 0; i < size - 1; i++) {
            right[i + 1] = Math.max(right[i + 1], right[i]);
            left[size - i - 2] = Math.max(left[size - i - 2], left[size - i - 1]);
        }

        // 前i个最大和 + 后i + 1个最大和
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++)
            res = Math.max(res, left[i + 1] + right[i]);

        return res;
    }

    /*
    45. 最大子数组差

    给定一个整数数组，找出两个不重叠的子数组A和B，使两个子数组和的差的绝对值|SUM(A) - SUM(B)|最大。

    返回这个最大的差值。

    1. 子数组最少包含一个数
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        int ans = Integer.MIN_VALUE;

        if (nums == null || nums.length < 1) return ans;

        int size = nums.length;
        int[] leftMax = new int[size], leftMin = new int[size], rightMax = new int[size], rightMin = new int[size];

        leftMax[size - 1] = leftMin[size - 1] = nums[size - 1];
        rightMax[0] = rightMin[0] = nums[0];

        for (int i = 0; i < size - 1; i++) {
            rightMax[i + 1] = Math.max(rightMax[i] + nums[i + 1], nums[i + 1]);
            rightMin[i + 1] = Math.min(rightMin[i] + nums[i + 1], nums[i + 1]);
            leftMax[size - i - 2] = Math.max(leftMax[size - i - 1] + nums[size - i - 2], nums[size - i - 2]);
            leftMin[size - i - 2] = Math.min(leftMin[size - i - 1] + nums[size - i - 2], nums[size - i - 2]);
        }

        for (int i = 0; i < size - 1; i++) {
            rightMax[i + 1] = Math.max(rightMax[i + 1], rightMax[i]);
            rightMin[i + 1] = Math.min(rightMin[i + 1], rightMin[i]);
            leftMax[size - i - 2] = Math.max(leftMax[size - i - 2], leftMax[size - i - 1]);
            leftMin[size - i - 2] = Math.min(leftMin[size - i - 2], leftMin[size - i - 1]);
        }

        for (int i = 0; i < size - 1; i++) {
            ans = Math.max(ans, Math.abs(rightMax[i] - leftMax[i + 1]));
            ans = Math.max(ans, Math.abs(rightMax[i] - leftMin[i + 1]));
            ans = Math.max(ans, Math.abs(rightMin[i] - leftMax[i + 1]));
            ans = Math.max(ans, Math.abs(rightMin[i] - leftMin[i + 1]));
        }

        return ans;
    }

    /*
    47. 主元素 II

    给定一个整型数组，找到主元素，它在数组中的出现次数严格大于数组元素个数的三分之一。

    1. 数组中只有唯一的主元素
     */
    public int majorityNumber(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) return Integer.MIN_VALUE;

        if (nums.size() < 3) return nums.get(0);

        int cnt_1 = 0, cnt_2 = 0, target_1 = 1, target_2 = -1;

        for (int num : nums) {
            if (num == target_1) {
                cnt_1++;
            } else if (num == target_2) {
                cnt_2++;
            } else if (cnt_1 == 0) {
                target_1 = num;
                cnt_1++;
            } else if (cnt_2 == 0) {
                target_2 = num;
                cnt_2++;
            } else {
                cnt_1--;
                cnt_2--;
            }
        }

        cnt_1 = cnt_2 = 0;

        for (int num : nums) {
            if (target_1 == num) cnt_1++;
            if (target_2 == num) cnt_2++;
        }

        return cnt_1 >= cnt_2 ? target_1 : target_2;
    }

    /*
    48. 主元素 III

    给定一个整型数组，找到主元素，它在数组中的出现次数严格大于数组元素个数的1/k。

    1.数组中只有唯一的主元素
     */
    public int majorityNumber(List<Integer> nums, int k) {
        // write your code here
        if (nums == null || nums.size() < 1 || k < 0) return Integer.MIN_VALUE;

        Map<Integer, Integer> target_count = new HashMap<>();
        int size = nums.size() / k;

        for (int num : nums)
            target_count.put(num, target_count.getOrDefault(num, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : target_count.entrySet())
            if (entry.getValue() > size)
                return entry.getKey();

        return Integer.MIN_VALUE;
    }

    /*
    49. 字符大小写排序

    给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序。

    1. 小写字母或者大写字母他们之间不一定要保持在原始字符串中的相对位置。
     */
    public void sortLetters(char[] chars) {
        // write your code here
        if (chars == null || chars.length == 0) return;

        int left = 0, right = chars.length - 1;

        while (left <= right) {
            while (left <= right && chars[left] >= 'a' && chars[left] <= 'z') left++;
            while (left <= right && chars[right] >= 'A' && chars[right] <= 'Z') right--;
            if (left <= right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
    }

    /*
    快速排序算法
     */
    public void quickSort(int[] nums) {

        if (nums == null || nums.length == 0) return;

        quickSort_helper(0, nums.length - 1, nums);
    }

    public void quickSort_helper(int start, int end, int[] nums) {
        if (start >= end) return;

        int pivot = nums[start + (end - start) / 2];
        int left = start, right = end;

        while (left <= right) {
            while (left <= right && nums[left] < pivot) left++;
            while (left <= right && nums[right] > pivot) right--;
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        quickSort_helper(start, right, nums);
        quickSort_helper(left, end, nums);
    }

    /*
    外排序算法
     */
    public void mergeSort(int[] nums) {

        if (nums == null || nums.length == 0) return;

        mergeSort_helper(0, nums.length - 1, new int[nums.length], nums);
    }

    public void mergeSort_helper(int start, int end, int[] temp, int[] nums) {

        if (start >= end) return;

        int pivot = start + (end - start) / 2;
        int left = start, right = pivot + 1;
        int idx = start;
        mergeSort_helper(start, pivot, temp, nums);
        mergeSort_helper(pivot + 1, end, temp, nums);

        while (left <= pivot && right <= end) {
            if (nums[left] <= nums[right]) {
                temp[idx++] = nums[left++];
            } else {
                temp[idx++] = nums[right++];
            }
        }

        while (left <= pivot) temp[idx++] = nums[left++];
        while (right <= end) temp[idx++] = nums[right++];

        for (idx = start; idx <= end; idx++) nums[idx] = temp[idx];
    }

    /*
    58. 四数之和

    给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。

    1. 四元组(a, b, c, d)中，需要满足a <= b <= c <= d

    2. 答案中不可以包含重复的四元组。
     */
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if (numbers == null || numbers.length < 4) return ans;

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) continue;
            for (int j = i + 1; j < numbers.length; j++) {
                if (j > i + 1 && numbers[j] == numbers[j - 1]) continue;
                int requireSum = target - (numbers[i] + numbers[j]);
                for (int k = j + 1, l = numbers.length - 1; k < l; ) {
                    while (k < l && k > j + 1 && numbers[k] == numbers[k - 1]) k++;
                    while (k < l && l < numbers.length - 1 && numbers[l] == numbers[l + 1]) l--;
                    if (k < l) {
                        int subSum = numbers[k] + numbers[l];
                        if (subSum < requireSum) {
                            k++;
                        } else if (subSum == requireSum) {
                            ans.add(Arrays.asList(numbers[i], numbers[j], numbers[k], numbers[l]));
                            k++;
                            l--;
                        } else {
                            l--;
                        }
                    }
                }
            }
        }

        return ans;
    }

    /*
    59. 最接近的三数之和

    给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。

    1. 只需要返回三元组之和，无需返回三元组本身
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        int ans = Integer.MIN_VALUE;
        if (numbers == null || numbers.length < 2) return ans;

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) continue;
            for (int j = i + 1, k = numbers.length - 1; j < k;) {
                while (j < k && j > i + 1 && numbers[j] == numbers[j - 1]) j++;
                while (j < k && k < numbers.length - 1 && numbers[k] == numbers[k + 1]) k--;
                if (j < k) {
                    if (target - numbers[i] > numbers[j] + numbers[k]) {
                        if (Math.abs(target - ans) > Math.abs(target - numbers[i] - numbers[j] - numbers[k]))
                            ans = numbers[i] + numbers[j] + numbers[k];
                        j++;
                    } else if (target - numbers[i] == numbers[j] + numbers[k]) {
                        return target;
                    } else {
                        if (Math.abs(target - ans) > Math.abs(target - numbers[i] - numbers[j] - numbers[k]))
                            ans = numbers[i] + numbers[j] + numbers[k];
                        k--;
                    }
                }
            }
        }

        return ans;
    }

    /*
    62. 搜索旋转排序数组

    假设有一个排序的按未知的旋转轴旋转的数组(比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。

    给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。你可以假设数组中不存在重复的元素。
     */

    /*
    63. 搜索旋转排序数组 II

    跟进“搜索旋转排序数组”，假如有重复元素又将如何？

    是否会影响运行时间复杂度？

    如何影响？

    为何会影响？

    写出一个函数判断给定的目标值是否出现在数组中。

    （无法使用二分，只能for循环）
     */
    public int search(int[] A, int target) {
        // write your code here
        int ans = -1;
        if (A == null || A.length == 0) return ans;

        int low = 0, high = A.length - 1;
        while (low < high - 1) {
            int mid = low + (high - low) / 2;
            if (A[mid] > A[low]) {
                if (A[mid] >= target && target >= A[low]) {
                    high = mid;
                } else {
                    low = mid;
                }
            } else {
                if (A[mid] <= target && target <= A[high]) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
        }

        if (A[low] == target) ans = low;
        if (A[high] == target) ans = high;

        return ans;
    }

    /*
    70. 二叉树的层次遍历 II

    给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                subList.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            ans.add(0, subList);
        }

        return ans;
    }

    /*
    71. 二叉树的锯齿形层次遍历

    给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行）
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);

                if (ans.size() % 2 == 0) {
                    subList.add(curr.val);
                } else {
                    subList.add(0, curr.val);
                }
            }
            ans.add(subList);
        }

        return ans;
    }

    /*
    72. 中序遍历和后序遍历树构造二叉树

    根据中序遍历和后序遍历树构造二叉树

    1. 你可以假设树中不存在相同数值的节点
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // write your code here
        TreeNode ans = null;
        if (inorder == null || inorder.length == 0) return ans;
        if (postorder == null || postorder.length == 0) return ans;

        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);

        ans = buildTree_helper(
                0, inorder.length - 1, new int[]{postorder.length - 1}, inorder, postorder, inorderMap
        );

        return ans;
    }

    private TreeNode buildTree_helper(
            int start, int end, int[] postorderIdx, int[] inorder, int[] postorder, Map<Integer, Integer> inorderMap
    ) {
        if (start > end) return null;
        if (start == end) {
            postorderIdx[0] = postorderIdx[0] - 1;
            return new TreeNode(inorder[start]);
        }

        int rootVal = postorder[postorderIdx[0]];
        int rootIdx = inorderMap.get(rootVal);
        if (rootIdx < start || rootIdx > end) return null;
        TreeNode root = new TreeNode(rootVal);

        postorderIdx[0] = postorderIdx[0] - 1;
        root.right = buildTree_helper(rootIdx + 1, end, postorderIdx, inorder, postorder, inorderMap);
        root.left = buildTree_helper(start, rootIdx - 1, postorderIdx, inorder, postorder, inorderMap);

        return root;
    }

    /*
    73. 前序遍历和中序遍历树构造二叉树

    根据前序遍历和中序遍历树构造二叉树.

    1. 你可以假设树中不存在相同数值的节点
     */
    public TreeNode buildTree_1(int[] preorder, int[] inorder) {
        // write your code here
        TreeNode ans = null;
        if (preorder == null || preorder.length == 0) return ans;
        if (inorder == null || inorder.length == 0) return ans;

        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);

        ans = buildTree_1_helper(
                0, inorder.length - 1, new int[]{0}, preorder, inorder, inorderMap
        );

        return ans;
    }

    private TreeNode buildTree_1_helper(
            int start, int end, int[] preorderIdx, int[] preorder, int[] inorder, Map<Integer, Integer> inorderMap
    ) {
        if (start > end) return null;
        if (start == end) {
            preorderIdx[0] = preorderIdx[0] + 1;
            return new TreeNode(inorder[start]);
        }

        int rootVal = preorder[preorderIdx[0]];
        int rootIdx = inorderMap.get(rootVal);
        if (rootIdx < start || rootIdx > end) return null;
        TreeNode root = new TreeNode(rootVal);

        preorderIdx[0] = preorderIdx[0] + 1;
        root.left = buildTree_1_helper(start, rootIdx - 1, preorderIdx, preorder, inorder, inorderMap);
        root.right = buildTree_1_helper(rootIdx + 1, end, preorderIdx, preorder, inorder, inorderMap);

        return root;
    }

    /*
    77. 最长公共子序列

    给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。

    1. 最长公共子序列的定义：
       最长公共子序列问题是在一组序列（通常2个）中找到最长公共子序列（注意：不同于子串，LCS不需要是连续的子串）。
       该问题是典型的计算机科学问题，是文件差异比较程序的基础，在生物信息学中也有所应用。
       https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int ans = 0;
        if (A == null || A.length() == 0) return ans;
        if (B == null || B.length() == 0) return ans;

        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i < A.length() + 1; i++) dp[i][0] = 0;
        for (int i = 0; i < A.length() + 1; i++) dp[i][0] = 0;

        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[(i - 1)][j], dp[i][(j - 1)]);
                    dp[i][j] = Math.max(dp[(i - 1)][(j - 1)] + 1, dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[(i - 1)][j], dp[i][(j - 1)]);
                    dp[i][j] = Math.max(dp[(i - 1)][(j - 1)], dp[i][j]);
                }
            }
        }

        ans = dp[A.length()][B.length()];

        return ans;
    }

    /*
    79. 最长公共子串

    给出两个字符串，找到最长公共子串，并返回其长度。

    1. 子串的字符应该连续的出现在原字符串中，这与子序列有所不同。
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        int ans = 0;
        if (A == null || A.length() == 0) return ans;
        if (B == null || B.length() == 0) return ans;

        int[][] dp = new int[A.length() + 1][B.length() + 1];

        for (int i = 0; i < A.length() + 1; i++) dp[i][0] = 0;
        for (int i = 0; i < B.length() + 1; i++) dp[0][i] = 0;

        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    /*
    90. k数和 II

    给定n个不同的正整数，整数k（1<= k <= n）以及一个目标数字。　　　　

    在这n个数里面找出K个数，使得这K个数的和等于目标数字，你需要找出所有满足要求的方案。
     */
    public List<List<Integer>> kSumII(int[] A, int k, int targer) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if (A == null || A.length == 0) return ans;
        if (k < 1 || k > A.length) return ans;

        if (k == 1)
            for (int num : A)
                if (num == targer) {
                    ans.add(Collections.singletonList(num));
                    return ans;
                }

        Arrays.sort(A);

        kSumII_helper(A, 0, k, targer, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void kSumII_helper(
            int[] A, int idx, int k, int target, int subSum, List<Integer> subAns, List<List<Integer>> ans
    ) {

        if (k == 2) {
            for (int i = idx, j = A.length - 1; i < j; ) {
                int curSum = A[i] + A[j] + subSum;
                if (curSum == target) {
                    List<Integer> curAns = new ArrayList<>(subAns);
                    curAns.addAll(Arrays.asList(A[i], A[j]));
                    ans.add(curAns);
                    i++;
                    j--;
                } else if (curSum < target) {
                    i++;
                } else {
                    j--;
                }
            }
            return;
        }

        for (int i = idx; i < A.length; i++) {
            subSum += A[i];
            if (subSum >= target) break;
            subAns.add(A[i]);
            kSumII_helper(A, i + 1, k - 1, target, subSum, subAns, ans);
            subAns.remove(subAns.size() - 1);
            subSum -= A[i];
        }
    }
}
