package com.keepthinker.example.general.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.keepthinker.example.general.util.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;

public class ExcelMain {
    public static void main(String[] args) {
//        writeExcelInherit();
        readExcelInherit();
    }


    private static void writeExcelInherit() {
        try {
            File file = new File(Utils.getContextClasspath() + "/inherit-example.xlsx");
            FileOutputStream fs = new FileOutputStream(file);
            //新建ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(fs).build();

            ElementB elementB = new ElementB();
            elementB.setParamB0("b0");

            elementB.setParamA0("a0");
            elementB.setParamA1(1);
            elementB.setParamA2(new Date());

            WriteSheet mainSheet = EasyExcel.writerSheet(0, "Inherit-Example").head(ElementB.class).build();
            //向sheet0写入数据 传入空list这样只导出表头
            excelWriter.write(Arrays.asList(elementB), mainSheet);
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readExcelInherit() {
        try {
            File file = new File(Utils.getContextClasspath() + "/inherit-example.xlsx");
            EasyExcel.read(file, ElementB.class, new PageReadListener<ElementB>(dataList -> {
                for (ElementB demoData : dataList) {
                    System.out.printf("读取到一条数据%s\n", Utils.toJsonString(demoData));
                }
            })).sheet().doRead();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
