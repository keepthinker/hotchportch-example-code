package com.keepthinker.example.general.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.util.Date;

public class ElementA {

    @ExcelProperty(value = {"FirstHeader", "FieldA0"}, order = -10)
    private String paramA0;
    @ExcelProperty(value = {"FirstHeader", "FieldA1"}, order = -9)
    private Integer paramA1;
    @DateTimeFormat(value = "yyyy-MM-dd")
    @ExcelProperty(value = {"FirstHeader", "FieldA2"}, order = -8)
    private Date paramA2;

    public String getParamA0() {
        return paramA0;
    }

    public void setParamA0(String paramA0) {
        this.paramA0 = paramA0;
    }

    public Integer getParamA1() {
        return paramA1;
    }

    public void setParamA1(Integer paramA1) {
        this.paramA1 = paramA1;
    }

    public Date getParamA2() {
        return paramA2;
    }

    public void setParamA2(Date paramA2) {
        this.paramA2 = paramA2;
    }
}
