package com.homeworks.work1;

/**
 * Created by god on 8/7/2016.
 */
public class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left;}
    public R getRight() { return right;}
}
