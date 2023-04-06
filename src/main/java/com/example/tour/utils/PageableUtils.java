//package com.example.tour.utils;
//
//import java.util.LinkedHashMap;
//
//public class PageableUtils {
//
//    private VariableData variableData;
//
//    public PageableUtils() {
//        this.init();
//    }
//
//    private void init() {
//        if (variableData == null) {
//            variableData = ApplicationContextProvider.getInstance().getContext().getBean(VariableData.class);
//        }
//    }
//
//    public void preparePageable(PageableCommon pageableCommon) {
//        if (pageableCommon == null) {
//            pageableCommon = new PageableCommon();
//        }
//        if (!Formatter.isNotNull(pageableCommon.getPage()))
//            pageableCommon.setPage(variableData.getPage());
//        if (!Formatter.isNotNull(pageableCommon.getPageSize()))
//            pageableCommon.setPageSize(variableData.getPageSize());
//        if (!Formatter.isNotNull(pageableCommon.getSort())) {
//            LinkedHashMap<Object, Object> sortData = new LinkedHashMap<>();
//            sortData.put("createdDate", "desc");
//            pageableCommon.setSort(sortData);
//        }
//    }
//}
