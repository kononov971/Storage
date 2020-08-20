package com.innotechnum.practice.task3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Customer implements Runnable {
    Store store;
    int purchaseCounter;
    int goodsCounter;


    public Customer(Store store) {
        this.store = store;
        purchaseCounter = 0;
        goodsCounter = 0;
    }

    public void run() {
        while(store.getGoodsBalance() > 0 ) {
            try {
                Store.barrier.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Произошло прерывание");
            } catch (BrokenBarrierException | TimeoutException ignored) {

            }
            int quantityOfGoods = getRandomGoods();
            if (store.getGoodsBalance() < quantityOfGoods) {
                quantityOfGoods = store.getGoodsBalance();
            }

            store.decreaseGoodsBalance(quantityOfGoods);
            if (quantityOfGoods > 0) {
                purchaseCounter++;
                goodsCounter += quantityOfGoods;
            }
        }
        System.out.println("Количество покупок - " + purchaseCounter + ", количество товара - " + goodsCounter);
    }

    private int getRandomGoods() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
