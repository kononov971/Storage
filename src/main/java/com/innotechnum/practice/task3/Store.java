package com.innotechnum.practice.task3;

import java.util.concurrent.CyclicBarrier;

public class Store {
    private int goodsBalance;
    static CyclicBarrier barrier;

    public Store(int numberCounters) {
        goodsBalance = 1000;
        this.barrier = new CyclicBarrier(numberCounters);
    }

    public int getGoodsBalance() {
        return goodsBalance;
    }

    public synchronized int decreaseGoodsBalance(int decreaseValue) {
        if (decreaseValue > goodsBalance) {
            decreaseValue = goodsBalance;
        }
        this.goodsBalance -= decreaseValue;
        return decreaseValue;
    }
}
