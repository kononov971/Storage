package com.innotechnum.practice.task3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

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
        while(store.getGoodsBalance() > 0 || Store.barrier.getNumberWaiting() != 0) {
            try {
                Store.barrier.await();
            } catch (InterruptedException e) {
                System.out.println("Произошло прерывание");
            } catch (BrokenBarrierException e) {
            }
            int quantityOfGoods = store.decreaseGoodsBalance(getRandomGoods());

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
