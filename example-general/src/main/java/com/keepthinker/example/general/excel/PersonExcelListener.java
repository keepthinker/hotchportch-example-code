package com.keepthinker.example.general.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.keepthinker.example.general.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class PersonExcelListener implements ReadListener<Person> {
    private Logger logger = LoggerFactory.getLogger(PersonExcelListener.class);
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        logger.error("on exception", exception);
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        logger.info("invokeHead:{}", Utils.toJsonString(headMap));
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        logger.info("extra:{}", Utils.toJsonString(extra));
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        logger.info("hasNext");
        return true;
    }

    @Override
    public void invoke(Person person, AnalysisContext analysisContext) {
        logger.info("invoke:{}", Utils.toJsonString(person));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("doAfterAllAnalysed");
    }
}
