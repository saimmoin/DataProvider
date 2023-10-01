package com.DataProvider.DataProvider.MapHashMapExample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Example {
    public void foo() {
        Map<String, String> type = new HashMap<>();
        type.put("Saim", "Highly Smart");
        type.put("Rayyan", "Highly Stupid and Dumb");
        System.out.println(type.get("Rayyan"));
//        type.clear();
        System.out.println(type.get("Saim"));
        type.replace("Rayyan", "Highly Stupid and Dumb", "Non-pti copy");
        System.out.println(type.get("Rayyan"));
        type.put(null, "is Null(ABC)");
//        System.out.println(type.+":"+type.get(null));p
        for (String key: type.keySet()) {
            System.out.println("Hash Code: "+type.hashCode());
            System.out.println("Key: "+key+" "+"Value: "+type.get(key));
            System.out.println("Response: "+type.containsValue("Highly Sm"));

        }
    }
}
