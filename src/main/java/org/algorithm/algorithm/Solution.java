package org.algorithm.algorithm;

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
}
