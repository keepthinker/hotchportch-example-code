package com.keepthinker.example.general.format;

import java.text.DecimalFormat;

public class DecimalFormatMain {

    public static void main(String[] args) {

        long c = 299792458;//光速
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
        //每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(c));//299,792,458
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米").format(c)); //光速大小为每秒299,792,458米


        formatDecimal(314.15927f);
        formatDecimal(31.415927f);
        formatDecimal(3.1415927f);
        formatDecimal(0.1415927f);
        formatDecimal(0.003f);

    }

    private static void formatDecimal(float number) {

        System.out.println("-------------------------------------");
        //取一位整数
        System.out.println(new DecimalFormat("0").format(number));//3
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(number));//3.14
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(number));//03.142
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(number));//3
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(number));//314.16%
    }
}