package com.adc.basesimple;

import com.sun.source.doctree.SeeTree;

import java.util.*;

/**
 * 集合相关示例
 *
 * @author risfeng
 * @date 2020/04/25
 */
public class CollectionsSimpleApplication {


    public static void main(String[] args) {
        int[] arr = {1, 4, 1, 4, 2, 5, 4, 5, 8, 7, 8, 77, 88, 5, 4, 9, 6, 2, 4, 1, 5};
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int i : arr) {
            boolean contains = countMap.containsKey(i);
            if (contains) {
                Integer value = countMap.get(i) + 1;
                countMap.put(i, value);
            } else {
                countMap.put(i, 1);
            }
        }
        List<Map.Entry<Integer, Integer>> linkedList = new LinkedList<>(countMap.entrySet());
        linkedList.sort(Map.Entry.comparingByValue());
        linkedList.forEach(item -> {
            System.out.println(item.getKey() + " 出现 " + item.getValue() + "次");
        });
//        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
//        for (Map.Entry<Integer, Integer> integerEntry : linkedList) {
//            linkedHashMap.put(integerEntry.getKey(), integerEntry.getValue());
//        }
//        linkedHashMap.forEach((k, v) -> {
//            System.out.println(k + " 出现 " + v + " 次");
//        });
    }
}
