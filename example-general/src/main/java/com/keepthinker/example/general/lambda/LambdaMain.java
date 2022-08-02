package com.keepthinker.example.general.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaMain {
    public static void main(String[] args) {
        map();
        filter();
        sorted();
        forEarch();
        reduce();
    }

    private static void map() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> squares = numbers.stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println("map: " + squares);
    }

    private static void filter() {
        List<String> names = Arrays.asList("Reflection", "Collection", "Stream");
        List<String> result = names.stream().filter(s -> s.startsWith("S")).collect(Collectors.toList());
        System.out.println("filter: " + result);
    }

    private static void sorted() {
        List<Integer> numbers = Arrays.asList(3, 1, 2, 4);
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("map: " + sortedNumbers);
    }

    private static void forEarch() {
        List<Integer> numbers = Arrays.asList(3, 1, 2, 4);
        numbers.stream().map(x -> x * x).forEach(x -> {
                    System.out.printf("%d ", x);
                }
        );
        System.out.println();
    }

    private static void reduce() {
        List<Integer> numbers = Arrays.asList(3, 1, 2, 4);
        int product = numbers.stream().reduce(1, (r, x) -> {
                    return r * x;
                }
        );
        System.out.println("product: " + product);
    }
}
