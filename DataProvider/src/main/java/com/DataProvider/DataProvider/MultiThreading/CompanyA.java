package com.DataProvider.DataProvider.MultiThreading;

public class CompanyA extends Thread{

    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello Company A");
    }

}
