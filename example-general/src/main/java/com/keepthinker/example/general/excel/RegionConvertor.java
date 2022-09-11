package com.keepthinker.example.general.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegionConvertor implements Converter<String> {
    private static Map<String, String> countryTopDomainMap = new HashMap<>();

    static {
        countryTopDomainMap.put("China".toLowerCase(), "cn");
        countryTopDomainMap.put("People's Republic of China".toLowerCase(), "cn");
        countryTopDomainMap.put("United States of America".toLowerCase(), "us");
        countryTopDomainMap.put("USA".toLowerCase(), "us");
        countryTopDomainMap.put("United Kingdom".toLowerCase(), "uk");
        countryTopDomainMap.put("Britain".toLowerCase(), "uk");
    }


    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadConverterContext<?> context) throws Exception {
        String excelValue = context.getReadCellData().getStringValue();
        return countryTopDomainMap.getOrDefault(excelValue, excelValue);
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
        return new WriteCellData<>(context.getValue());
    }
}
