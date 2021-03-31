package org.algorithm.algorithm.structures.custom;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name MaxPathSumResultType
 * @date 2021/3/31 15:04
 **/
public class MaxPathSumResultType {
    public int leftMax, rightMax, totalMax;

    public MaxPathSumResultType() {
    }

    public MaxPathSumResultType(int leftMax, int rightMax, int totalMax) {
        this.leftMax = leftMax;
        this.rightMax = rightMax;
        this.totalMax = totalMax;
    }
}
