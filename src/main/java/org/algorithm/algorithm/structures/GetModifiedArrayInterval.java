package org.algorithm.algorithm.structures;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name GetModifiedArrayInterval
 * @date 2020/6/2 17:02
 **/
public class GetModifiedArrayInterval {
    private int idx, inc;

    public GetModifiedArrayInterval(int idx, int inc) {
        this.idx = idx;
        this.inc = inc;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }
}
