package org.algorithm.algorithm;

import org.algorithm.algorithm.structures.basic.TreeNode;
import org.algorithm.algorithm.structures.custom.GetModifiedArrayInterval;
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
}
