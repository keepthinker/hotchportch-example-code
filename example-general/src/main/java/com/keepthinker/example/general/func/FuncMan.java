package com.keepthinker.example.general.func;

import com.keepthinker.example.general.excel.Person;

import java.lang.reflect.Array;
import java.util.function.*;

public class FuncMan {
    public static void main(String[] args) {
        //代表了一个接受两个输入参数的操作，并且不返回任何结果
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello");

        //代表了一个接受两个输入参数的方法，并且返回一个结果
        BiFunction<Integer, Integer, String> biFunction = (x, y) -> new StringBuilder().append(x).append(':').append(y).toString();
        System.out.println(biFunction.apply(1,3));

        BinaryOperator<Integer> multiplyOperator = (x, y) -> x * y;
        System.out.println(multiplyOperator.apply(2, 8));

        BiPredicate<String, String> equalsBiPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> notEqualsBiPredicate = (x, y) -> x.equals(y);
        System.out.println(equalsBiPredicate.or(notEqualsBiPredicate).test("x", "y"));

        BooleanSupplier booleanSupplier = () -> true;
        System.out.println(booleanSupplier.getAsBoolean());

        Supplier<Person> cons = Person::new;
        System.out.println(cons.get());

        Function<String, Person> func = Person::new;
        System.out.println(func.apply("name-my"));

        int[] x = {5,2,10};
        Integer[][][] ob = (Integer[][][])Array.newInstance(Integer.class, x);
        ob[0][0][9] = 1;
        System.out.println(ob[0][0][9]);

        Object array = Array.newInstance(Integer.class, 1);
        Class<?> wrapperType = Array.get(array, 0).getClass();
        System.out.println(wrapperType);

    }
}
