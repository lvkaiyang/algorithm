package org.algorithm.algorithm.structures.custom;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name ShortestPathPoint
 * @date 2020/6/2 17:04
 **/
public class ShortestPathPoint {

    private int x, y, val;

    public ShortestPathPoint(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
