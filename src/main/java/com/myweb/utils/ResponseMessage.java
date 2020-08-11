package com.myweb.utils;

import java.io.Serializable;
import java.util.HashMap;


/**
 * 訊息響應工具類，允許更彈性且方便地進行訊息響應。
 */
public class ResponseMessage implements Serializable {

    private String resultMsg;
    private String resultCode;
    private HashMap<String,Object> objectMap = new HashMap<>();

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public HashMap<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(HashMap<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    public ResponseMessage addObject(String key,Object value){
        objectMap.put(key , value);
        return this;
    }

    //響應表示符合要求。
    public static ResponseMessage success(){
        ResponseMessage rm = new ResponseMessage();
        rm.setResultCode("100");
        rm.setResultMsg("符合權限");
        return rm;
    }

    //響應表示不符合要求，權限不足等等。
    public static ResponseMessage error(){
        ResponseMessage rm = new ResponseMessage();
        rm.setResultCode("200");
        rm.setResultMsg("錯誤權限");
        return rm;
    }



}






