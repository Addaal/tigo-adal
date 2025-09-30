package com.adal.tigo.model;

public class GenericResponse {
    private String timestamp;
    private String code;
    private String detail;
    private int status;

    public GenericResponse(String timestamp, String code, String detail, int status) {
        this.timestamp = timestamp;
        this.code = code;
        this.detail = detail;
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
