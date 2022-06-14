package com.thefool.ratelimiter.order;

import java.util.Comparator;

public class OrderComparator implements Comparator<Object> {

    public static OrderComparator instance = new OrderComparator();

    @Override
    public int compare(Object o1, Object o2) {
        Order order1 = o1.getClass().getAnnotation(Order.class);
        Order order2 = o2.getClass().getAnnotation(Order.class);
        int value1 = order1 == null ? 100 : order1.value();
        int value2 = order2 == null ? 100 : order2.value();
        return value1 - value2;
    }
}