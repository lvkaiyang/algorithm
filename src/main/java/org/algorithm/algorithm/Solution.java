package org.algorithm.algorithm;

import org.algorithm.algorithm.structures.GetModifiedArrayInterval;
import org.algorithm.algorithm.structures.ShortestPathPoint;

import java.util.*;

public class Solution {

    /*
    在无限平面上，机器人最初位于（0，0）并朝北。 机器人可以接收以下三个指令之一：

            “ G”：直线前进1个单位；
            “ L”：向左旋转90度；
            “ R”：向右转90度。
    机器人执行顺序给出的指令，一直重复执行。
    当且仅当平面中存在一个使机器人永远不会离开环时，才返回true。

    1 <= instructions.length <= 100
    instructions[i] 属于 {'G', 'L', 'R'}
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

        points.sort(Comparator.comparingInt(p -> p.getIdx()));

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
}
