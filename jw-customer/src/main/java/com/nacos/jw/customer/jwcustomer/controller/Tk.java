package com.nacos.jw.customer.jwcustomer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 抢票代码
 */
public class Tk {

    /**
     * 签名常量
     */
    public static final String ATGC = "plat_pc";
    /**
     * 票价详情页
     */
    public static final String DETAIL = "https://platformpcgateway.polyt.cn/api/1.0/show/getShowInfoDetail";


    public static void main(String[] args) {


        DetailRequest detailRequest = new DetailRequest("29665", "584053652661002240", "", new RequestModel());
        String signString = JSON.toJSONString(detailRequest);
        JSONObject jsonObject = JSON.parseObject(signString);

        Map<String, Object> finalParam = new LinkedHashMap<>();
        jsonObject.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            if (entry instanceof JSONObject) {
                finalParam.put(entry.getKey(), ((JSONObject) entry.getValue())
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Function.identity(), (t1, t2) -> t2, LinkedHashMap::new)));
            } else {
                finalParam.put(entry.getKey(), entry.getValue());
            }
        });
        String value = JSON.toJSONString(finalParam);
        System.out.println(value);
        System.out.println(MD5.md5EncryptTo32(value + ATGC));
    }


    public static class DetailRequest {

        private String productId;

        private String projectId;

        private String theaterId;

        private RequestModel requestModel;

        public DetailRequest(String productId, String projectId, String theaterId, RequestModel requestModel) {
            this.productId = productId;
            this.projectId = projectId;
            this.theaterId = theaterId;
            this.requestModel = requestModel;
        }

        public String getProductId() {
            return productId;
        }

        public String getProjectId() {
            return projectId;
        }

        public String getTheaterId() {
            return theaterId;
        }

        public RequestModel getRequestModel() {
            return requestModel;
        }
    }


    public static class RequestModel {

        private String applicationSource = ATGC;

        private String applicationCode = ATGC;

        private Integer current = 1;

        private Integer size = 10;

        private long timestamp = System.currentTimeMillis();

        private String utgc = "utoken";

        private String atgc = "atoken";

        public String getApplicationSource() {
            return applicationSource;
        }

        public void setApplicationSource(String applicationSource) {
            this.applicationSource = applicationSource;
        }

        public String getApplicationCode() {
            return applicationCode;
        }

        public void setApplicationCode(String applicationCode) {
            this.applicationCode = applicationCode;
        }

        public Integer getCurrent() {
            return current;
        }

        public void setCurrent(Integer current) {
            this.current = current;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getUtgc() {
            return utgc;
        }

        public void setUtgc(String utgc) {
            this.utgc = utgc;
        }

        public String getAtgc() {
            return atgc;
        }

        public void setAtgc(String atgc) {
            this.atgc = atgc;
        }
    }
}
