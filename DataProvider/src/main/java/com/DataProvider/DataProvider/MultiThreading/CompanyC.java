package com.DataProvider.DataProvider.MultiThreading;

public class CompanyC implements Runnable{
    @Override
    public void run() {
//        try {
//            Thread.sleep(8000);
//            throw new RuntimeException(e);
//        }
        
        System.out.println("This is Company C");
    }
}
