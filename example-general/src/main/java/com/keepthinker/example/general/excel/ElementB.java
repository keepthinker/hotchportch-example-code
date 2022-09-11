package com.keepthinker.example.general.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class ElementB extends ElementA{
    @ExcelProperty(value = {"FirstHeader", "FieldB0"})
    private String paramB0;

    public String getParamB0() {
        return paramB0;
    }

    public void setParamB0(String paramB0) {
        this.paramB0 = paramB0;
    }
}
