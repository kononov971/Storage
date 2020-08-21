package com.innotechnum.practice.task3;

public class Storage {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Некорректные параметры запуска. Один параметр - количество покупателей.");
        }

        int numberCustomers = 0;
        try {
            numberCustomers = Integer.valueOf(args[0]);
            if (numberCustomers <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректно указано количество покупателей. Количество  - положительное число");
        }

        Store store = new Store(numberCustomers);


        for(int i = 0; i < numberCustomers; i++){
            new Thread(new Customer(store)).start();
        }
    }
}
