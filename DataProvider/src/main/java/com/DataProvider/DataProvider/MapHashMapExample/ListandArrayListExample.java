package com.DataProvider.DataProvider.MapHashMapExample;

import com.DataProvider.DataProvider.Entity.Employee;

import java.util.*;

public class ListandArrayListExample {
    public void bar() {
        List<Employee> list = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("Muhammad Ali");
        employee.setEmailAddress("thegreatali@gmail.com");
        employee.setSalary(1012);
        list.add(employee);

        Employee employee2 = new Employee();
        employee2.setName("Khabib");
        employee2.setEmailAddress("thegreatkhabib@gmail.com");
        employee2.setSalary(5000);
        list.add(employee2);

        Employee employee3 = new Employee();
        employee3.setName("Islam");
        employee3.setEmailAddress("thegreatislam@gmail.com");
        employee3.setSalary(6000);
        list.add(employee3);

        System.out.println(list);

        list.forEach(e -> System.out.println(e.getName()+" is having salary of "+e.getSalary()));
      List<Employee> lst =  list.stream().filter(e -> e.getSalary() > 3000).toList();
        System.out.println(lst);
//        list.add("Sha256");
//        list.add("Sha1");
//        list.add("Sha112");
//        list.forEach(x -> System.out.println(x));
//        System.out.println(list);
    }

    public void moo() {
        List<Integer> integers = new LinkedList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        System.out.println("Integer: "+integers.get(0));
    }

    public void boo() {
        List<Integer> lst = new Stack<>();
        lst.add(4);
        lst.add(8);
        lst.add(6);
        System.out.println(lst.get(1));
    }
}
