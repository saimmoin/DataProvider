package com.DataProvider.DataProvider.MultiThreading;

public class CompanyD implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("This is Company D");
    }
}
