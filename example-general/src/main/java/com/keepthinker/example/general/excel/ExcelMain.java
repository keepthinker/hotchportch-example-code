package com.keepthinker.example.general.excel;

import com.alibaba.excel.EasyExcel;
import com.keepthinker.example.general.util.Utils;

import java.io.File;

public class ExcelMain {
    public static void main(String[] args) {
        String fileName = Utils.getContextClasspath() + "excel" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Person.class, new PersonExcelListener()).sheet().doRead();
    }
}
