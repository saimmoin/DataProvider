package com.DataProvider.DataProvider.MultiThreading;

import org.apache.http.util.Args;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        Thread thread =  Thread.currentThread();
        String mainThread = thread.getName();
        long mainThreadId = thread.getId();
        System.out.println("This is thread name : "+mainThread);
        System.out.println("This is thread id : "+mainThreadId);
        CompanyA companyA = new CompanyA();
        companyA.start();
//        companyA.wait(5000);
        companyA.setName("Company A");
        String threadOne = companyA.getName();
        long ThreadOneId = companyA.getId();
        System.out.println("This is first thread name : "+threadOne);
        System.out.println("This is first thread id : "+ThreadOneId);
        CompanyB companyB = new CompanyB();
        companyB.start();
        companyB.setName("Company B");
        String threadTwo = companyB.getName();
        long ThreadTwoId = companyB.getId();
        System.out.println("This is second thread name : "+threadTwo);
        System.out.println("This is second thread id : "+ThreadTwoId);




        CompanyC companyC = new CompanyC();
        Thread threadC = new Thread(companyC);
        threadC.setName("Company C");
        String threadCName = threadC.getName();
        long threadCID = threadC.getId();
        System.out.println("Name Of Company C: "+threadCName);
        System.out.println("ID Of Company C: "+threadCID);
        threadC.start();

        CompanyD companyD = new CompanyD();
        Thread threadD = new Thread(companyD);
        threadD.setName("Company D");
        String threadDName = threadD.getName();
        long threadDID = threadD.getId();
        System.out.println("Name Of Company D: "+threadDName);
        System.out.println("ID Of Company D: "+threadDID);
        threadD.start();

    }

}
