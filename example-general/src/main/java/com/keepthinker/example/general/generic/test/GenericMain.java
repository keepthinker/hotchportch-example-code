package com.keepthinker.example.general.generic.test;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.List;

/**
 * 文章：https://qidawu.github.io/posts/java-reflection-generic-type/
 */
public class GenericMain {
    public static void main(String[] args) throws NoSuchMethodException {
//        Type genericSuperclass = PersonMapper.class.getGenericInterfaces()[0];
//        if (genericSuperclass instanceof ParameterizedType parameterizedType) {
//            Type[] types = parameterizedType.getActualTypeArguments();
//            for (Type type : types) {
//                System.out.println(type.getTypeName());
//            }
//        }
        test3();
    }

    private static void test1() throws NoSuchMethodException {

        Method method = PersonMapper.class.getMethod("test", List.class, List.class, Number[].class, Number.class, String.class);

//        Method method2 = PersonMapper.class.getMethod("test2", List.class, Object[].class, Object.class, String.class);

//        Method method3 = PersonMapper.class.getMethod("test3", String.class);

        // [0] ParameterizedType
        // [1] ParameterizedType
        // [2] GenericArrayType
        // [3] TypeVariable
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type type : genericParameterTypes) {
            if (type instanceof ParameterizedType pt) {
                System.out.printf("%s, %s, %s, %s\n", pt.getTypeName(), pt.getRawType(), pt.getOwnerType(), pt.getActualTypeArguments());
            }
            if (type instanceof GenericArrayType gat) {
                System.out.printf("%s, %s\n", gat.getTypeName(), gat.getGenericComponentType());
            }
            if (type instanceof TypeVariable tv) {
                System.out.printf("%s, %s\n", tv.getTypeName(), tv.getBounds());

            }
            System.out.printf("%s; %s\n", type, type.getTypeName());
        }
        System.out.println("----------------------------------------");
        Class[] parameterTypes = method.getParameterTypes();
        for (Class clazz : parameterTypes) {

            System.out.printf("%s\n", clazz);
        }

        // Class
        Type genericReturnType = method.getGenericReturnType();

        // Class[0]
        Type[] genericExceptionTypes = method.getGenericExceptionTypes();

        System.out.println(genericExceptionTypes);
    }

    public static void test2() {
        // com.github.reflection.BaseMapper<com.github.reflection.Person, java.lang.Long>
        Type genericInterface = PersonMapper.class.getGenericInterfaces()[0];
        System.out.println(genericInterface instanceof ParameterizedType);
        ParameterizedType parameterizedType = (ParameterizedType) genericInterface;

        // 获取原始类型：BaseMapper.class
        System.out.println(BaseMapper.class == parameterizedType.getRawType());

        // 获取实际类型参数列表：Person.class、Long.class
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println("There are two actual type arguments|" + (2 == actualTypeArguments.length));
        // class com.github.reflection.Person
        System.out.println("One is Person|" + (actualTypeArguments[0] == Person.class));
        // class java.lang.Long
        System.out.println("Another is Long|" + (actualTypeArguments[1] == Long.class));
    }

    public static void test3() throws NoSuchMethodException {
        // java.util.List<?>
        Method method = PersonMapper.class.getMethod("log", List.class);
        Type genericParameterType = method.getGenericParameterTypes()[0];
        System.out.println("The first parameter type of log method is instance of ParameterizedType|" +
                (genericParameterType instanceof ParameterizedType));
        ParameterizedType parameterType = (ParameterizedType) genericParameterType;

        // WildcardType
        Type type = parameterType.getActualTypeArguments()[0];
        System.out.println("The actual type argument of ParameterizedType is instance of WildcardType|" + (type instanceof WildcardType));
        WildcardType wildcardType = (WildcardType) type;
        System.out.println("No lower bounds exist|" + wildcardType.getLowerBounds().length);
        System.out.println("Only one upper bound exist|" + wildcardType.getUpperBounds().length);
        // 通配符默认上限类型为 Object
        System.out.println("The upper bound is Object|" + wildcardType.getUpperBounds()[0]); //, Object.class
    }

    public static void test4() throws NoSuchMethodException {
        Method method = BaseMapper.class.getMethod("getById", Serializable.class);

        // TypeVariable
        Type genericReturnType = method.getGenericReturnType();
        System.out.println("Return type of method is instance of TypeVariable|" + (genericReturnType instanceof TypeVariable));
        TypeVariable typeVariable = (TypeVariable) genericReturnType;
        System.out.println("First upper bound is Serializable|" + (Serializable.class == typeVariable.getBounds()[0]));
        ParameterizedType parameterizedType = (ParameterizedType) typeVariable.getBounds()[1];
        System.out.println("Second upper bound is Comparable|" + (Comparable.class == parameterizedType.getRawType()));

    }

}
