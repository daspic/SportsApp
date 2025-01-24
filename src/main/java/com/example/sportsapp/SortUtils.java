package com.example.sportsapp;

import java.util.Comparator;
import java.util.List;

public class SortUtils {
    public static <T> void sortList(List<T> list, String sortField, Comparator<T> nameComparator, Comparator<T> idComparator) {
        if ("name".equals(sortField)) {
            list.sort(nameComparator);
        } else if ("id".equals(sortField)) {
            list.sort(idComparator);
        }
    }
}
