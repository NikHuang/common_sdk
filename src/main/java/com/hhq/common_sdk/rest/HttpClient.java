package com.hhq.common_sdk.rest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HttpClient {

    @Autowired
    RestTemplate restTemplate;

    public <T> T get(String url, Map<String, String> headerMap, Map<String, Object> params, Class<T> resType) {
        HttpHeaders httpHeaders = buildHeaders(headerMap);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<T> exchange = restTemplate.exchange(buildUrl(url,params), HttpMethod.GET, httpEntity, resType);
        return exchange.getBody();
    }

    public JSONObject get(String url, Map<String, String> headerMap, Map<String, Object> params) {
        return get(url, headerMap, params, JSONObject.class);
    }

    public <T> T post(String url, Map<String, String> headerMap, Object body,Map<String, Object> params, Class<T> resType) {
        return bodyRequest(url,headerMap,body,params,resType,HttpMethod.POST);
    }
    public JSONObject post(String url, Map<String, String> headerMap, Object body,Map<String, Object> params) {
        return bodyRequest(url,headerMap,body,params,JSONObject.class,HttpMethod.POST);
    }

    public <T> T put(String url, Map<String, String> headerMap, Object body,Map<String, Object> params, Class<T> resType) {
        return bodyRequest(url,headerMap,body,params,resType,HttpMethod.PUT);
    }
    public JSONObject put(String url, Map<String, String> headerMap, Object body,Map<String, Object> params) {
        return bodyRequest(url,headerMap,body,params,JSONObject.class,HttpMethod.PUT);
    }

    public <T> T patch(String url, Map<String, String> headerMap, Object body,Map<String, Object> params, Class<T> resType) {
        return bodyRequest(url,headerMap,body,params,resType,HttpMethod.PATCH);
    }
    public JSONObject patch(String url, Map<String, String> headerMap, Object body,Map<String, Object> params) {
        return bodyRequest(url,headerMap,body,params,JSONObject.class,HttpMethod.PATCH);
    }

    public <T> T delete(String url, Map<String, String> headerMap, Object body,Map<String, Object> params, Class<T> resType) {
        return bodyRequest(url,headerMap,body,params,resType,HttpMethod.DELETE);
    }
    public JSONObject delete(String url, Map<String, String> headerMap, Object body,Map<String, Object> params) {
        return bodyRequest(url,headerMap,body,params,JSONObject.class,HttpMethod.DELETE);
    }

    private String buildUrl(String url,Map<String, Object> params){
        StringBuilder urlBuilder = new StringBuilder(url);
        //处理url与参数
        if (Objects.nonNull(params) && !(params.isEmpty())) {
            urlBuilder.append("?");
            List<String> uriParamList = new ArrayList<>();
            Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                StringBuilder paramBuilder = new StringBuilder();
                String param = paramBuilder.append(next.getKey()).append("=").append(next.getValue()).toString();
                uriParamList.add(param);
            }
            String urlParams = uriParamList.stream().collect(Collectors.joining("&"));
            urlBuilder.append(urlParams);
        }
        return urlBuilder.toString();
    }

    private HttpHeaders buildHeaders(Map<String, String> headerMap){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (Objects.nonNull(headerMap) && !(headerMap.isEmpty())) {
            Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                httpHeaders.add(next.getKey(), next.getValue());
            }
        }
        return httpHeaders;
    }

    private <T> T bodyRequest(String url, Map<String, String> headerMap, Object body,Map<String, Object> params, Class<T> resType,HttpMethod httpMethod){
        HttpHeaders httpHeaders = buildHeaders(headerMap);
        HttpEntity httpEntity = new HttpEntity(body,httpHeaders);
        ResponseEntity<T> exchange = restTemplate.exchange(buildUrl(url,params), httpMethod, httpEntity, resType);
        return exchange.getBody();
    }
}
