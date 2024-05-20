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

        List<ValueObject> valueObjectList = makeValueObjectList();
        System.out.println(valueObjectList.stream().map(ValueObject::getField1).collect(Collectors.toList()));
        System.out.println(valueObjectList.stream().map(ValueObject::getField1).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList()));

        System.out.println(valueObjectList.stream().reduce(null, (x, y) -> {
            if (x == null) {
                return y;
            }
            ValueObject valueObject = new ValueObject();
            valueObject.setField1(x.getField1() + ":" + y.getField1());
            valueObject.setField2(x.getField2() + y.getField2());
            return valueObject; }));

        System.out.println();
        System.out.println(valueObjectList.stream().map(ValueObject::getField1).collect(Collectors.joining(",")));


        System.out.println(valueObjectList.stream().map(x -> {
            ValueObject[] valueObjects = new ValueObject[2];
            valueObjects[0] = makeValueObject(x.getField1() + 1, x.getField2() + 1);
            valueObjects[1] = makeValueObject(x.getField1() + 2, x.getField2() + 2);
            return valueObjects;
        }).flatMap(Arrays::stream).peek(x -> {System.out.println("consume field1:" + x.getField1());}).collect(Collectors.toList()));



    }

    private static List<ValueObject> makeValueObjectList() {

        List<ValueObject> valueObjectList = new ArrayList<>();
        valueObjectList.add(makeValueObject("field1", 12));
        valueObjectList.add(makeValueObject("field3", 34));
        valueObjectList.add(makeValueObject("field2", 78));
        return valueObjectList;

    }

    private static ValueObject makeValueObject(String field1, Integer field2) {
        ValueObject valueObject = new ValueObject();
        valueObject.setField1(field1);
        valueObject.setField2(field2);
        return valueObject;
    }

    private static void map() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 5, 5);
        List<Integer> squares = numbers.stream().distinct().map(x -> x * x).collect(Collectors.toList());
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

        new Random().ints().limit(10).forEach(x -> {System.out.print(x + ":");});
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
