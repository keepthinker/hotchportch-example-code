package com.keepthinker.example.general.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.keepthinker.example.general.util.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PersonExcelMain {
    private static final String fileName = Utils.getContextClasspath() + "excel" + File.separator + "demo.xlsx";


    public static void main(String[] args) {
//        readExcel();
        readMultipleSheet();
//        writeExcel();
    }

    private static void readExcel() {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Person.class, new PersonExcelListener()).sheet(1).doRead();
    }

    private static void readMultipleSheet() {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ExcelReader excelReader = EasyExcel.read(fileName).build();

        // 如果有多行表头，反而这里要加headRowNumber。
        ReadSheet readSheet0 =
                EasyExcel.readSheet(0).head(Person.class).headRowNumber(2).registerReadListener(new PersonExcelListener()).build();
        ReadSheet readSheet1 =
                EasyExcel.readSheet(1).head(Person.class).headRowNumber(2).registerReadListener(new PersonExcelListener()).build();
        // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能

        excelReader.read(readSheet0, readSheet1);

        excelReader.close();
    }

    private static void writeExcel() {
        try {
            File file = new File(fileName);
            FileOutputStream fs = new FileOutputStream(file);
            //新建ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(fs).build();
            //获取sheet0对象
            WriteSheet mainSheet = EasyExcel.writerSheet(0, "person-1").head(Person.class).build();
            //向sheet0写入数据 传入空list这样只导出表头
            excelWriter.write(new ArrayList(),mainSheet);
            //获取sheet1对象
            WriteSheet detailSheet = EasyExcel.writerSheet(1, "person-2").head(Person.class).build();
            //向sheet1写入数据 传入空list这样只导出表头
            excelWriter.write(Arrays.asList(createPerson0(), createPerson1()),detailSheet);
            //关闭流
            excelWriter.finish();
            excelWriter.close();
        } catch (Exception e) {
            System.out.printf("导出异常%s", e.getMessage());
        }
    }

    private static Person createPerson0() {
        Person person = new Person();
        person.setName("Michael");
        person.setRegion("united states of america");
        person.setBirthday(new Date());
        person.setAge(18);
        person.setHeight(180);
        person.setAddress("New York");
        person.setPhone("+115739987769");
        person.setProfession("Engineer");
        person.setRace("English people");
        person.setSalary(80000);
        person.setSex("Male");
        person.setWeight(70);
        return person;
    }

    private static Person createPerson1() {
        Person person = new Person();
        person.setName("Chen Yong");
        person.setRegion("Republic of China");
        person.setBirthday(new Date());
        person.setAge(20);
        person.setHeight(181);
        person.setAddress("Taiwan");
        person.setPhone("+88615739987769");
        person.setProfession("Lawyer");
        person.setRace("Chinese");
        person.setSalary(70000);
        person.setSex("Male");
        person.setWeight(69);
        return person;
    }
}
