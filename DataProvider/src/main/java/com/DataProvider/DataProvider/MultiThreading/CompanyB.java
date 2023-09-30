package com.DataProvider.DataProvider.MultiThreading;

public class CompanyB extends Thread{

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello Company B");
    }

}
