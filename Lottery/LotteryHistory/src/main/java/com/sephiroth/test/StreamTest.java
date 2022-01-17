package com.sephiroth.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> result = integerList.stream().collect(partitioningBy(i -> i < 3));
        System.out.println(result);
    }
}
