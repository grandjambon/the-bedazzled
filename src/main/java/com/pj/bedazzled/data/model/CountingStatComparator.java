package com.pj.bedazzled.data.model;

import java.util.Comparator;

public class CountingStatComparator implements Comparator<CountingStat> {
    @Override
    public int compare(CountingStat o1, CountingStat o2) {
        int compare = Integer.compare(o1.getCountingStat(), o2.getCountingStat()) * -1;

        if (compare == 0) {
            compare = o1.getName().compareTo(o2.getName()) * -1;
        }

        return compare;
    }
}
